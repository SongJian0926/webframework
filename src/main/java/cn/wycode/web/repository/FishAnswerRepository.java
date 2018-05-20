package cn.wycode.web.repository;

import cn.wycode.web.entity.FishQuestionAnswer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FishAnswerRepository extends PagingAndSortingRepository<FishQuestionAnswer,Long> {
    List<FishQuestionAnswer> findAllByQuestion_IdOrderByValueDesc(Long questionId);
}
