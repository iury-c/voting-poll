package com.demoapp.votingpoll.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VoteId implements Serializable {

    private String cpf;

    @Column(name = "session_id")
    private Integer sessionId;
}

