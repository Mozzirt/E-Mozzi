package game.mozzi.controller;


import game.mozzi.config.SessionListener;
import game.mozzi.config.response.CommonConstants;
import game.mozzi.config.response.Message;
import game.mozzi.config.response.StatusEnum;
import game.mozzi.domain.entity.Member;
import game.mozzi.dto.MemberDto;
import game.mozzi.dto.RegisterDto;
import game.mozzi.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static game.mozzi.config.Constants.SESSION_NAME;

@RestController
@Slf4j
@RequestMapping("/api/v1/user")
@Api(tags = {"USER API"})
@RequiredArgsConstructor
public class MemberController {

    private final SessionListener sessionListener;
    private final MemberService memberService;

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

    @GetMapping("/current-id")
    @ApiOperation(value = "접속중인유저 조회",notes = "접속중인유저 조회")
    public ResponseEntity<Message> currentUserId(@SessionAttribute(name= SESSION_NAME, required = false) String socialId, Message msg){
        msg.setMessage(StatusEnum.OK, CommonConstants.MZ_99_0001, socialId);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/current-info")
    @ApiOperation(value = "유저정보조회",notes = "유저정보조회")
    public ResponseEntity<Message> findUserBySocialId(@SessionAttribute(name= SESSION_NAME, required = false) String socialId, Message msg){
        return getMemberResponseEntity(socialId, msg);
    }

    @GetMapping("/info/{socialId}")
    @ApiOperation(value = "유저정보조회 (특정유저)",notes = "유저정보조회 (특정유저)")
    public ResponseEntity<Message> findUserByOtherSocialId(@PathVariable String socialId, Message msg){
        return getMemberResponseEntity(socialId, msg);
    }

    /**
     * /member/{socialId}
     * /member
     * @param socialId
     * @param msg
     * @return
     */
    private ResponseEntity<Message> getMemberResponseEntity(@PathVariable String socialId, Message msg) {
        if(socialId == null){
            msg.setMessage(StatusEnum.UNAUTHORIZED, CommonConstants.MZ_00_0005, "");
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
        msg.setMessage(StatusEnum.OK, CommonConstants.MZ_99_0001, memberService.findUserBySocialId(socialId));
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/check-nickname")
    @ApiOperation(value = "닉네임 중복조회",notes = "닉네임 중복조회")
    public ResponseEntity<Message> duplicateNickname(@RequestParam String nickname, Message msg){
        boolean resNickname = memberService.findUserNickname(nickname);
        if(resNickname){
            msg.setMessage(StatusEnum.FOUND, CommonConstants.MZ_00_0007, nickname);
        }else{
            msg.setMessage(StatusEnum.OK, CommonConstants.MZ_00_0006, nickname);
        }
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    // 회원수정
    @PutMapping("/info")
    public ResponseEntity<?> modifyUserInfo(@SessionAttribute(name= SESSION_NAME, required = false) String socialId, @Valid MemberDto memberDto, Message msg) {
        Member userEntity = memberService.modifyInfo(socialId, memberDto);
        msg.setMessage(StatusEnum.OK, CommonConstants.MZ_99_0001, userEntity);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}

