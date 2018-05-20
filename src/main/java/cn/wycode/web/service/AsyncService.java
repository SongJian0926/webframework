package cn.wycode.web.service;

import java.util.List;

public interface AsyncService {
    void moveQuestionImage(Long questionId,List<String> images);
}
