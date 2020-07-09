package com.demoapp.votingpoll.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@Data
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    @OneToOne
    private Subject subject;

    private Date start;

    private Date finish;
}
