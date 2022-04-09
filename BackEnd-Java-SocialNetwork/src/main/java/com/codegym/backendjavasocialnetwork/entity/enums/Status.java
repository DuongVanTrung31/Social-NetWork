package com.codegym.backendjavasocialnetwork.entity.enums;

import lombok.Getter;

@Getter
public enum Status {
    PRIVATE("Chỉ mình tôi"),
    PUBLIC("Công khai"),
    FRIENDS("Bạn bè");
    private final String name;

    Status(String name) {
        this.name = name;
    }
}
