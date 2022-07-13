package game.mozzi.dto;

import game.mozzi.domain.user.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserDto {

    @NotBlank
    @ApiModelProperty(value = "유저 이름" , example = "rkdwlss2")
    private String username;

    @NotBlank
    @ApiModelProperty(value = "비밀번호" , example = "1234")
    private String password;

    @NotBlank
    @ApiModelProperty(value = "이메일" , example = "rkdwlss2@gmail.com")
    private String email;

    @NotBlank
    @ApiModelProperty(value = "이름" , example = "kangmyoungjin")
    private String name;

    @ApiModelProperty(value = "성별" , example = "남/여")
    private String gender;

    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .name(name)
                .gender(gender)
                .build();
    }

}