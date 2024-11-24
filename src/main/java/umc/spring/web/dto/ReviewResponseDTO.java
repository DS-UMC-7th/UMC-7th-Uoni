package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


public class ReviewResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddResultDTO {
        private Long reviewId;
        private String body;
        private Float score;
        private String storeName;
        private String memberName;
        private List<String> imageUrls;


    }
}

