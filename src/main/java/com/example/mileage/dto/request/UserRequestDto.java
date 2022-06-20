package com.example.mileage.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
public class UserRequestDto {

    private String userId;

    @Size(min = 1, max = 20, message = "닉네임은 20자까지 입력할 수 있습니다.")
    @Pattern(regexp = "[\\s_a-zA-Z0-9가-힣]",message = "특수문자는 사용이 불가능 합니다.")
    private String nickName;


}
