package game.mozzi.dto;

import game.mozzi.domain.entity.Member;
import game.mozzi.domain.entity.embedded.Role;
import game.mozzi.domain.entity.embedded.SocialType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberDto {

    @NotBlank
    @ApiModelProperty(value = "닉네임" , example = "nickname")
    private String nickname;

    @ApiModelProperty(value = "유저이미지" , example = "유저이미지")
    private String userImage;

    @ApiModelProperty(value = "이메일" , example = "rkdwlss2@gmail.com")
    private String email;
    
    @ApiModelProperty(value = "휴대전화" , example = "010-1234-5678")
    private String phone;

    public Member toEntity(){
        return Member.builder()
                .nickname(nickname)
                .email(email)
                .phone(phone)
                .userImage(userImage)
                .build();
    }

}
