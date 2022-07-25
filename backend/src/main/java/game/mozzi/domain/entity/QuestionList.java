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
public class QuestionList extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionListNum;

    @Column
    private String title;

    @Column
    private String time;

    @Column
    private String score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "socialId")
    private Member socialId;

    @OneToMany(mappedBy = "questionListNum")
    private List<Question> Question;
}
