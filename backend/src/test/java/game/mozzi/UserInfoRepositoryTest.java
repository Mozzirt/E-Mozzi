package game.mozzi;

import game.mozzi.domain.user.User;
import game.mozzi.domain.repository.UserInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class UserInfoRepositoryTest extends MozziApplicationTests{

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Test
    public void create(){
        User user = new User();

        user.setUserImage("test");
        user.setEmail("rlaqjacjf@naver.com");
        user.setSocialId("socialId");
        user.setNickname("nickname");
        user.setUserNum(1L);

        User newUser = userInfoRepository.save(user);

        User selectUser = this.select("socialId");

        System.out.println("#################### TEST : " + selectUser);
    }

    public User select(String socialId){
        return userInfoRepository.findBySocialId(socialId);
    }


}
