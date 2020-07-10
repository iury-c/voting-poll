package com.demoapp.votingpoll.service;

import com.demoapp.votingpoll.dto.ResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class ResultService {

    @Autowired
    private VoteService voteService;

    @Autowired
    private SessionService sessionService;

    public ResultDto getResults() {
        Set<Integer> voteList = voteService.findAllSessionsIds();

        List<ResultDto.ResultSessionDto> resultSessionDtoList = getResultSessionDtos(voteList);

        log.info("Returning result list {}", resultSessionDtoList);
        return new ResultDto(resultSessionDtoList);
    }

    private List<ResultDto.ResultSessionDto> getResultSessionDtos(Set<Integer> voteList) {
        List<ResultDto.ResultSessionDto> resultSessionDtoList = new ArrayList<>();

        for (Integer sessionId : voteList) {
            ResultDto.ResultSessionDto resultSessionDto = ResultDto.ResultSessionDto.builder()
                .id(sessionId)
                .subject(sessionService.findById(sessionId).getSubject().getName())
                .votes(voteService.getVotes(sessionId))
                .build();
            resultSessionDtoList.add(resultSessionDto);
        }
        return resultSessionDtoList;
    }
}
