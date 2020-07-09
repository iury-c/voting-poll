package com.demoapp.votingpoll.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SessionDto {

    private Integer duration;

    @NotBlank(message = "Subject should not be blank.")
    private String subject;
}
