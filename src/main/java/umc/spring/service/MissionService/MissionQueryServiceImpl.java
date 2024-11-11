package umc.spring.service.MissionService;

import umc.spring.domain.Mission;
import umc.spring.repository.MissionRepository.MissionRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepositoryCustom missionRepositoryCustom;

    @Override
    public List<Mission> getCompletedMissions(Long memberId) {
        return missionRepositoryCustom.findCompletedMissions(memberId);
    }

    @Override
    public List<Mission> getAvailableMissions(Long memberId) {
        return missionRepositoryCustom.findAvailableMissionsByRegion(memberId);  // Available 미션 조회
    }
}
