package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MissionChallengeResponseDTO {
    private Long memberMissionId;
    private String status;
}
