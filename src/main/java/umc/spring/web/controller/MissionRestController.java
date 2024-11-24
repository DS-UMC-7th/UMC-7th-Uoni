package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.web.dto.MissionChallengeRequestDTO;
import umc.spring.web.dto.MissionChallengeResponseDTO;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    @PostMapping
    public ResponseEntity<MissionResponseDTO> addMission(
            @RequestBody @Valid MissionRequestDTO.AddMissionDto request) {
        MissionResponseDTO response = missionCommandService.addMission(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/challenge")
    public ResponseEntity<MissionChallengeResponseDTO> challengeMission(
            @RequestBody @Valid MissionChallengeRequestDTO request) {
        MissionChallengeResponseDTO response = missionCommandService.challengeMission(request);
        return ResponseEntity.ok(response);
    }
}
