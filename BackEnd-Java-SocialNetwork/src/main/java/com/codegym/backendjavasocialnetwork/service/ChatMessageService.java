package com.codegym.backendjavasocialnetwork.service;

import com.codegym.backendjavasocialnetwork.entity.ChatMessage;

import java.util.List;

public interface ChatMessageService {
    ChatMessage save(ChatMessage chatMessage);
    List<ChatMessage> findAll();
}
