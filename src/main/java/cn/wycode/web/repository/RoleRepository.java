package cn.wycode.web.repository;

import cn.wycode.web.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * Created by wayne on 2017/10/25.
 */
@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {

    Role findByName(String name);
}
