package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.web.dto.MissionResponseDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResponseDTO.StoreMissionDTO StoreMissionDTO(Mission mission){
        return MissionResponseDTO.StoreMissionDTO.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .reward(mission.getReward())
                .deadline(mission.getDeadline())
                .missionSpec(mission.getMissionSpec())
                .build();
    }

    public static MissionResponseDTO.StoreMissionListDTO StoreMissionListDTO(Page<Mission> missionList){
        List<MissionResponseDTO.StoreMissionDTO> missionListDTO =  missionList.stream()
                .map(MissionConverter::StoreMissionDTO).collect(Collectors.toList());

        return MissionResponseDTO.StoreMissionListDTO.builder()
                .listSize(missionList.getSize())
                .totalElements(missionList.getTotalElements())
                .totalPage(missionList.getTotalPages())
                .isFirst(missionList.isFirst())
                .isLast(missionList.isLast())
                .missionList(missionListDTO)
                .build();
    }

    public static MissionResponseDTO.MyChMissionListDTO myChMissionListDTO(Page<Mission> missionList){
        List<MissionResponseDTO.StoreMissionDTO> missionDTOList = missionList.stream()
                .map(MissionConverter::StoreMissionDTO).collect(Collectors.toList());
        return MissionResponseDTO.MyChMissionListDTO.builder()
                .missionList(missionDTOList)
                .listSize(missionList.getSize())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .isFirst(missionList.isFirst())
                .isLast(missionList.isLast())
                .build();
    }
}
