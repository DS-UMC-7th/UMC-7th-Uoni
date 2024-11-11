package umc.spring.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.Member;
import umc.spring.repository.ReviewRepository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public Review createReview(Long storeId, Long memberId, String title, Float score) {
        // Store 및 Member 엔티티를 조회 (예: 이미 존재하는 Store와 Member를 사용)
        Store store = Store.builder().id(storeId).build(); // 실제로는 Store 조회
        Member member = Member.builder().id(memberId).build(); // 실제로는 Member 조회

        // Review 엔티티 생성
        Review review = Review.builder()
                .title(title)
                .score(score)
                .store(store)
                .member(member)
                .build();

        // Review 저장
        return reviewRepository.save(review);
    }
}
