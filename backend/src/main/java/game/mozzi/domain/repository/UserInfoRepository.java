package game.mozzi.domain.repository;

import game.mozzi.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

//어노테이션이 없어도 IOC등록이 자동으로 됨 JPA레파지토리를 상속 했으면 ㅇㅇ
public interface UserInfoRepository extends JpaRepository<User,Long> {
    // JPA query method
    User findBySocialId(String socialId);

    boolean existsBySocialId(String socialId);
}
