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
public class Report extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportNum;

    @Column
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "socialId")
    private Member socialId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commentNum")
    private Comment comment;
}
