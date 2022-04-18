package com.codegym.backendjavasocialnetwork.service.impl;

import com.codegym.backendjavasocialnetwork.entity.ChatMessage;
import com.codegym.backendjavasocialnetwork.repository.ChatMessageRepository;
import com.codegym.backendjavasocialnetwork.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {

    @Autowired
    private ChatMessageRepository chatMessageRepo;

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        return chatMessageRepo.save(chatMessage);
    }

    @Override
    public List<ChatMessage> findAll() {
        return chatMessageRepo.findAll();
    }
}
