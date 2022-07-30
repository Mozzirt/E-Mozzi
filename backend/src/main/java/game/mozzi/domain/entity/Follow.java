package game.mozzi.domain.entity;

import game.mozzi.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Follow extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followNum;

    @Column
    private String follower;

    @Column
    private String following;

    @Builder
    public Follow(String follower, String following){
        this.follower = follower;
        this.following = following;
    }
}
