package com.example.mileage.domain.point;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@Embeddable
public class Point {

    private int contentPoint;
    private int bonusPoint;

    protected Point() {
        this.contentPoint = 0;
        this.bonusPoint = 0;
    }

    public void plusOnePoint() {
        this.contentPoint += 1;
    }

    public void minusOnePoint() {
        this.contentPoint -= 1;
    }

    public void plusBonusPoint() {
        this.bonusPoint += 1;
    }

    public void minusBonusPoint() {
        this.bonusPoint -= 1;
    }

    public boolean verifyPointWhenZero() {
        return this.contentPoint != 0;
    }

    public static Point getInstance() {
        return new Point();
    }
}
