package umc.spring.repository.MissionRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Mission;
import umc.spring.domain.QMission;
import umc.spring.domain.QMemberMission;
import umc.spring.domain.QStore;
import umc.spring.domain.enums.MissionStatus;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QMission mission = QMission.mission;
    private final QMemberMission memberMission = QMemberMission.memberMission;
    private final QStore store = QStore.store;

    @Override
    public List<Mission> findCompletedMissions(Long memberId) {
        return jpaQueryFactory
                .select(mission)
                .from(mission)
                .join(mission.store, store) // Mission과 Store를 조인
                .join(mission.memberMissionList, memberMission) // Mission과 MemberMission을 조인
                .where(memberMission.member.id.eq(memberId) // 특정 memberId로 필터링
                        .and(memberMission.status.eq(MissionStatus.COMPLETED))) // 상태가 완료인 미션만 필터링
                .fetch(); // 결과 리스트 반환
    }

    @Override
    public List<Mission> findAvailableMissionsByRegion(Long memberId) {
        return jpaQueryFactory
                .select(mission)  // Mission 엔티티를 선택
                .from(mission)
                .join(mission.store, store)  // Mission과 Store를 조인
                .join(mission.memberMissionList, memberMission)  // Mission과 MemberMission을 조인
                .where(memberMission.member.id.eq(memberId)  // 특정 memberId로 필터링
                        .and(memberMission.status.eq(MissionStatus.COMPLETED)))  // 상태가 완료인 미션만 필터링
                .and(mission.deadline.after(LocalDate.now()))  // 남은 기간이 있는 미션만 필터링
                .and(store.region.id.eq(getRegionIdByMemberId(memberId)))  // 지역 필터링
                .fetch();  // 결과 리스트 반환
    }

    private Long getRegionIdByMemberId(Long memberId) {
        // memberId에 해당하는 Region을 반환하는 로직을 추가
        // 예: MemberRepository를 사용해 해당 회원의 Region을 찾을 수 있습니다.
        return 1L; // 예시 값
    }
}
