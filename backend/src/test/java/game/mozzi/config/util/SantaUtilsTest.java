package game.mozzi.config.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SantaUtilsTest {


    // 암복호화 테스트
    @Test
    public void encrypt() throws Exception {
        String encryptData = SantaUtils.encrypt("kimbeomchul");
        System.out.println("encryptData = " + encryptData);

        String decryptData = SantaUtils.decrypt(encryptData);

        //복호화 검증
        assertEquals("kimbeomchul", decryptData);
        System.out.println("decryptData = " + decryptData);
    }

}