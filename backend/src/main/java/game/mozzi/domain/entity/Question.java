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

    @Column(name = "image_url2")
    private String imageUrl2;

    @Column(name = "image_url3")
    private String imageUrl3;

    @Column(name = "image_url4")
    private String imageUrl4;

    @Column(name = "image_url5")
    private String imageUrl5;

    @Column(name = "image_url6")
    private String imageUrl6;

    @Column(name = "image_url7")
    private String imageUrl7;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionListNum")
    private QuestionList questionListNum;
}
