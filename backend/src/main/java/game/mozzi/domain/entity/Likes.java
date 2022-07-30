package game.mozzi.domain.entity;

import game.mozzi.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Likes extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likesNum;

    @OneToMany(mappedBy = "socialId")
    private List<Member> members;

    @OneToMany(mappedBy = "commentNum")
    private List<Comment> comments;

}
