package cn.wycode.web.repository;

import cn.wycode.web.entity.FishBaike;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FishBaikeRepository extends CrudRepository<FishBaike, Long> {
    List<FishBaike> findByTypeOrderByReadCountDesc(String type);
}
