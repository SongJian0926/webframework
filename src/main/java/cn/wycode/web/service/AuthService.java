package cn.wycode.web.service;

import cn.wycode.web.entity.JsonResult;
import cn.wycode.web.entity.User;

/**
 * Created by Yuicon on 2017/5/20.
 * https://segmentfault.com/u/yuicon
 */
public interface AuthService {

    JsonResult<User> register(User userToAdd);

    JsonResult<String> login(String username, String password);

    String refresh(String oldToken);
}
