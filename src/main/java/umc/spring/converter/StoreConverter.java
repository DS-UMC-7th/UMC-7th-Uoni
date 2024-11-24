package umc.spring.converter;

import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

public class StoreConverter {

    public static StoreResponseDTO.AddResultDTO toAddResultDTO(Store store) {
        return StoreResponseDTO.AddResultDTO.builder()
                .storeId(store.getId())
                .regionName(store.getRegion().getName())
                .build();
    }

    public static Store toStore(StoreRequestDTO.AddDto request, Region region) {
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .region(region)
                .build();
    }
}
