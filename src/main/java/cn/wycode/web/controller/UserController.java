package cn.wycode.web.controller;

import cn.wycode.web.entity.JsonResult;
import cn.wycode.web.entity.User;
import cn.wycode.web.repository.UserRepository;
import cn.wycode.web.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Yuicon on 2017/5/14.
 * https://github.com/Yuicon
 * 在 @PreAuthorize 中我们可以利用内建的 SPEL 表达式：比如 'hasRole()' 来决定哪些用户有权访问。
 * 需注意的一点是 hasRole 表达式认为每个角色名字前都有一个前缀 'ROLE_'。所以这里的 'ADMIN' 其实在
 * 数据库中存储的是 'ROLE_ADMIN' 。这个 @PreAuthorize 可以修饰Controller也可修饰Controller中的方法。
 */
@Api(value = "users", description = "用户相关", tags = "User")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户列表")
    @PreAuthorize("hasAnyRole('ADMIN','GUEST','USER')")
    @RequestMapping(method = RequestMethod.GET, path = "/users")
    public JsonResult<List<User>> getUsers() {
        List<User> users = (List<User>) repository.findAll();
        return JsonResult.<List<User>>builder().data(users).build();
    }

    @ApiOperation(value = "新建用户")
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/add")
    JsonResult<User> addUser(@RequestBody User addedUser) {
        return JsonResult.<User>builder().data(repository.save(addedUser)).build();
    }

    @ApiOperation(value = "获取用户", notes = "根据url的id来获取用户详细信息")
    @PostAuthorize("returnObject.username == principal.username or hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JsonResult<User> getUser(@PathVariable long id) {
        return JsonResult.<User>builder().data(repository.findOne(id)).build();
    }

    @ApiOperation(value = "修改用户", notes = "通过ID")
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    JsonResult<User> updateUser(@PathVariable long id, @RequestBody User updatedUser) {
        updatedUser.setUserId(id);
        return JsonResult.<User>builder().data(repository.save(updatedUser)).build();
    }

    @ApiOperation(value = "删除用户", notes = "通过ID")
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    JsonResult<User> removeUser(@PathVariable long id) {
        User deletedUser = repository.findOne(id);
        repository.delete(id);
        return JsonResult.<User>builder().data(deletedUser).build();
    }

    @ApiOperation(value = "获取用户", notes = "通过用户名")
    @PostAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public JsonResult<User> getUserByUsername(@RequestParam(value = "username") String username) {
        return JsonResult.<User>builder().data(repository.findByUsername(username)).build();
    }

    @ApiOperation(value = "获取当前用户")
    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public JsonResult<User> getCurrentUser(HttpServletRequest request) {
        return userService.getCurrentUser(request);
    }
}
