package game.mozzi.controller;


import game.mozzi.config.response.CommonConstants;
import game.mozzi.config.response.Message;
import game.mozzi.config.response.StatusEnum;
import game.mozzi.config.util.RandomUtils;
import game.mozzi.domain.entity.Member;
import game.mozzi.domain.entity.embedded.Role;
import game.mozzi.domain.entity.embedded.SocialType;
import game.mozzi.dto.MemberDto;
import game.mozzi.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
import java.util.NoSuchElementException;
import java.util.UUID;

import static game.mozzi.config.Constants.SERVER_URL;
import static game.mozzi.config.Constants.SESSION_NAME;


/**
 *  작성자 : beomchul.kim@lotte.com
 *  Social Login
 *  todo : GUEST 로그인시 UUID '-' 값존재 , 카카오 소셜아이디 -> 숫자 , 네이버 소셜아이디 -> '-' 랜덤으로 존재 따라서 정규식처리? replaceAll처리? ..
 */

@RestController
@Slf4j
@RequestMapping()
@Api(tags = {"회원가입/로그인 API"})
@RequiredArgsConstructor
public class LoginController {


    @Value("${kakao.rest}")
    private String kakao_rest;
    @Value("${naver.client}")
    private String naver_client;
    @Value("${naver.secret}")
    private String naver_secret;
    private final MemberService userService;


    // 로그인 분기
    @GetMapping("/login/{with}")
    public RedirectView MainLogin(@PathVariable("with") String login_with){
        RedirectView rv = new RedirectView();
        switch (login_with){
            case "guest":
                rv.setUrl("http://localhost:8080/auth/test/callback");
                break;
            case "kakao":
                rv.setUrl("https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="+ kakao_rest +"&redirect_uri="+SERVER_URL+"/auth/kakao/callback");
                break;
            case "naver":
                String state = RandomUtils.generateState();
                rv.setUrl("https://nid.naver.com/oauth2.0/authorize?client_id="+ naver_client +"&response_type=code&redirect_uri="+SERVER_URL+"/auth/naver/callback&state="+ state);
                break;
            default:
                log.info("############## MainLogin.login error : urlPath =>  ", login_with);
        }
        return rv;
    }

    /**
     * GUEST 로그인
     */
    @RequestMapping(value = "auth/test/callback")
    public ResponseEntity<Message> guestLogin(HttpServletRequest request){
        Message msg = new Message();
        String guestUUID = UUID.randomUUID().toString();
        MemberDto memberDto = new MemberDto();
        memberDto.setSocialId(guestUUID);
        memberDto.setRole(Role.GUEST);
        memberDto.setSocialType(SocialType.GUEST);
        this.signUp(memberDto);

        HttpSession session = request.getSession(true);
        session.setAttribute(SESSION_NAME, guestUUID);
        msg.setMessage(StatusEnum.OK, CommonConstants.MZ_00_0004, guestUUID);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }


    /**
     * 작성자 : beomchul.kim@lotte.com
     * 카카오 로그인
     */
    @RequestMapping(value = "auth/kakao/callback")
    public ResponseEntity<Message> KakaoLogin(@RequestParam("code") String code ,HttpServletRequest request, Model model){
        Message msg = new Message();
        RestTemplate rt = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("client_id", kakao_rest);
        params.add("redirect_uri",SERVER_URL + "/auth/kakao/callback");
        params.add("code",code);

        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = new HttpEntity<>(params,httpHeaders);

        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        // 토큰값 Json 형식으로 가져오기위해 생성
        JSONObject jo = new JSONObject(response.getBody());

        // 토큰결과값
        log.debug("kakao token result = {} " , response);

        RestTemplate rt2 = new RestTemplate();
        HttpHeaders headers2 = new HttpHeaders();

        headers2.add("Authorization", "Bearer "+ jo.get("access_token"));
        headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String,String >> kakaoProfileRequest2= new HttpEntity<>(headers2);

        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest2,
                String.class
        );

        // 토큰을 사용하여 사용자 정보 추출
        JSONObject jo2 = new JSONObject(response2.getBody());
        log.debug("###### kakao login = {}", jo2);

        boolean userYsno = userService.findUser(String.valueOf(jo2.get("id")));

