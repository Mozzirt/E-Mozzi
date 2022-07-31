package game.mozzi.controller;

import game.mozzi.domain.entity.QuestionList;
import game.mozzi.dto.CMRespDto;
import game.mozzi.service.QuestionListService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *  작성자 : rkdwlss2@gmail.com 강명진
 *  QuestionController - 질문리스트
 */

@RestController
@Slf4j
@RequestMapping("/api/v1/question_list")
@Api(tags = {"QUESTION LIST API"})
@RequiredArgsConstructor
public class QuestionListController {

    private final QuestionListService questionListService;

    @PostMapping("/insert")
    public ResponseEntity<?> insertQuestionList(@RequestBody QuestionList questionList){
        questionListService.makeQuestionList(questionList);
        return new ResponseEntity<>(new CMRespDto<>(1,"좋아요취소성공",null), HttpStatus.OK);
    }
}
