package game.mozzi.domain.question;

import game.mozzi.domain.BaseTimeEntity;
import game.mozzi.domain.user.User;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// table 생성됨 ORM OBject 기준으로 테이블 만들어짐
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity // DB에 테이블을 생성 해줌
public class QuestionList extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //번호 증가 전략이 데이터 베이스를 따라 갑니다.
    private Long id; // 서비스 하는 프로그램이 아니라서 int로도 충분함

    // 질문 리스트 명
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "questionList",fetch = FetchType.LAZY)
    private List<Question> questions;

    @Column(name = "content1")
    private String content1;

    @Column(name = "content2")
    private String content2;

    @Column(name = "content3")
    private String content3;

    @Column(name = "content4")
    private String content4;

    @Column(name = "content5")
    private String content5;

    @Column(name = "content6")
    private String content6;

    @Column(name = "content7")
    private String content7;

    @Column(name = "picture")
    private String picture;

}