        // 회원인경우
        if(userYsno){
            HttpSession session = request.getSession(true);
            session.setAttribute(SESSION_NAME, String.valueOf(jo2.get("id")));
            msg.setMessage(StatusEnum.OK, CommonConstants.MZ_00_0001, String.valueOf(jo2.get("id")));
        }else {
            try {
                MemberDto memberDto = new MemberDto();
                memberDto.setSocialId(String.valueOf(jo2.get("id")));
                memberDto.setUserImage(String.valueOf(jo2.getJSONObject("properties").get("profile_image")));
                memberDto.setRole(Role.NORMAL);
                memberDto.setSocialType(SocialType.KAKAO);
                this.signUp(memberDto);

                HttpSession session = request.getSession(true);
                session.setAttribute(SESSION_NAME, String.valueOf(jo2.get("id")));
                msg.setMessage(StatusEnum.OK, CommonConstants.MZ_00_0002, String.valueOf(jo2.get("id")));
            } catch (NoSuchElementException e) {
                log.info("#### Naver login Err = {} ", e);
                msg.setMessage(StatusEnum.BAD_REQUEST, CommonConstants.MZ_00_0003, String.valueOf(jo2.get("id")));
            }
        }
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }


    /**
     * 작성자 : beomchul.kim@lotte.com
     * 네이버 로그인
     */
    @RequestMapping(value = "auth/naver/callback")
    public ResponseEntity<Message> NaverLogin(@RequestParam("code") String code, @RequestParam("state") String state, HttpServletRequest request, Model model){
        Message msg = new Message();
        RestTemplate rt = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("client_id", naver_client);
        params.add("client_secret", naver_secret);
        params.add("grant_type", "authorization_code");
        params.add("state", state);  // state 일치를 확인
        params.add("code", code);

        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = new HttpEntity<>(params,httpHeaders);

        ResponseEntity<String> response = rt.exchange(
                "https://nid.naver.com/oauth2.0/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        // 토큰값 Json 형식으로 가져오기위해 생성
        JSONObject jo = new JSONObject(response.getBody());

        // 토큰결과값
        log.debug("naver Id token result = {} " ,  response);

        RestTemplate rt2 = new RestTemplate();
        HttpHeaders headers2 = new HttpHeaders();

        headers2.add("Authorization", "Bearer "+ jo.get("access_token"));
        headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String,String >> kakaoProfileRequest2= new HttpEntity<>(headers2);

        ResponseEntity<String> response2 = rt2.exchange(
                "https://openapi.naver.com/v1/nid/me",
                HttpMethod.POST,
                kakaoProfileRequest2,
                String.class
        );

        // 토큰을 사용하여 사용자 정보 추출
        JSONObject jo2 = new JSONObject(response2.getBody());

        log.debug("##### naver login = {}" , jo2);
        log.info(String.valueOf(jo2.getJSONObject("response").get("id")));

        boolean userYsno = userService.findUser(String.valueOf(jo2.getJSONObject("response").get("id")));

        // 회원인경우
        if(userYsno){
            HttpSession session = request.getSession(true);
            session.setAttribute(SESSION_NAME, String.valueOf(jo2.getJSONObject("response").get("id")));
            msg.setMessage(StatusEnum.OK, CommonConstants.MZ_00_0001, String.valueOf(jo2.getJSONObject("response").get("id")));
        }else{
            try{
                MemberDto memberDto = new MemberDto();
                memberDto.setSocialId(String.valueOf(jo2.getJSONObject("response").get("id")));
                memberDto.setUserImage(String.valueOf(jo2.getJSONObject("response").get("profile_image")));
                memberDto.setEmail(String.valueOf(jo2.getJSONObject("response").get("email")));
                memberDto.setRole(Role.NORMAL);
                memberDto.setSocialType(SocialType.NAVER);
                this.signUp(memberDto);

                HttpSession session = request.getSession(true);
                session.setAttribute(SESSION_NAME, String.valueOf(jo2.getJSONObject("response").get("id")));
                msg.setMessage(StatusEnum.OK, CommonConstants.MZ_00_0002, String.valueOf(jo2.getJSONObject("response").get("id")));
            }catch(NoSuchElementException e){
                log.info("#### Naver login Err = {} ", e);
                msg.setMessage(StatusEnum.BAD_REQUEST, CommonConstants.MZ_00_0003, String.valueOf(jo2.getJSONObject("response").get("id")));
            }

        }
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    /**
     * 세션 로그아웃
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);

        // 세션존재시 세션제거
        if(session != null){
            session.invalidate();
        }
        return "ok";
    }

    /**
     * 게스트 세션로그아웃
     * @param socialId
     * @param request
     * @return
     */
    @GetMapping("/guest/logout")
    public String guestLogout(@SessionAttribute(name= SESSION_NAME, required = false) String socialId, HttpServletRequest request) {

        if(socialId.replaceAll("-","").equals(socialId)){
            return "guest";
        }

        HttpSession session = request.getSession(false);

        // 세션존재시 세션제거
        if (session != null) {
            session.invalidate();
        }
        return "ok";
    }


    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid MemberDto memberDto) {
        Member member = memberDto.toEntity();
        Member userEntity = userService.join(member);
        return new ResponseEntity<>(userEntity, HttpStatus.OK); // 회원가입 성공했을 경우 http status code 200 전달
    }

}


