package com.example.mileage.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_FOUND_USER("유저를 찾을 수 없습니다."),
    ALREADY_WROTE_REVIEW("이미 리뷰를 작성한 상품 입니다."),
    NOT_FOUND_PLACE("선택하신 상품을 찾을 수 없습니다."),
    NOT_FOUND_REVIEW("선택하신 리뷰정보를 찾을 수 없습니다."),
    NOT_FOUND_REVIEW_RECORD("포인트 증감 이력을 찾을 수 없습니다."),
    USER_NOT_HAVE_REVIEW_PERMISSION("해당 리뷰 수정 및 삭제에 권한이 없습니다.");


    private final String message;

}