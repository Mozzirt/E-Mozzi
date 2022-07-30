package game.mozzi.domain.entity;

import game.mozzi.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Mission extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long missionNum;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String point;

    @OneToMany(mappedBy = "attainmentNum")
    private List<Attainment> attainments;

    @Builder
    public Mission(String title, String content, String point){
        this.title = title;
        this.content = content;
        this.point = point;
    }
}
