package game.mozzi.service;

import game.mozzi.domain.user.User;
import game.mozzi.domain.user.UserInfoRepository;
import game.mozzi.handler.ex.CustomValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserInfoRepository userInfoRepository;

    @Transactional
    public User join(User user){

        if (userInfoRepository.existsBySocialId(user.getSocialId())){
            throw new CustomValidationException("이미 존재하는 소셜아이디 입니다");
        }
        User userEntity = userInfoRepository.save(user);
        return userEntity;
    }
}
