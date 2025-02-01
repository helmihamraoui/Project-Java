package com.server.controllers;

import com.server.dtos.MessageDTO;
import com.server.models.Message;
import com.server.services.JwtService;
import com.server.services.MessageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/any")
public class ChatController {
    private final MessageService messageService;
    private final JwtService jwtService;

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public Message sendMessage(MessageDTO messageDTO) {
        return messageService.saveMessage(messageDTO);
    }
    @GetMapping("/get/my/History/{receiverId}")
    public List<Message> getMyHistory(
            @PathVariable("receiverId") Long receiverId,
            HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            Long senderId = jwtService.extractUserId(token);
            // Fetch chat history between the sender and receiver
            return messageService.getChatHistory(senderId, receiverId);
        }
        throw new RuntimeException("Authorization header is missing or invalid");
    }
}
