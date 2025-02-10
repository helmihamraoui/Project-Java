package com.server.carelink.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.carelink.models.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m FROM Message m WHERE (m.sender.id= ?1 and m.receiver.id=?2) or (m.sender.id= ?2 and m.receiver.id=?1) order by m.createdAt ASC ")
    List<Message> findBySenderAndReceiver(Long senderId, Long receiverId);
    ;

    @Query(value = "SELECT m.* " +
            "FROM javaproject.messages m " +
            "JOIN (" +
            "    SELECT m2.sender_id, MAX(m2.created_at) AS latest_message_time " +
            "    FROM javaproject.messages m2 " +
            "    WHERE m2.receiver_id = :receiverId " +
            "      AND m2.sender_id != :receiverId " +
            "      AND NOT EXISTS (" +
            "          SELECT 1 " +
            "          FROM javaproject.messages sub " +
            "          WHERE sub.sender_id = :receiverId " +
            "            AND sub.receiver_id = m2.sender_id " +
            "            AND sub.created_at > m2.created_at" +
            "      ) " +
            "    GROUP BY m2.sender_id" +
            ") subquery " +
            "ON m.sender_id = subquery.sender_id " +
            "AND m.created_at = subquery.latest_message_time " +
            "WHERE m.receiver_id = :receiverId " +
            "ORDER BY m.created_at DESC", nativeQuery = true)
    List<Message> findLatestMessagesByReceiverId(@Param("receiverId") Long receiverId);

}
