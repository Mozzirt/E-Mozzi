package game.mozzi.service;

import game.mozzi.domain.entity.Member;
import game.mozzi.domain.entity.QuestionList;
import game.mozzi.domain.repository.MemberInfoRepository;
import game.mozzi.domain.repository.QuestionListRepository;
import game.mozzi.dto.MemberDto;
import game.mozzi.dto.RegisterDto;
import game.mozzi.handler.ex.CustomValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QuestionListService {

    private final QuestionListRepository questionListRepository;

    /**
     * 질문 리스트 작성
     * @param questionList
     */
    @Transactional
    public void makeQuestionList(QuestionList questionList){
        questionListRepository.save(questionList);
    }


}
