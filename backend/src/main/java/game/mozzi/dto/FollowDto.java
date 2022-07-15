package game.mozzi.dto;

import game.mozzi.domain.user.Follow;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FollowDto {

    @NotBlank
    @ApiModelProperty(value = "팔로워" , example = "rkdwlss2")
    private String follower;

    @NotBlank
    @ApiModelProperty(value = "팔로잉" , example = "beomchul")
    private String following;

    public Follow toEntity(){
        return Follow.builder()
                .follower(follower)
                .following(following)
                .build();
    }

}
