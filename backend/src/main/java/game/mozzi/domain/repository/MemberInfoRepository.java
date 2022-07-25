package game.mozzi.domain.repository;

import game.mozzi.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberInfoRepository extends JpaRepository<Member,Long> {

    Member findBySocialId(String socialId);

    boolean existsBySocialId(String socialId);
}
