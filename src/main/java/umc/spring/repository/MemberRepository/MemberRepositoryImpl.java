package umc.spring.repository.MemberRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Member;
import umc.spring.domain.QMember;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QMember member = QMember.member;

    @Override
    public Member findMemberDetailsById(Long memberId) {
        return jpaQueryFactory
                .selectFrom(member)
                .where(member.id.eq(memberId))  // memberId로 필터링
                .fetchOne();  // 결과 하나만 반환
    }
}
