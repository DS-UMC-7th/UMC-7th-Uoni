package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
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
    public MissionResponseDTO addMission(MissionRequestDTO.AddMissionDto request) {
        // Store 존재 여부 확인
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new MissionHandler(ErrorStatus.STORE_NOT_FOUND));

        // Mission 생성
        Mission mission = Mission.builder()
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .missionSpec(request.getMissionSpec())
                .store(store)
                .build();

        // 저장
        missionRepository.save(mission);

        return new MissionResponseDTO(
                mission.getId(),
                mission.getMissionSpec(),
                mission.getReward(),
                store.getName()
        );
    }
    @Override
    public MissionChallengeResponseDTO challengeMission(MissionChallengeRequestDTO request) {
        // 회원 확인
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MEMBER_NOT_FOUND));

        // 미션 확인
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        // MemberMission 생성
        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)
                .build();

        // 저장
        memberMissionRepository.save(memberMission);

        return new MissionChallengeResponseDTO(memberMission.getId(), memberMission.getStatus().name());
    }
}



