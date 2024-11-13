package umc.spring.apiPayload.code;

import umc.spring.apiPayload.ReasonDTO;

public interface BaseCode {

    ReasonDTO getReason();

    ReasonDTO getReasonHttpStatus();
}