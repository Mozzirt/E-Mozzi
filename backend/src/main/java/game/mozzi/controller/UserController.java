package game.mozzi.controller;


import game.mozzi.config.SessionListener;
import game.mozzi.config.response.CommonConstants;
import game.mozzi.config.response.Message;
import game.mozzi.config.response.StatusEnum;
import game.mozzi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import static game.mozzi.config.Constants.SESSION_NAME;

@RestController
@Slf4j
@RequestMapping("/api/v1/user")
@Api(tags = {"USER API"})
@RequiredArgsConstructor
public class UserController {

    private final SessionListener sessionListener;
    private final UserService userService;

    @GetMapping("/session/count")
    @ApiOperation(value = "로그인유저 수 조회",notes = "로그인유저 수 조회")
    public ResponseEntity<Message> currentLoginUserCnt(Message msg){
        msg.setMessage(StatusEnum.OK, CommonConstants.MZ_99_0001, sessionListener.getTotalUser());
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/session/id")
    @ApiOperation(value = "로그인유저 아이디조회",notes = "로그인유저 아이디조회")
    public ResponseEntity<Message> currentLoginUserId(Message msg){
        msg.setMessage(StatusEnum.OK, CommonConstants.MZ_99_0001, sessionListener.getLoginList());
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/find/login")
    @ApiOperation(value = "접속중인유저 조회",notes = "접속중인유저 조회")
    public ResponseEntity<Message> currentUserId(@SessionAttribute(name= SESSION_NAME, required = false) String socialId, Message msg){
        msg.setMessage(StatusEnum.OK, CommonConstants.MZ_99_0001, socialId);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/find")
    @ApiOperation(value = "유저정보조회",notes = "유저정보조회")
    public ResponseEntity<Message> findUserBySocialId(@SessionAttribute(name= SESSION_NAME, required = false) String socialId, @PageableDefault() Pageable pageable, Message msg){
        if(socialId == null){
            msg.setMessage(StatusEnum.UNAUTHORIZED, CommonConstants.MZ_00_0005, "");
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
        msg.setMessage(StatusEnum.OK, CommonConstants.MZ_99_0001, userService.findUserBySocialId(socialId,pageable));
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

}

