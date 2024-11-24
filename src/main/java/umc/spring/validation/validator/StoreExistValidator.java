package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.validation.annotation.ExistStore;

@RequiredArgsConstructor
public class StoreExistValidator implements ConstraintValidator<ExistStore, Long> {

    private final StoreRepository storeRepository;

    @Override
    public void initialize(ExistStore constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long storeId, ConstraintValidatorContext context) {
        // 가게 ID가 존재하는지
        boolean isValid = storeRepository.existsById(storeId);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.STORE_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
