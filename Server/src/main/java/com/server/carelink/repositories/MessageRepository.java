package com.server.carelink.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.carelink.models.Message;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m FROM Message m WHERE (m.sender.id= ?1 and m.receiver.id=?2) or (m.sender.id= ?2 and m.receiver.id=?1) order by m.createdAt ASC ")
    List<Message> findBySenderAndReceiver(Long senderId, Long receiverId);
}
