package umc.spring.service.MissionService;

import org.springframework.data.domain.Page;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.domain.Mission;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

public interface MissionCommandService {
    MissionResponseDTO.AddResultDTO addMission(MissionRequestDTO.AddMissionDto request);

    MissionResponseDTO.MissionChallengeResultDTO challengeMission(MissionRequestDTO.MissionChallengeRequestDTO request);

    ApiResponse<MissionResponseDTO.MissionChallengeResultDTO> completeMission(MissionRequestDTO.MissionChallengeRequestDTO request);
}
