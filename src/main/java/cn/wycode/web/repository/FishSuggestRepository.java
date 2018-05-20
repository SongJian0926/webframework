package cn.wycode.web.repository;

import cn.wycode.web.entity.FishSuggest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishSuggestRepository extends CrudRepository<FishSuggest, Long> {
}
