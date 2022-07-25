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
public class reaction extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reactionNum;

    @OneToMany(mappedBy = "socialId")
    private List<Member> members;

    @OneToMany(mappedBy = "commentNum")
    private List<Comment> comments;

}
