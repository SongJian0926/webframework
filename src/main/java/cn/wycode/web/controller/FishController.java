package cn.wycode.web.controller;

import cn.wycode.web.entity.*;
import cn.wycode.web.repository.*;
import cn.wycode.web.service.AsyncService;
import cn.wycode.web.service.WXSessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/public/fish")
@Api(value = "fish", description = "养鱼小助手", tags = "Fish")
public class FishController {

    private final FishBaikeRepository baikeRepository;
    private final FishSuggestRepository suggestRepository;
    private final FishQuestionRepository questionRepository;
    private final AsyncService asyncService;
    private final WXSessionService sessionService;
    private final FishUserRepository userRepository;
    private final FishAnswerRepository answerRepository;

    @Autowired
    public FishController(FishBaikeRepository baikeRepository, FishSuggestRepository suggestRepository, FishQuestionRepository questionRepository, AsyncService asyncService, WXSessionService sessionService, FishUserRepository userRepository, FishAnswerRepository answerRepository) {
        this.baikeRepository = baikeRepository;
        this.suggestRepository = suggestRepository;
        this.questionRepository = questionRepository;
        this.asyncService = asyncService;
        this.sessionService = sessionService;
        this.userRepository = userRepository;
        this.answerRepository = answerRepository;
    }

    @ApiOperation(value = "根据类型查询百科，按阅读量倒序排序")
    @RequestMapping(method = RequestMethod.GET, path = "/getBaike")
    public JsonResult<List<FishBaike>> getBaike(@RequestParam String type) {
        List<FishBaike> fishBaikes = baikeRepository.findByTypeOrderByReadCountDesc(type);
        return JsonResult.builder().data(fishBaikes).build();
    }

    @ApiOperation(value = "增加阅读量")
    @RequestMapping(method = RequestMethod.GET, path = "/addReadCount")
    public JsonResult<FishBaike> addReadCount(@RequestParam long id) {
        FishBaike baike = baikeRepository.findOne(id);
        if (baike != null) {
            baike.setReadCount(baike.getReadCount() + 1);
            baike = baikeRepository.save(baike);
        }
        return JsonResult.builder().data(baike).build();
    }

    @ApiOperation(value = "添加百科")
    @RequestMapping(method = RequestMethod.POST, path = "/addBaike")
    public JsonResult<FishSuggest> addBaike(@RequestParam String type, @RequestParam String title, @RequestParam String detail, @RequestParam String imageName) {
        FishBaike baike = new FishBaike(type, title, detail, imageName, new Date());
        return JsonResult.builder().data(baikeRepository.save(baike)).build();
    }

    @ApiOperation(value = "意见反馈")
    @RequestMapping(method = RequestMethod.POST, path = "/suggest")
    public JsonResult<FishSuggest> suggest(@RequestParam String content, @RequestParam String contact) {
        FishSuggest suggest = new FishSuggest(new Date(), content, contact);
        return JsonResult.builder().data(suggestRepository.save(suggest)).build();
    }

    @ApiOperation(value = "添加问题")
    @RequestMapping(method = RequestMethod.POST, path = "/addQuestion")
    public JsonResult<FishQuestion> addQuestion(@RequestParam String accessKey, @RequestParam String content, @RequestParam String title, @RequestParam(required = false) String images) {
        FishUser user = userRepository.findByKey(accessKey);
        if (user == null) {
            return JsonResult.builder().error("accessKey错误").build();
        }
        List<String> imageList = null;
        if (!StringUtils.isEmpty(images)) {
            imageList = Arrays.asList(images.split(","));
        }
        FishQuestion question = new FishQuestion(title, content, user, imageList);
        question = questionRepository.save(question);

        if (imageList != null) {
            asyncService.moveQuestionImage(question.getId(), imageList);
        }

        return JsonResult.builder().data(question).build();
    }

    @ApiOperation(value = "获取问题列表")
    @RequestMapping(method = RequestMethod.GET, path = "/getQuestions")
    public JsonResult<List<FishQuestion>> getQuestions() {
        List<FishQuestion> questions = questionRepository.findByOrderByUpdateTimeDesc();
        return JsonResult.builder().data(questions).build();
    }

