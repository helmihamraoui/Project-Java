package com.server.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.models.Message;
import com.server.models.User;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m FROM Message m WHERE (m.sender.id= ?1 and m.receiver.id=?2) or (m.sender.id= ?2 and m.receiver.id=?1)")
    List<Message> findBySenderAndReceiver(Long senderId, Long receiverId);
}
