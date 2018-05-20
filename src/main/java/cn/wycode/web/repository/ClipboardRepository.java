package cn.wycode.web.repository;

import cn.wycode.web.entity.Clipboard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClipboardRepository extends CrudRepository<Clipboard,Long> {
    Clipboard findTopByOrderByIdDesc();
}
