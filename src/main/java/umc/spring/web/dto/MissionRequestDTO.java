package umc.spring.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.validation.annotation.ExistCategories;
import umc.spring.validation.annotation.ExistMemberMission;
import umc.spring.validation.annotation.ExistStore;

import java.time.LocalDate;


public class MissionRequestDTO {
    @Getter
    public static class MissionChallengeRequestDTO {

        private Long memberId;

        private Long missionId;
    }


    @Getter
    public static class AddMissionDto {
        private Integer reward;
        private LocalDate deadline;
        private String missionSpec;
        @ExistStore
        private Long storeId;
    }
}

