package cn.wycode.web.service.Impl;

import cn.wycode.web.config.security.JwtTokenUtil;
import cn.wycode.web.entity.JsonResult;
import cn.wycode.web.entity.User;
import cn.wycode.web.repository.UserRepository;
import cn.wycode.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
/**
 * Created by Yuicon on 2017/8/20.
 * https://github.com/Yuicon
 */
@Service()
public class UserServiceImpl implements UserService {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    private final JwtTokenUtil jwtTokenUtil;

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(JwtTokenUtil jwtTokenUtil, UserRepository userRepository) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
    }

    @Override
    public JsonResult<User> getCurrentUser(HttpServletRequest request) {

        String authHeader = request.getHeader(this.tokenHeader);
        if (authHeader != null && authHeader.startsWith(tokenHead)) {
            final String authToken = authHeader.substring(tokenHead.length()); // The part after "Bearer "
            User user = userRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(authToken));
            if (user == null) {
                return JsonResult.<User>builder().error("token错误").build();
            }
            return JsonResult.<User>builder().data(user).build();
        }
        return JsonResult.<User>builder().error("token错误").build();
    }
}
