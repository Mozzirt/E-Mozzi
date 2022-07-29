package game.mozzi.dto;

import game.mozzi.domain.entity.Member;
import game.mozzi.domain.entity.embedded.Role;
import game.mozzi.domain.entity.embedded.SocialType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberDto {

    @ApiModelProperty(value = "닉네임" , example = "nickname")
    private String nickname;

    @NotBlank
    @ApiModelProperty(value = "소셜 ID" , example = "rkdwlss2")
    private String socialId;

    @ApiModelProperty(value = "유저이미지" , example = "유저이미지")
    private String userImage;

    @ApiModelProperty(value = "이메일" , example = "rkdwlss2@gmail.com")
    private String email;

    @ApiModelProperty(value = "소셜타입" , example = "kakao")
    private SocialType socialType;

    @ApiModelProperty(value = "유저권한" , example = "ADMIN")
    private Role role;

    public Member toEntity(){
        return Member.builder()
                .nickname(nickname)
                .email(email)
                .socialType(socialType)
                .role(role)
                .socialId(socialId)
                .userImage(userImage)
                .build();
    }

}
