package com.example.mileage.domain.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USERS")
public class User {

    @Id
    @Column(name = "user_id")
    private String id;

    @Column(length = 20)
    private String userName;

    @Embedded
    @Column(name = "total_point",nullable = false)
    private Point point;

    public User(String id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    @PrePersist
    private void initPoint() {
        this.point = this.point != null ? this.point : new Point(0);
    }
}
