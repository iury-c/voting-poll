package com.demoapp.votingpoll.service;

import com.demoapp.votingpoll.dto.SubjectDto;
import com.demoapp.votingpoll.entity.Subject;
import com.demoapp.votingpoll.repository.SubjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Slf4j
@Service
public class SubjectService {

    @Autowired
    private SubjectRepository repository;

    public Subject createSubject(SubjectDto subjectDto) {
        Subject subject = new Subject();
        subject.setName(subjectDto.getName());
        subject.setCreationDate(Calendar.getInstance().getTime());

        log.info("Saving subject in database {}", subject);
        return repository.save(subject);
    }

    public Subject findByName(String subject) {
        return repository.findByName(subject);
    }
}
