package com.demoapp.votingpoll.repository;

import com.demoapp.votingpoll.entity.Vote;
import com.demoapp.votingpoll.entity.VoteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, VoteId> {
    Vote findByCpfAndSessionId(String cpf, Integer sessionId);
}