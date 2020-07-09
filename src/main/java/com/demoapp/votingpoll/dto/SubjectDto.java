package com.demoapp.votingpoll.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SubjectDto {

    @NotBlank(message = "Name should not be blank.")
    private String name;
}