    @ApiOperation(value = "获取问题")
    @RequestMapping(method = RequestMethod.GET, path = "/getQuestion")
    public JsonResult<FishQuestion> getQuestion(@RequestParam Long id) {
        FishQuestion question = questionRepository.findOne(id);
        return JsonResult.builder().data(question).build();
    }

    @ApiOperation(value = "获取问题回答")
    @RequestMapping(method = RequestMethod.GET, path = "/getQuestionAnswers")
    public JsonResult<List<FishQuestionAnswer>> getQuestions(@RequestParam Long id) {
        List<FishQuestionAnswer> answers = answerRepository.findAllByQuestion_IdOrderByValueDesc(id);
        return JsonResult.builder().data(answers).build();
    }

    @ApiOperation(value = "问题回答")
    @RequestMapping(method = RequestMethod.POST, path = "/postAnswer")
    public JsonResult<FishQuestionAnswer> postAnswer(@RequestParam String  accessKey,@RequestParam Long id,@RequestParam String content) {
        FishUser user = userRepository.findByKey(accessKey);
        if(user==null) {
            return JsonResult.builder().error("用户session异常，请重新登录").build();
        }
        FishQuestion question = questionRepository.findOne(id);
        if(question==null){
            return JsonResult.builder().error("问题不存在").build();
        }
        question.setUpdateTime(new Date());
        questionRepository.save(question);
        FishQuestionAnswer answer = new FishQuestionAnswer(content,question,user);
        return JsonResult.builder().data(answerRepository.save(answer)).build();
    }

    @ApiOperation(value = "置顶问题")
    @RequestMapping(method = RequestMethod.GET, path = "/questionUp")
    public JsonResult<FishQuestion> questionUp(@RequestParam Long id) {
        FishQuestion question = questionRepository.findOne(id);
        if(question==null){
            return JsonResult.builder().error("问题不存在").build();
        }
        Date now = new Date();
        if(now.getTime()-question.getUpdateTime().getTime()<12*3600*1000){
            return JsonResult.builder().error("12小时内只能置顶1次").build();
        }
        question.setUpdateTime(now);
        return JsonResult.builder().data(questionRepository.save(question)).build();
    }

    @ApiOperation(value = "获取微信")
    @RequestMapping(path = "/wx/getSession", method = RequestMethod.GET)
    public JsonResult<String> getSession(@RequestParam String jsCode) {
        WXSession session = sessionService.getWXSession(jsCode);
        if (session != null) {
            System.out.println(session.toString());
            String accessKey = EncryptionUtil.getHash(session.session_key, EncryptionUtil.MD5);
            FishUser user = userRepository.findByOpenId(session.openid);
            if (user == null) {
                user = new FishUser(session.openid);
            }
            user.setKey(accessKey); //一旦登录就刷新key
            System.out.println(user.toString());
            userRepository.save(user);
            return JsonResult.builder().data(accessKey).build();
        } else {
            return JsonResult.builder().error("未获取到session").build();
        }
    }

    @ApiOperation(value = "更新用户信息")
    @RequestMapping(path = "/updateUserInfo", method = RequestMethod.POST)
    public JsonResult<FishUser> updateUserInfo(@RequestParam String accessKey,
                                               @RequestParam String avatarUrl,
                                               @RequestParam String city,
                                               @RequestParam String country,
                                               @RequestParam int gender,
                                               @RequestParam String language,
                                               @RequestParam String nickName,
                                               @RequestParam String province) {
        FishUser user = userRepository.findByKey(accessKey);
        user.setAvatarUrl(avatarUrl);
        user.setNickName(nickName);
        user.setLanguage(language);
        user.setCity(city);
        user.setCountry(country);
        user.setGender(gender);
        user.setProvince(province);
        user.setUpdateTime(new Date());
        return JsonResult.builder().data(userRepository.save(user)).build();
    }

}
