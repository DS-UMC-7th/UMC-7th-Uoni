package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class StoreRequestDTO {

    @Getter
    public static class AddDto {
        private String name;
        private String address;
        private Long region;
    }
}

