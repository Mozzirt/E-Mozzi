package game.mozzi.dto;

import game.mozzi.domain.user.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserDto {

    @ApiModelProperty(value = "닉네임" , example = "nickname")
    private String nickname;

    @NotBlank
    @ApiModelProperty(value = "소셜 ID" , example = "rkdwlss2")
    private String socialId;

    @ApiModelProperty(value = "유저이미지" , example = "유저이미지")
    private String userImage;


    @ApiModelProperty(value = "이메일" , example = "rkdwlss2@gmail.com")
    private String email;


    public User toEntity(){
        return User.builder()
                .nickname(nickname)
                .email(email)
                .socialId(socialId)
                .userImage(userImage)
                .build();
    }

}
