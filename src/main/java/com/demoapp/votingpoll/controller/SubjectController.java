package com.demoapp.votingpoll.controller;

import com.demoapp.votingpoll.dto.SubjectDto;
import com.demoapp.votingpoll.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@Slf4j
@Api(tags = "Subject APIs")
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping("/subject")
    @ApiOperation(value = "Creates a new subject for discussion")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<String> createSubject(@Valid @RequestBody SubjectDto subjectDto) {
        log.info("New Subject received: {}", subjectDto);
        subjectService.createSubject(subjectDto);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .build();
    }
}
