package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.apiPayload.exception.handler.MemberMissionHandler;
import umc.spring.apiPayload.exception.handler.MissionHandler;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.web.dto.*;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    @Override
    @Transactional
    public MissionResponseDTO.AddResultDTO addMission(MissionRequestDTO.AddMissionDto request) {
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new MissionHandler(ErrorStatus.STORE_NOT_FOUND));

        Mission mission = Mission.builder()
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .missionSpec(request.getMissionSpec())
                .store(store)
                .build();

        missionRepository.save(mission);

        return new MissionResponseDTO.AddResultDTO(
                mission.getId(),
                mission.getMissionSpec(),
                mission.getReward(),
                store.getName()
        );
    }
    @Override
    public MissionResponseDTO.MissionChallengeResultDTO challengeMission(MissionRequestDTO.MissionChallengeRequestDTO request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)
                .build();

        memberMissionRepository.save(memberMission);

        return new MissionResponseDTO.MissionChallengeResultDTO(memberMission.getId(), memberMission.getStatus());
    }

    @Override
    public ApiResponse<MissionResponseDTO.MissionChallengeResultDTO> completeMission(MissionRequestDTO.MissionChallengeRequestDTO request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));
        MemberMission memberMission = memberMissionRepository.findByMemberAndMission(member, mission)
                .orElseThrow(() -> new MemberMissionHandler(ErrorStatus.MEMBER_MISSION_NOT_FOUND));

        if (memberMission.getStatus() == MissionStatus.COMPLETE) {
            throw new MissionHandler(ErrorStatus.MISSION_ALREADY_COMPLETED);
        }

        memberMission.changeStatus(MissionStatus.COMPLETE);
        memberMissionRepository.save(memberMission);

        MissionResponseDTO.MissionChallengeResultDTO result = MissionResponseDTO.MissionChallengeResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .status(memberMission.getStatus())
                .build();
        return ApiResponse.onSuccess(result);
    }


}



