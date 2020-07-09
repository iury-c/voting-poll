package com.demoapp.votingpoll.service;

import com.demoapp.votingpoll.dto.SubjectDto;
import com.demoapp.votingpoll.entity.Subject;
import com.demoapp.votingpoll.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository repository;

    public void createSubject(SubjectDto subjectDto) {
        Subject subject = new Subject();
        subject.setName(subjectDto.getName());
        subject.setCreationDate(Calendar.getInstance().getTime());

        log.info("Saving subject in database {}", subject);
        repository.save(subject);
    }
}
