package umc.spring.repository.MemberRepository;

import umc.spring.domain.Member;

public interface MemberRepositoryCustom {

    // 회원 정보를 조회하는 메서드 (마이 페이지용)
    Member findMemberDetailsById(Long memberId);
}
