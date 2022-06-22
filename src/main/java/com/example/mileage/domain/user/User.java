package com.example.mileage.domain.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
