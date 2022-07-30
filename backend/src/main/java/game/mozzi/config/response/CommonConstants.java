package game.mozzi.config.response;


/**
 * 작성자 : beomchul.kim@lotte.com
 * - CommonConstants
 * 규칙 MZ_xx_yyyy
 * xx : 모듈
 * yy : 순번
 * 모듈 = {
 *     00 : 회원
 *     01 :
 *     02 :
 *     03 :
 *     99 : 공통
 * }
 */
public final class CommonConstants {

    // 공통
    public static final String MZ_99_0001 = "성공";
    public static final String MZ_99_0002 = "실패";
    public static final String MZ_99_0003 = "크기초과";
    public static final String MZ_99_0004 = "데이터가 존재하지 않습니다";

    // 회원
    public static final String MZ_00_0001 = "로그인";
    public static final String MZ_00_0002 = "회원가입";
    public static final String MZ_00_0003 = "회원가입 실패";
    public static final String MZ_00_0004 = "게스트 로그인";
    public static final String MZ_00_0005 = "권한이 없습니다";
    public static final String MZ_00_0006 = "사용가능한 닉네임입니다";
    public static final String MZ_00_0007 = "이미 사용중인 닉네임입니다";



}
