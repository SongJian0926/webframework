package cn.wycode.web;

import cn.wycode.web.entity.User;
import cn.wycode.web.entity.Role;
import cn.wycode.web.repository.UserRepository;
import cn.wycode.web.repository.RoleRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 容器启动后执行
 * Created by wayne on 2017/10/26.
 */
@Component
public class RunAfterStarted implements ApplicationRunner {

    private Log log = LogFactory.getLog(RunAfterStarted.class);

    @Autowired
    UserRepository repository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${wycode.user.admin.username}")
    String adminUsername;

    @Value("${wycode.user.admin.password}")
    String adminPassword;

    @Value("${wycode.user.guest.username}")
    String guestUsername;

    @Value("${wycode.user.guest.password}")
    String guestPassword;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info(Arrays.toString(args.getSourceArgs()));
        log.info("container started，running initialize code");
        //填充两个用户 admin guest
        try {
            fillUserData();
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
    }

    private void fillRoleData() {


    }

    private void fillUserData() {
        //添加管理员角色
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        if (adminRole == null) {
            adminRole = roleRepository.save(new Role("ROLE_ADMIN"));
        }
        //添加用户角色
        Role userRole = roleRepository.findByName("ROLE_USER");
        if (userRole == null) {
            userRole = roleRepository.save(new Role("ROLE_USER"));
        }
        //添加游客角色
        Role guestRole = roleRepository.findByName("ROLE_GUEST");
        if (guestRole == null) {
            guestRole = roleRepository.save(new Role("ROLE_GUEST"));
        }
        //添加管理员
        User admin = repository.findByUsername(adminUsername);
        if (admin == null) {
            admin = new User("177****3491", adminUsername, 1, "http://wycode.cn/img/logo_48.png", 27, "1990.05.03", "wangyu@gmail.com", passwordEncoder.encode(adminPassword));
            admin.setRoles(Collections.singletonList(adminRole));
            repository.save(admin);
        }
        //添加游客
        User guest = repository.findByUsername(guestUsername);
        if (guest == null) {
            guest = new User(null, guestUsername, 0, null, 20, "1990.01.01", null, passwordEncoder.encode(guestPassword));
            guest.setRoles(Collections.singletonList(guestRole));
            repository.save(guest);
        }
    }
}
