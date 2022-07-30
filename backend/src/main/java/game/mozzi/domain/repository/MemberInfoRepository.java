package game.mozzi.domain.repository;

import game.mozzi.domain.entity.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberInfoRepository extends JpaRepository<Member,Long> {

    // 소셜아이디로 회원검색
    Member findBySocialId(String socialId);

    // 소셜아이디 존재여부검색
    boolean existsBySocialId(String socialId);

    // 닉네임 존재여부검색
    boolean existsByNickname(String nickname);
}
