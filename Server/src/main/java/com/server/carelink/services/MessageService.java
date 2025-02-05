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
        Optional<User> senderOpt = userRepository.findById(messageDTO.getSenderId());
        Optional<User> receiverOpt = userRepository.findById(messageDTO.getReceiverId());

        if (senderOpt.isEmpty() || receiverOpt.isEmpty()) {
            throw new RuntimeException("Sender or receiver not found");
        }

        Message message = new Message();
        message.setSender(senderOpt.get());
        message.setReceiver(receiverOpt.get());
        message.setMessage(messageDTO.getMessage());

        return messageRepository.save(message);
    }

    public List<Message> getChatHistory(Long senderId, Long receiverId) {
        return messageRepository.findBySenderAndReceiver(senderId, receiverId);
    }

}

