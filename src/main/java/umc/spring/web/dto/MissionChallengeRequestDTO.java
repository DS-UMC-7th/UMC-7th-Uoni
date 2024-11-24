package umc.spring.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.validation.annotation.ExistMemberMission;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ExistMemberMission
public class MissionChallengeRequestDTO {

    @NotNull(message = "회원 ID는 필수입니다.")
    private Long memberId;

    @NotNull(message = "미션 ID는 필수입니다.")
    private Long missionId;
}

