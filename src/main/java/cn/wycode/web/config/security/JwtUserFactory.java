package cn.wycode.web.config.security;

import cn.wycode.web.entity.Role;
import cn.wycode.web.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Yuicon on 2017/5/14.
 * https://segmentfault.com/u/yuicon
 */
final class JwtUserFactory {

    private JwtUserFactory() {
    }

    static JwtUser create(User user) {
        return new JwtUser(
                user.getUserId(),
                user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
