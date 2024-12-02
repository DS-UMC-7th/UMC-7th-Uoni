package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.validation.annotation.ExistStore;

import java.util.List;


public class ReviewRequestDTO {

    @Getter
    public static class AddDto {
        @ExistStore
        private Long storeId;
        private Long memberId;
        private String body;
        private Float score;
        private List<String> imageUrls;
    }
}
