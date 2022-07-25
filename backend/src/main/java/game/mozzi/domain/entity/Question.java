package game.mozzi.domain.entity;

import game.mozzi.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Question extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionNum;

    @Column
    private String content1;

    @Column
    private String content2;

    @Column
    private String content3;

    @Column
    private String content4;

    @Column
    private String content5;

    @Column
    private String content6;

    @Column
    private String content7;

    @Column(name = "image_url1")
    private String imageUrl1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionListNum")
    private QuestionList questionListNum;
}
