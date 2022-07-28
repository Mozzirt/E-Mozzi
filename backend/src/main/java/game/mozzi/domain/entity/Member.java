package game.mozzi.domain.entity;

import game.mozzi.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mmbrNum;

    @Column(name = "social_id")
    private String socialId;

    @Column(name="user_image")
    private String userImage;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private String nickname;

    @Column
    private String role;

    //@OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    @OneToOne(mappedBy = "member")
    private Mileage mileage;

    @OneToMany(mappedBy = "socialId")
    private List<Solved> solved;

    @OneToMany(mappedBy = "questionListNum")
    private List<QuestionList> questionLists;
}
