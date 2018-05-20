package cn.wycode.web.controller;

import cn.wycode.web.entity.Clipboard;
import cn.wycode.web.entity.ClipboardSuggest;
import cn.wycode.web.entity.JsonResult;
import cn.wycode.web.repository.ClipboardRepository;
import cn.wycode.web.repository.ClipboardSuggestRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping("/api/public/clipboard")
@Api(value = "clipboard", description = "剪切板", tags = "Clipboard")
public class ClipboardController {

    private ClipboardRepository repository;

    private ClipboardSuggestRepository suggestRepository;

    @Autowired
    public ClipboardController(ClipboardRepository repository, ClipboardSuggestRepository suggestRepository) {
        this.repository = repository;
        this.suggestRepository = suggestRepository;
    }

    @ApiOperation(value = "新建剪切板")
    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public JsonResult<Clipboard> create() {
        Clipboard last = repository.findTopByOrderByIdDesc();
        long id = 1000;
        if (last != null) {
            id = last.getId() + new Random().nextInt(5)+1;
        }

        Clipboard newClipboard = new Clipboard(new Date(), new Date(), "请使用查询码"+id+"在：https://wycode.cn/clipboard/ 或使用微信小程序查看自己的粘贴板");
        newClipboard.setId(id);
        Clipboard save = repository.save(newClipboard);

        return JsonResult.builder().data(save).build();
    }

    @ApiOperation(value = "查询剪切板")
    @RequestMapping(method = RequestMethod.GET, path = "/query")
    public JsonResult<Clipboard> query(@RequestParam long id) {
        Clipboard p = repository.findOne(id);
        return JsonResult.builder().data(p).build();
    }

    @ApiOperation(value = "保存剪切板")
    @RequestMapping(method = RequestMethod.POST, path = "/save")
    public JsonResult<Clipboard> save(@RequestParam long id,@RequestParam String content) {
        Clipboard p = repository.findOne(id);
        Clipboard save = null;
        if(p!=null){
            p.setContent(content);
            p.setLastUpdate(new Date());
            save = repository.save(p);
        }
        return JsonResult.builder().data(save).build();
    }

    @ApiOperation(value = "意见反馈")
    @RequestMapping(method = RequestMethod.POST, path = "/suggest")
    public JsonResult<ClipboardSuggest> suggest(@RequestParam String content,@RequestParam String contact){
        ClipboardSuggest suggest = new ClipboardSuggest(new Date(),content,contact);
        return JsonResult.builder().data(suggestRepository.save(suggest)).build();
    }

}
