package umc.spring.service.StoreService;

import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO;

public interface StoreCommandService {
    Store addStoreToRegion(StoreRequestDTO.AddDto request);
}

