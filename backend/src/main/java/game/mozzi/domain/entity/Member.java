package game.mozzi.domain.entity;

import game.mozzi.domain.BaseTimeEntity;
import game.mozzi.domain.entity.embedded.Role;
import game.mozzi.domain.entity.embedded.SocialType;
import game.mozzi.dto.MemberDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
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

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true )
    private Mileage mileage;

    @OneToMany(mappedBy = "socialId")
    private List<Solved> solved;

    @OneToMany(mappedBy = "questionListNum")
    private List<QuestionList> questionLists;

    public void update(MemberDto memberDto){
        this.userImage = memberDto.getUserImage();
        this.email = memberDto.getEmail();
        this.phone = memberDto.getPhone();
        this.nickname = memberDto.getNickname();
    }

    @Builder
    public Member(String socialId, String userImage, SocialType socialType, String email, String phone, String nickname, Role role){
        this.socialId = socialId;
        this.userImage = userImage;
        this.socialType = socialType;
        this.email = email;
        this.phone = phone;
        this.nickname = nickname;
        this.role = role;
    }
}
