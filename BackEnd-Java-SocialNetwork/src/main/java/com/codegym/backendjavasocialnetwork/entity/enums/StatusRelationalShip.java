package com.codegym.backendjavasocialnetwork.entity.enums;


import lombok.Getter;

@Getter
public enum StatusRelationalShip {
    FRIENDS("Bạn bè"),
    BLOCKED("Block"),
    PENDING("Đã gửi lời mời");
    private final String name;

    StatusRelationalShip(String name) {
        this.name = name;
    }
}
