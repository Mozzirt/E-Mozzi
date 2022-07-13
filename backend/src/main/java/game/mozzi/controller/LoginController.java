package game.mozzi.controller;


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
import java.util.UUID;

import static game.mozzi.config.Constants.SERVER_URL;
import static game.mozzi.config.Constants.SESSION_NAME;

@RestController
@Slf4j
@RequestMapping(value = "/member/v1")
@Api(tags = {"회원가입 API"})
@RequiredArgsConstructor
public class LoginController {


    @Value("${kakao.rest}")
    private String kakao_rest;
    @Value("${naver.client}")
    private String naver_client;
    @Value("${naver.secret}")
    private String naver_secret;
    private final UserService userService;

    // 네이버 로그인 state 난수 생성
    public String generateState()
    {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

    // 로그인 분기
    @GetMapping("/login/{with}")
    public RedirectView MainLogin(@PathVariable("with") String login_with){
        RedirectView rv = new RedirectView();
        switch (login_with){
            case "guest":
                rv.setUrl("https://santa-community.co.kr/auth/test/callback");
                break;
            case "kakao":
                rv.setUrl("https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="+ kakao_rest +"&redirect_uri="+SERVER_URL+"/auth/kakao/callback");
                break;
            case "naver":
                String state = generateState();
                rv.setUrl("https://nid.naver.com/oauth2.0/authorize?client_id="+ naver_client +"&response_type=code&redirect_uri="+SERVER_URL+"/auth/naver/callback&state="+ state);
                break;
            default:
                log.debug("############## MainLogin.login error");
        }
        return rv;
    }

    /**
     * GUEST 로그인
     */
    @RequestMapping(value = "auth/test/callback")
    public String guestLogin(HttpServletRequest request, Model model){
        String guestUUID = UUID.randomUUID().toString();
        // TODO : 게스트 로그인로직 구현
        return "redirect:/";
    }


    /**
     * 작성자 : beomchul.kim@lotte.net
     * 카카오 로그인
     */
    @RequestMapping(value = "auth/kakao/callback")
    public String KakaoLogin(@RequestParam("code") String code ,HttpServletRequest request, Model model){


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

        // TODO : 회원가입여부 조회 후 회원가입로직 or 로그인로직

        return "redirect:/";
    }


    /**
     * 작성자 : beomchul.kim@lotte.net
     * 네이버 로그인
     */
    @RequestMapping(value = "auth/naver/callback")
    public String NaverLogin(@RequestParam("code") String code, @RequestParam("state") String state, HttpServletRequest request, Model model){

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

        // TODO : 회원가입여부 조회 후 회원가입로직 or 로그인로직

        return "redirect:/";
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
        return "redirect:/";
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
            return "redirect:/";
        }

        HttpSession session = request.getSession(false);

        // 세션존재시 세션제거
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }


    @ApiOperation(value = "회원가입",notes = "회원가입 api",response = User.class)
    @PostMapping(value = "/join")
    public ResponseEntity<?> signUp(@Valid UserDto userDto) {
        User user = userDto.toEntity();
        User userEntity = userService.join(user);
        return new ResponseEntity<>(userEntity, HttpStatus.OK); // 회원가입 성공했을 경우 http status code 200 전달
    }

}
