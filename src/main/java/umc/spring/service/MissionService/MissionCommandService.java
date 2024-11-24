package umc.spring.service.MissionService;


import umc.spring.web.dto.MissionChallengeRequestDTO;
import umc.spring.web.dto.MissionChallengeResponseDTO;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

public interface MissionCommandService {
    MissionResponseDTO addMission(MissionRequestDTO.AddMissionDto request);

    MissionChallengeResponseDTO challengeMission(MissionChallengeRequestDTO request);

}
