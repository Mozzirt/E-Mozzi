package game.mozzi.service;

import game.mozzi.domain.user.User;
import game.mozzi.domain.repository.UserInfoRepository;
import game.mozzi.handler.ex.CustomValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserInfoRepository userInfoRepository;

    /**
     * 회원가입
     * @param user
     * @return
     */
    @Transactional
    public User join(User user){

        if (userInfoRepository.existsBySocialId(user.getSocialId())){
            throw new CustomValidationException("이미 존재하는 소셜아이디 입니다");
        }
        User userEntity = userInfoRepository.save(user);
        return userEntity;
    }

    /**
     * 회원여부 찾기
     * @param socialId
     * @return
     */
    public boolean findUser(String socialId){
        return userInfoRepository.existsBySocialId(socialId);
    }

    /**
     * 회원조회 ( 소셜아이디 )
     * @param socialId
     * @return
     */
    public User findUserBySocialId(String socialId){
        return userInfoRepository.findBySocialId(socialId);
    }

}
