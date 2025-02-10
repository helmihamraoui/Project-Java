package com.server.carelink.services;


import java.util.List;
import java.util.Optional;

import com.server.carelink.dtos.MessageDTO;
import com.server.carelink.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import com.server.carelink.models.Message;
import com.server.carelink.models.User;
import com.server.carelink.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public Message saveMessage(MessageDTO messageDTO) {
        User sender = userRepository.findById(messageDTO.getSenderId())
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        User receiver = userRepository.findById(messageDTO.getReceiverId())
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setMessage(messageDTO.getMessage());  // Ensure message content is set correctly
        // Print debug information to verify content before saving
        System.out.println("Saving message content: " + messageDTO.getMessage());

        return messageRepository.save(message);
    }

    public List<Message> getChatHistory(Long senderId, Long receiverId) {
        return messageRepository.findBySenderAndReceiver(senderId, receiverId);
    }

    public List<Message> findLatestMessages(Long receiverId) {
        return messageRepository.findLatestMessagesByReceiverId(receiverId);
    }
}

