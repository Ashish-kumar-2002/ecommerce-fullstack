package com.ecommerce.project.controller;

import com.ecommerce.project.payload.ChatRequest;
import com.ecommerce.project.payload.ChatResponse;
import com.ecommerce.project.service.ChatService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/chat")
@CrossOrigin
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public ResponseEntity<ChatResponse> chat(
            @Valid @RequestBody ChatRequest request) {

        String reply =
                chatService.getChatResponse(request.getMessage());

        return ResponseEntity.ok(
                new ChatResponse(reply)
        );
    }
}