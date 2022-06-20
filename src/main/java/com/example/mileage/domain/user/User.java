package com.example.mileage.domain.user;

import com.example.mileage.domain.point.Point;
import lombok.AccessLevel;
import lombok.Builder;
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
    private String nickName;


    @Builder
    public User(String id, String nickName) {
        this.id = id;
        this.nickName = nickName;
    }


}
