package com.codegym.backendjavasocialnetwork.entity.enums;

import lombok.Getter;

@Getter
public enum StatusChatMessage {
    CHAT("Đang Chat"),
    LEAVE("Rời Khỏi"),
    JOIN("Tham Gia");
    private final String name;

    StatusChatMessage(String name) {
        this.name = name;
    }
}
