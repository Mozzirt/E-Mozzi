package game.mozzi.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  작성자 : rkdwlss2@gmail.com 강명진
 *  QuestionController - 질문리스트
 */

@RestController
@Slf4j
@RequestMapping("/api/v1/question")
@Api(tags = {"QUESTION API"})
@RequiredArgsConstructor
public class QuestionController {

}
