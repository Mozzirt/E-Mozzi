package game.mozzi.domain.entity;

import game.mozzi.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Attainment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attainmentNum;

    @Column
    private Long point;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "socialId")
    private Member socialId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "missionId")
    private Mission missionId;

    @Builder
    public Attainment(Long point){
        this.point = point;
    }

}
