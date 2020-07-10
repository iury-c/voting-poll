package com.demoapp.votingpoll.repository;

import com.demoapp.votingpoll.entity.Vote;
import com.demoapp.votingpoll.entity.VoteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface VoteRepository extends JpaRepository<Vote, VoteId> {
    Vote findByCpfAndSessionId(String cpf, Integer sessionId);

    @Query(value = "SELECT distinct session_id FROM VOTE", nativeQuery = true)
    Set<Integer> findAllSessionIdDistinct();

    @Query(value = "SELECT distinct type FROM VOTE ORDER BY 1", nativeQuery = true)
    Set<String> findAllVoteTypeDistinct();

    Integer countBySessionIdAndType(Integer sessionId, String type);

}