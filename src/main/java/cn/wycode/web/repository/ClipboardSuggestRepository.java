package cn.wycode.web.repository;

import cn.wycode.web.entity.ClipboardSuggest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClipboardSuggestRepository extends CrudRepository<ClipboardSuggest, Long> {
}
