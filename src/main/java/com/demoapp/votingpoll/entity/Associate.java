package com.demoapp.votingpoll.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Associate {

    @Id
    private String cpf;

    private String name;
}
