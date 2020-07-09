package com.demoapp.votingpoll.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class SubjectDto {

    @NotBlank(message = "Name should not be blank.")
    private String name;
}
