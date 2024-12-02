package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.service.ReviewService.ReviewQueryService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ExistMemberMission;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionRestController {

    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;
    @PutMapping("/complete")
    public ApiResponse<MissionResponseDTO.MissionChallengeResultDTO> completeMission(
            @RequestBody @Valid MissionRequestDTO.MissionChallengeRequestDTO request){
        return missionCommandService.completeMission(request);
    }

    @GetMapping("{memberId}/challenging")
    public ApiResponse<MissionResponseDTO.MyChMissionListDTO> getMyChallengingMission(
            @PathVariable @Valid Long memberId,
            @RequestParam @CheckPage Integer page){
        page = page - 1;
    Page<Mission> missionPage = missionQueryService.getMyChallengingMission(memberId, page);
    return ApiResponse.onSuccess(MissionConverter.myChMissionListDTO(missionPage));
    }
    @GetMapping("/{storeId}/missions")
    public ApiResponse<MissionResponseDTO.StoreMissionListDTO> getStoreMissionList(
            @PathVariable @ExistStore Long storeId,
            @RequestParam @CheckPage Integer page){
        page = page - 1;
        Page<Mission> missionList = missionQueryService.getStoreMissionList(storeId,page);
        return ApiResponse.onSuccess(MissionConverter.StoreMissionListDTO(missionList));
    }

    @PostMapping
    public ResponseEntity<MissionResponseDTO.AddResultDTO> addMission(
            @RequestBody @Valid MissionRequestDTO.AddMissionDto request) {
        MissionResponseDTO.AddResultDTO response = missionCommandService.addMission(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/challenge")
    public ResponseEntity<MissionResponseDTO.MissionChallengeResultDTO> challengeMission(
            @RequestBody @Valid MissionRequestDTO.MissionChallengeRequestDTO request) {
        MissionResponseDTO.MissionChallengeResultDTO response = missionCommandService.challengeMission(request);
        return ResponseEntity.ok(response);
    }
}
