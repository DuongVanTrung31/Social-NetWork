package com.codegym.backendjavasocialnetwork.controller;

import com.codegym.backendjavasocialnetwork.entity.ChatMessage;
import com.codegym.backendjavasocialnetwork.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ChatMessageController {

    @Autowired
    private ChatMessageService chatMessagerService;

    @PostMapping("/chat")
    public ResponseEntity<?> chatMessage(@Valid @RequestBody ChatMessage chatMessage){
        chatMessagerService.save(chatMessage);
        return new ResponseEntity<>(chatMessage.getContent(), HttpStatus.OK);
    }
    @GetMapping("/chat")
    public ResponseEntity<?> getListChat(){
        List<ChatMessage> chatMessages = chatMessagerService.findAll();
        return new ResponseEntity<>(chatMessages, HttpStatus.OK);
    }
}
