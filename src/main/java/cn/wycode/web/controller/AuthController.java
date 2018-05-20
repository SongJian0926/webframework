package cn.wycode.web.controller;

import cn.wycode.web.entity.JsonResult;
import cn.wycode.web.entity.User;
import cn.wycode.web.service.AuthService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yuicon on 2017/5/20.
 * https://segmentfault.com/u/yuicon
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonResult<String> createAuthenticationToken(
            @ApiParam(value = "username") @RequestParam(name = "username") String username,
            @ApiParam(value = "password") @RequestParam(name = "password") String password) throws AuthenticationException {

        return authService.login(username, password);
    }

    @ApiOperation(value = "刷新Token")
    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException {
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if (refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(refreshedToken);
        }
    }

    @ApiOperation(value = "注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public JsonResult<User> register(@RequestBody User addedUser) throws AuthenticationException {
        return authService.register(addedUser);
    }

}
