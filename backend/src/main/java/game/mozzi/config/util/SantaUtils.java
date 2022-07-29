package game.mozzi.config.util;

import game.mozzi.domain.entity.Member;
import game.mozzi.domain.entity.embedded.Role;
import game.mozzi.domain.repository.MemberInfoRepository;
import game.mozzi.service.MemberService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 작성자 : beomchul.kim@lotte.com
 * ETC Utils
 * 이해안가면 웹엑스주세염
 */

@RequiredArgsConstructor
@Controller
@Slf4j
public class SantaUtils {

    private final MemberService memberService;
    public static String aesAlgorithm = "AES/CBC/PKCS5Padding";
    private static final String secretKey = "imsiimsiimsiimsi";
    private static final String iv = secretKey.substring(0, 16); // 16byte


    /**
     * 데이터 암호화
     * @param text
     * @return
     * @throws Exception
     */
    public static String encrypt(String text) throws Exception {
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
    public static String decrypt(String cipherText) throws Exception {
        Cipher cipher = Cipher.getInstance(aesAlgorithm);
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

        byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
        byte[] decrypted = cipher.doFinal(decodedBytes);
        return new String(decrypted, "UTF-8");
    }


    /**
     * JPA Util
     * JPA ID 널체크 후 존재시 Update 없을시 Insert 처리 (분기최적화용) -> pk값으로 찾을경우 가능
     * 사용 ) Member member = memberRepository.findById(member.getSocialId).orElseGet(Member::new);
     * 사용 ) SantaUtils.jpaSaveUpdate(member.getSocialId, memberRepository, member);
     * @param id
     * @param repo
     * @param entity
     */
    public static void jpaSaveUpdate(Long id, JpaRepository repo, Object entity) {
        if (id == null || id.equals("")) {
            repo.save(entity);
        }
    }

    /**
     * isAdmin Util
     * ID 체크 후 존재시 ROLE이 사용자 권한인지 관리자 권한인지 Boolean형식으로 반환 관리자 계정이면 TRUE, 계정이 없거나, USER권한이면 false반환
     * @param socialId
     */
    public boolean isAdmin(String socialId) {
        if (socialId != null && !socialId.equals("")) {
            Member member = memberService.findUserBySocialId(socialId);
            if (member.getRole() == Role.ADMIN){
                return true;
            }
        }
        return false;
    }
}
