package com.demoapp.votingpoll.service;

import com.demoapp.votingpoll.dto.VoteDto;
import com.demoapp.votingpoll.entity.Vote;
import com.demoapp.votingpoll.repository.SessionRepository;
import com.demoapp.votingpoll.repository.VoteRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
@Data
public class VoteService {

    @Autowired
    private VoteRepository repository;

    @Autowired
    private AssociateService associateService;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private CpfValidationService cpfValidationService;

    public Vote createVote(VoteDto voteDto) {
        validateVote(voteDto);

        Vote vote = new Vote();
        vote.setCpf(voteDto.getCpf());
        vote.setSessionId(voteDto.getSessionId());
        vote.setType(voteDto.getVoteType());
        vote.setCreationDate(Calendar.getInstance().getTime());

        log.info("Saving vote in database {}", vote);
        return repository.save(vote);
    }

    public void validateVote(VoteDto voteDto) {
        log.info("Starting vote validation");

        if (!voteDto.getVoteType().contains("Sim") && !voteDto.getVoteType().contains("Nao")) {
            throw new IllegalArgumentException("Invalid vote type");
        }

        Assert.notNull(sessionService.findById(voteDto.getSessionId()), "Invalid session id");
        Assert.isTrue(sessionService.isOpenForVoting(voteDto.getSessionId()), "Voting session is finished");

        Assert.notNull(associateService.findById(voteDto.getCpf()), "Invalid cpf");
        Assert.isTrue(cpfValidationService.isCpfValid(voteDto.getCpf()), "Cpf could not be validated");

        Assert.isNull(repository.findByCpfAndSessionId(voteDto.getCpf(), voteDto.getSessionId()), "Cpf has already voted for this session");
    }

    public Set<Integer> findAllVotedSessionsIds() {
        log.info("Finding all session ids that have votes");
        return repository.findAllSessionIdDistinct();
    }

    public Map<String, Integer> getVotes(Integer sessionId) {
        Set<String> voteTypeList = repository.findAllVoteTypeDistinct();

        Map<String, Integer> votes = new HashMap<>();

        for (String voteType : voteTypeList) {
            Integer count = repository.countBySessionIdAndType(sessionId, voteType);
            votes.put(voteType, count);
        }
        return votes;
    }
}
