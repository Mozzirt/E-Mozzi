package game.mozzi.config.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}