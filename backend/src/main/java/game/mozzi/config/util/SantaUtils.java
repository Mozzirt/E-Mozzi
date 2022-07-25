package game.mozzi.config.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


/**
 * 작성자 : beomchul.kim@lotte.net
 * ETC Utils
 */

@Slf4j
@PropertySource("classpath:application.properties")
public class SantaUtils {


    public static String aesAlgorithm = "AES/CBC/PKCS5Padding";
    private final String secretKey = "imsiimsiimsiimsi";
    private final String iv = secretKey.substring(0, 16); // 16byte


    /**
     * 데이터 암호화
     * @param text
     * @return
     * @throws Exception
     */
    public String encrypt(String text) throws Exception {
        Cipher cipher = Cipher.getInstance(aesAlgorithm);
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

        byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encrypted);
    }


    /**
     * 데이터 복호화
     * @param cipherText
     * @return
     * @throws Exception
     */
    public String decrypt(String cipherText) throws Exception {
        Cipher cipher = Cipher.getInstance(aesAlgorithm);
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

        byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
        byte[] decrypted = cipher.doFinal(decodedBytes);
        return new String(decrypted, "UTF-8");
    }


}
