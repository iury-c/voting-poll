package com.demoapp.votingpoll.repository;

import com.demoapp.votingpoll.entity.Associate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociateRepository extends JpaRepository<Associate, String> {
}