package com.demoapp.votingpoll.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Date;

@Entity
@Data
@IdClass(VoteId.class)
public class Vote {

    @Id
    private String cpf;

    @Id
    @Column(name = "session_id")
    private Integer sessionId;

    private String type;

    @Column(name = "create_date")
    private Date creationDate;
}
