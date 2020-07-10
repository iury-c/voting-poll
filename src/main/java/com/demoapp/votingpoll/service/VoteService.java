package com.demoapp.votingpoll.service;

import com.demoapp.votingpoll.dto.VoteDto;
import com.demoapp.votingpoll.entity.Vote;
import com.demoapp.votingpoll.repository.SessionRepository;
import com.demoapp.votingpoll.repository.VoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Calendar;

@Slf4j
@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private AssociateService associateService;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SessionService sessionService;

    public Vote createVote(VoteDto voteDto) {
        validateVote(voteDto);

        Vote vote = new Vote();
        vote.setCpf(voteDto.getCpf());
        vote.setSessionId(voteDto.getSessionId());
        vote.setType(voteDto.getVoteType());
        vote.setCreationDate(Calendar.getInstance().getTime());

        return voteRepository.save(vote);
    }

    public void validateVote(VoteDto voteDto) {
        if (!voteDto.getVoteType().contains("Sim") && !voteDto.getVoteType().contains("Nao")) {
            throw new IllegalArgumentException("Invalid vote type");
        }

        Assert.isTrue(sessionService.isOpen(voteDto.getSessionId()), "Voting session is finished");

        Assert.notNull(associateService.findById(voteDto.getCpf()), "Invalid cpf");
        Assert.notNull(sessionService.findById(voteDto.getSessionId()), "Invalid session id");

        Assert.isNull(voteRepository.findByCpfAndSessionId(voteDto.getCpf(), voteDto.getSessionId()), "Cpf has already voted for this session");
    }
}
