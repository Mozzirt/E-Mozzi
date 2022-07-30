package game.mozzi.domain.entity;

import game.mozzi.domain.BaseTimeEntity;
import game.mozzi.domain.entity.embedded.ExampleList;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Question extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionNum;

    @Column(name = "image_url")
    private String imageUrl;

    @Embedded
    private ExampleList contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionListNum")
    private QuestionList questionListNum;

    @Builder
    public Question(String imageUrl, ExampleList contents){
        this.imageUrl = imageUrl;
        this.contents = contents;
    }
}
