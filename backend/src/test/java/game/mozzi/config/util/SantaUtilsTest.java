package game.mozzi.config.util;

import game.mozzi.config.response.Message;
import game.mozzi.controller.LoginController;
import game.mozzi.domain.entity.Member;
import game.mozzi.dto.MemberDto;
import game.mozzi.service.MemberService;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
class SantaUtilsTest {


    // 암복호화 테스트
    @Test
    public void encrypt() throws Exception {
        SantaUtils su = new SantaUtils();
        String encryptData = su.encrypt("kimbeomchul");
        System.out.println("encryptData = " + encryptData);

        String decryptData = su.decrypt(encryptData);

        //복호화 검증
        assertEquals("kimbeomchul", decryptData);
        System.out.println("decryptData = " + decryptData);
    }

    @Mock
    private LoginController loginController;

    @InjectMocks
    private MemberService memberService;

    MockHttpServletRequest servletRequest;
    // 유저 권한 체크
    @Test
    public void idAdminCheck() throws Exception {
        // given
        MockHttpServletRequest req =new MockHttpServletRequest();
        ResponseEntity<Message> responseEntity = loginController.guestLogin(req);
        String socialId = responseEntity.getBody().getMessage();
        System.out.println(socialId);
//        SantaUtils su = new SantaUtils();
        // when
//        Boolean answer = su.idAdminCheck("")
        // then
    }
}
