package com.demoapp.votingpoll.controller;

import com.demoapp.votingpoll.dto.ResultDto;
import com.demoapp.votingpoll.service.ResultService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Data
@Api(tags = "Result APIs")
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class ResultController {

    @Autowired
    private ResultService resultService;

    @GetMapping("/results")
    @ApiOperation(value = "Display the results of all voting sessions")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResultDto getResults() {
        log.info("New Results request received");
        return resultService.getResults();
    }
}
