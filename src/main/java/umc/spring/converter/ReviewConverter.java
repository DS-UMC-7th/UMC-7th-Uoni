package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.ReviewImage;
import umc.spring.domain.Store;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResponseDTO.AddResultDTO toAddResultDTO(Review review) {
        List<String> imageUrls = review.getReviewImages().stream()
                .map(ReviewImage::getImageUrl)
                .collect(Collectors.toList());

        return ReviewResponseDTO.AddResultDTO.builder()
                .reviewId(review.getId())
                .body(review.getBody())
                .score(review.getScore())
                .storeName(review.getStore().getName())
                .memberName(review.getMember().getName())
                .imageUrls(imageUrls)
                .build();
    }

    public static Review toReview(ReviewRequestDTO.AddDto request, Store store, Member member) {
        return Review.builder()
                .body(request.getBody())
                .score(request.getScore())
                .store(store)
                .member(member)
                .reviewImages(
                        request.getImageUrls() != null
                                ? request.getImageUrls().stream()
                                .map(url -> ReviewImage.builder().imageUrl(url).build())
                                .collect(Collectors.toList())
                                : new ArrayList<>()
                )
                .build();
    }
}

