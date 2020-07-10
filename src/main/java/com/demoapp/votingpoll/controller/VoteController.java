package com.demoapp.votingpoll.controller;

import com.demoapp.votingpoll.dto.VoteDto;
import com.demoapp.votingpoll.entity.Vote;
import com.demoapp.votingpoll.service.VoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@Api(tags = "Vote APIs")
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping("/vote")
    @ApiOperation(value = "Creates a new vote")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public Vote createVote(@Valid @RequestBody VoteDto voteDto) {
        log.info("New Vote received: {}", voteDto);
        return voteService.createVote(voteDto);
    }
}
