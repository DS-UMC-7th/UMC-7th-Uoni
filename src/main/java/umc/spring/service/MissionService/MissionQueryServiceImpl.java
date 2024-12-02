package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService{
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
    public Page<Mission> getMyChallengingMission(Long memberId, Integer page){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        return missionRepository.findMissionsByMemberAndStatus(member, MissionStatus.CHALLENGING, PageRequest.of(page,10));

    }

    @Override
    public Page<Mission> getStoreMissionList(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId).get();
        return missionRepository.findAllbyStore(store, PageRequest.of(page,10));
    }
}
