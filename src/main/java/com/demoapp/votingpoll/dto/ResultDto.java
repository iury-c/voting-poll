package com.demoapp.votingpoll.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class ResultDto {

    List<ResultSessionDto> resultSessionDtoList;

    @Data
    @Builder
    public static class ResultSessionDto {
        private Integer id;

        private String subject;

        private Map<String, Integer> votes;
    }
}
