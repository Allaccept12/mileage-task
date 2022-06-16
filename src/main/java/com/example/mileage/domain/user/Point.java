package com.example.mileage.domain.user;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Point {

    private int value;

    public Point(int value) {
        this.value = value;
    }

//    //사진+코멘트 2점 , 사진 1점, 코멘트 1점,
//    public void add
}
