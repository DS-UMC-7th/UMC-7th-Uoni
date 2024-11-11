package umc.spring.service.MemberService;

import umc.spring.domain.Member;
import umc.spring.repository.MemberRepository.MemberRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class MemberQueryService {
    private final MemberRepositoryCustom memberRepositoryCustom;

    // 회원 정보 조회 (마이 페이지)
    public Member getMemberDetails(Long memberId) {
        return memberRepositoryCustom.findMemberDetailsById(memberId);
    }
}