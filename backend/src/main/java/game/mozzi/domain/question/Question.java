package game.mozzi.domain.question;

import game.mozzi.domain.BaseTimeEntity;
import game.mozzi.domain.user.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Question extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //번호 증가 전략이 데이터 베이스를 따라 갑니다.
    private Long id; // 서비스 하는 프로그램이 아니라서 int로도 충분함

    @Column(name = "title")
    private String title;


    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(name = "password")
    private String password;

    // 이어하기
    @Column(name = "continueDo")
    private String continueDo;

    @JoinColumn(name = "questionlistId")
    @ManyToOne
    private QuestionList questionList;

}
