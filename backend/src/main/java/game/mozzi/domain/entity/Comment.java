package game.mozzi.domain.entity;

import game.mozzi.domain.BaseTimeEntity;
import lombok.*;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentNum;

    @Column
    private String title;

    @Column
    private String content;

    @OneToMany(mappedBy = "reportNum")
    private List<Report> reports;

    @Builder
    public Comment(String title, String content){
        this.title = title;
        this.content = content;
    }
}
