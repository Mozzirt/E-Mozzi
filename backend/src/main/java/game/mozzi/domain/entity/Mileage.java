package game.mozzi.domain.entity;

import game.mozzi.domain.BaseTimeEntity;
import game.mozzi.domain.entity.embedded.Role;
import game.mozzi.domain.entity.embedded.SocialType;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Mileage extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mileageNum;

    @Column
    private String mileage;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "socialId")
    private Member member;

    @Builder
    public Mileage(String mileage){
        this.mileage = mileage;
    }
}
