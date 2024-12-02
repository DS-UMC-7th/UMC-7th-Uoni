package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.validation.annotation.ExistMemberMission;
import umc.spring.web.dto.MissionRequestDTO;

@Component
@RequiredArgsConstructor
public class MemberMissionExistValidator implements ConstraintValidator<ExistMemberMission, MissionRequestDTO.MissionChallengeRequestDTO> {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public boolean isValid(MissionRequestDTO.MissionChallengeRequestDTO value, ConstraintValidatorContext context) {
        // 이미 도전 중인지 확인
        boolean exists = memberMissionRepository.existsByMemberIdAndMissionId(value.getMemberId(), value.getMissionId());
        return !exists; // 존재하면 false 반환
    }
}

