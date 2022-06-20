package com.example.mileage.domain.point;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@Embeddable
public class Point {

    private int value;

    protected Point() {
        this.value = 0;
    }

    public void plusOnePoint() {
        this.value += 1;
    }

    public void minusOnePoint() {
        this.value -= 1;
    }

    public boolean verifyPointWhenZero() {
        return this.value != 0;
    }

    public static Point getInstance() {
        return new Point();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return value == point.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
