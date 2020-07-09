package com.demoapp.votingpoll.service;

import com.demoapp.votingpoll.dto.SessionDto;
import com.demoapp.votingpoll.entity.Vote;
import com.demoapp.votingpoll.repository.VoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Slf4j
@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    public Vote createVote(SessionDto sessionDto) {
        //Fazer controle por que n tem FK
        //Fazer a logica do voto so poder ser feito dentro da session
        Vote vote = new Vote();
        vote.setCpf("asasass");
        vote.setSessionId(3322323);
        vote.setType("S");
        vote.setCreationDate(Calendar.getInstance().getTime());
        return voteRepository.save(vote);
    }
}
