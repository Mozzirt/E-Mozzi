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
public class Hidden extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hiddenNum;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String point;

    @OneToMany(mappedBy = "attainmentNum")
    private List<Attainment> attainments;
}
