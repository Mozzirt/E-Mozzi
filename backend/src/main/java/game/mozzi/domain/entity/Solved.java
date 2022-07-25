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
public class Solved extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long solvedId;

    @Column
    private String score;

    @Column
    private String time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionListNum")
    private QuestionList questionListNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "socialId")
    private Member socialId;

}
