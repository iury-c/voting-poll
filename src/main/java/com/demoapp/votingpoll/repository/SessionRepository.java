package com.demoapp.votingpoll.repository;

import com.demoapp.votingpoll.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Integer> {
}