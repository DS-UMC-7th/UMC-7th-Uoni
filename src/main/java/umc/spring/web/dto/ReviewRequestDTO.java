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
        private Long storeId; // 가게 ID
        private Long memberId; // 작성자 ID
        private String body;
        private Float score;  // 리뷰 점수
        private List<String> imageUrls; // 이미지 URL 목록
    }
}
