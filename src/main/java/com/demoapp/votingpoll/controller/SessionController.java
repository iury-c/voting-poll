package com.demoapp.votingpoll.controller;

import com.demoapp.votingpoll.dto.SessionDto;
import com.demoapp.votingpoll.entity.Session;
import com.demoapp.votingpoll.service.SessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Data;
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
@Data
@Api(tags = "Session APIs")
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping("/session")
    @ApiOperation(value = "Creates a new session")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public Session createSession(@Valid @RequestBody SessionDto sessionDto) {
        log.info("New Session received: {}", sessionDto);
        return sessionService.createSession(sessionDto);
    }
}
