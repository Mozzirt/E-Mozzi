package game.mozzi.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

//어노테이션이 없어도 IOC등록이 자동으로 됨 JPA레파지토리를 상속 했으면 ㅇㅇ
public interface UserInfoRepository extends JpaRepository<User,Long> {
    // JPA query method
    User findByUsername(String username);
}