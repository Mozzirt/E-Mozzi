package game.mozzi.domain.entity;

import game.mozzi.domain.BaseTimeEntity;
import game.mozzi.domain.entity.embedded.Role;
import game.mozzi.domain.entity.embedded.SocialType;
import game.mozzi.dto.MemberDto;
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

    @Column(name="social_type")
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private Mileage mileage;

    @OneToMany(mappedBy = "socialId")
    private List<Solved> solved;

    @OneToMany(mappedBy = "questionListNum")
    private List<QuestionList> questionLists;

    public void updateMember(MemberDto memberDto){
        this.userImage = memberDto.getUserImage();
        this.email = memberDto.getEmail();
        this.phone = memberDto.getPhone();
        this.nickname = memberDto.getNickname();
    }
}
