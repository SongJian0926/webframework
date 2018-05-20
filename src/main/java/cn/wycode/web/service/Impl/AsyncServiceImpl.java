package cn.wycode.web.service.Impl;

import cn.wycode.web.service.AsyncService;
import cn.wycode.web.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsyncServiceImpl implements AsyncService {

    private final StorageService storageService;

    @Autowired
    public AsyncServiceImpl(StorageService storageService) {
        this.storageService = storageService;
    }

    @Async
    @Override
    public void moveQuestionImage(Long questionId, List<String> images) {
        String questionFolder = "question/"+questionId.toString();
        for (String image :
                images) {
            storageService.moveTempFileToFolder(image,questionFolder);
        }
    }
}
