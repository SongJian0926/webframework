package cn.wycode.web.repository;

import cn.wycode.web.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wayne on 2017/10/24.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
