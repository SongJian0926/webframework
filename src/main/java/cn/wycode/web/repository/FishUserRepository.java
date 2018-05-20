package cn.wycode.web.repository;

import cn.wycode.web.entity.FishUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishUserRepository extends CrudRepository<FishUser,Long>{
    FishUser findByOpenId(String openId);
    FishUser findByKey(String accessKey);

}
