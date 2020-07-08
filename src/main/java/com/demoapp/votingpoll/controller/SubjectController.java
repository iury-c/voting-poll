package com.demoapp.votingpoll.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@Api(tags = "Subject APIs")
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class SubjectController extends BaseController {

    @GetMapping("/subject")
    @ApiOperation(value = "Creates a new subject for discussion")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity createSubject(String subject) {
        log.info("Creating new subject.");
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .build();
    }
}
