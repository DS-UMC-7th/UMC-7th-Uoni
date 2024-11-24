package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MissionResponseDTO {

    private Long missionId;
    private String missionSpec;
    private Integer reward;
    private String storeName;
}

