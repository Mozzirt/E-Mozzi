package game.mozzi.domain.entity;

import game.mozzi.domain.BaseTimeEntity;
import game.mozzi.domain.entity.embedded.ExampleList;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class QuestionList extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionListNum;

    @Column
    private String title;

    @Column
    private String size;

    @Column(name = "question_size")
    private String questionSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "socialId")
    private Member socialId;

    @OneToMany(mappedBy = "questionListNum")
    private List<Question> Question;

    @Builder
    public QuestionList(String title, String size){
        this.title = title;
        this.size = size;
    }
}
