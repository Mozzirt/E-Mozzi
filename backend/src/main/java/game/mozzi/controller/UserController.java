package game.mozzi.controller;


import game.mozzi.config.SessionListener;
import game.mozzi.config.response.CommonConstants;
import game.mozzi.config.response.Message;
import game.mozzi.config.response.StatusEnum;
import game.mozzi.domain.user.User;
import game.mozzi.dto.UserDto;
import game.mozzi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static game.mozzi.config.Constants.SERVER_URL;
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
    public ResponseEntity<Message> findUserBySocialId(@SessionAttribute(name= SESSION_NAME, required = false) String socialId, Message msg){
        if(socialId == null){
            msg.setMessage(StatusEnum.UNAUTHORIZED, CommonConstants.MZ_00_0005, "");
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
        msg.setMessage(StatusEnum.OK, CommonConstants.MZ_99_0001, userService.findUserBySocialId(socialId));
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

}

