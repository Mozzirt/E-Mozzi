package game.mozzi.domain.entity.embedded;

import javax.persistence.Embeddable;


/**
 * 복합값타입
 * 작성자 : beomchul.kim@lotte.net
 * 1. 질문리스트엔 여러개의 질문이 존재한다
 * 2. 질문에는 여러개의 보기가 존재한다
 */
@Embeddable
public class ExampleList {

    private String content1;
    private String content2;
    private String content3;
    private String content4;
    private String content5;
    private String content6;
    private String content7;

}
