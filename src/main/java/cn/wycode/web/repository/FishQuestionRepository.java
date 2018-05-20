package cn.wycode.web.repository;

import cn.wycode.web.entity.FishQuestion;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FishQuestionRepository extends PagingAndSortingRepository<FishQuestion, Long> {
    List<FishQuestion> findByOrderByUpdateTimeDesc();
}
