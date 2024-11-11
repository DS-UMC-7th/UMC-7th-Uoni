package umc.spring.service.MissionService;

import umc.spring.domain.Mission;
import java.util.List;

public interface MissionQueryService {

    // 특정 회원의 완료된 미션 목록을 조회하는 메서드
    List<Mission> getCompletedMissions(Long memberId);
    List<Mission> getAvailableMissions(Long memberId);
}
