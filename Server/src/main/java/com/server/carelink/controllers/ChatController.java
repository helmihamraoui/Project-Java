package com.server.carelink.controllers;

import com.server.carelink.dtos.MessageDTO;
import com.server.carelink.models.Message;
import com.server.carelink.services.JwtService;
import com.server.carelink.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/any")
public class ChatController {
    private final MessageService messageService;
    private final JwtService jwtService;

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public Message sendMessage(MessageDTO messageDTO) {

        return messageService.saveMessage(messageDTO);
    }
    @GetMapping("/get/my/History/{receiverId}/{senderId}")
    public List<Message> getMyHistory(
            @PathVariable("receiverId") Long receiverId,
            @PathVariable("senderId") Long senderId) {


        return messageService.getChatHistory(senderId, receiverId);
    }
}
