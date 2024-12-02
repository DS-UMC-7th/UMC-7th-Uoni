package umc.spring.service.MissionService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;

public interface MissionQueryService {

    Page<Mission> getMyChallengingMission(Long memberId, Integer page);
    Page<Mission> getStoreMissionList(Long storeId, Integer page);
}
