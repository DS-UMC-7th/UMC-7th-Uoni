package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Store;
import umc.spring.service.MemberService.MemberCommandService;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;
import umc.spring.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {
    private final StoreCommandService storeCommandService;
    @PostMapping("/add")
    public ApiResponse<StoreResponseDTO.AddResultDTO> addStoreToRegion(
            @RequestBody @Valid StoreRequestDTO.AddDto request) {
        Store store = storeCommandService.addStoreToRegion(request);
        return ApiResponse.onSuccess(StoreConverter.toAddResultDTO(store));
    }


}
