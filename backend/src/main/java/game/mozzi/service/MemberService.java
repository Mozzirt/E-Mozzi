package game.mozzi.service;

import game.mozzi.domain.entity.Member;
import game.mozzi.domain.repository.MemberInfoRepository;
import game.mozzi.handler.ex.CustomValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PrePersist;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberInfoRepository memberInfoRepository;

    /**
     * 회원가입
     * @param member
     * @return
     */
    @Transactional
    public Member join(Member member){

        if (memberInfoRepository.existsBySocialId(member.getSocialId())){
            throw new CustomValidationException("이미 존재하는 소셜아이디 입니다");
        }
        Member userEntity = memberInfoRepository.save(member);
        return userEntity;
    }

    /**
     * 회원여부 찾기
     * @param socialId
     * @return
     */
    public boolean findUser(String socialId){
        return memberInfoRepository.existsBySocialId(socialId);
    }

    /**
     * 회원조회 ( 소셜아이디 )
     * @param socialId
     * @return
     */
    public Member findUserBySocialId(String socialId){
        return memberInfoRepository.findBySocialId(socialId);
    }

    /**
     * 닉네임 중복조회 ( 닉네임 )
     * @param nickname
     * @return
     */
    public boolean findUserNickname(String nickname) {
        return memberInfoRepository.existsByNickname(nickname);
    }

    /**
     * 회원수정
     * @param member
     * @return
     */
    @Transactional
    public Member modifyInfo(String socialId, Member member){
        Member findMember = memberInfoRepository.findBySocialId(socialId);
        // todo : 데이터저장작업 임시 이메일만 test@test.com으로 함
        findMember.setEmail("test@test.com");
        return findMember;
    }

}
