package com.clara.notification.center.repository;

import com.clara.notification.center.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
}
