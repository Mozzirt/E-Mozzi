package game.mozzi.service;

import game.mozzi.domain.user.User;
import game.mozzi.domain.user.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserInfoRepository userInfoRepository;

    @Transactional
    public User join(User user){
        User userEntity = userInfoRepository.save(user);
        return userEntity;
    }
}
