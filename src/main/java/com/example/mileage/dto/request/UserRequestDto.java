package com.example.mileage.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
public class UserRequestDto {

    private String userId;

    @Size(min = 1, max = 20, message = "닉네임은 1글자 이상 20글자 이하만 가능합니다.")
    @Pattern(regexp = "^([a-zA-Z0-9가-힣\\s_]*)$",message = "특수문자는 사용이 불가능 합니다.")
    private String nickName;


}
