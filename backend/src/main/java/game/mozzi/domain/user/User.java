package game.mozzi.domain.user;

import game.mozzi.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// table 생성됨 ORM OBject 기준으로 테이블 만들어짐
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity // DB에 테이블을 생성 해줌
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //번호 증가 전략이 데이터 베이스를 따라 갑니다.
    private Long userNum;

    @Column(name = "socialId")
    private String socialId;

    @Column(name = "email")
    private String email;

    @Column(name="userImage")
    private String userImage;

    @Column(name = "nickname")
    private String nickname;


}
