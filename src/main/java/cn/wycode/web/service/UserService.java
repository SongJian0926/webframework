package cn.wycode.web.service;


import cn.wycode.web.entity.JsonResult;
import cn.wycode.web.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yuicon on 2017/8/8.
 * https://github.com/Yuicon
 */
public interface UserService {

    JsonResult<User> getCurrentUser(HttpServletRequest request);

}
