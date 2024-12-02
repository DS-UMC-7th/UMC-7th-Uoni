package umc.spring.apiPayload.exception.handler;

import umc.spring.apiPayload.exception.GeneralException;
import umc.spring.apiPayload.code.BaseErrorCode;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
