package com.server.carelink.controllers;

import com.server.carelink.dtos.MessageDTO;
import com.server.carelink.models.Message;
import com.server.carelink.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/any")
public class ChatController {
    private final MessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/sendMessage")
    public void sendMessage(MessageDTO messageDTO) {
        // Print debug information to ensure the message content is received correctly
        System.out.println("Received message content: " + messageDTO.getMessage());

        Message savedMessage = messageService.saveMessage(messageDTO);
        String senderDestination = "/topic/messages/" + messageDTO.getSenderId();
        String receiverDestination = "/topic/messages/" + messageDTO.getReceiverId();

        messagingTemplate.convertAndSend(senderDestination, savedMessage);
        messagingTemplate.convertAndSend(receiverDestination, savedMessage);
    }


    @PostMapping("/send/{receiverId}/{senderId}")
    public Message sendMessageAPI(@RequestBody MessageDTO messageDTO,
                                  @PathVariable Long receiverId,
                                  @PathVariable Long senderId) {
        messageDTO.setSenderId(senderId);
        messageDTO.setReceiverId(receiverId);
        return messageService.saveMessage(messageDTO);
    }

    @GetMapping("/get/my/History/{receiverId}/{senderId}")
    public List<Message> getMyHistory(@PathVariable Long receiverId,
                                      @PathVariable Long senderId) {
        return messageService.getChatHistory(senderId, receiverId);
    }
}
