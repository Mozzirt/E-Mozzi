package game.mozzi.domain.repository;

import game.mozzi.domain.user.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow,Long> {

}
