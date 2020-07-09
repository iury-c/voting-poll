package com.demoapp.votingpoll.service;

import com.demoapp.votingpoll.dto.SessionDto;
import com.demoapp.votingpoll.entity.Session;
import com.demoapp.votingpoll.entity.Subject;
import com.demoapp.votingpoll.repository.SessionRepository;
import com.demoapp.votingpoll.repository.SubjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Calendar;
import java.util.Objects;

@Slf4j
@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Value("${session.defaultDuration}")
    private Integer duration;

    public Session createSession(SessionDto sessionDto) {
        Subject subject = subjectRepository.findByName(sessionDto.getSubject());

        Assert.notNull(subject, "Invalid subject");

        Session session = processSession(sessionDto.getDuration(), subject);

        log.info("Saving session in database {}", session);
        return sessionRepository.save(session);
    }

    private Session processSession(Integer sessionDuration, Subject subject) {
        Session session = new Session();
        session.setSubject(subject);

        Calendar calendar = Calendar.getInstance();

        session.setStart(calendar.getTime());

        if (Objects.isNull(sessionDuration) || sessionDuration <= 0) {
            log.info("Session duration set to default.");
            calendar.add(Calendar.MINUTE, duration);
        } else {
            log.info("Session duration set to: {}", sessionDuration);
            calendar.add(Calendar.MINUTE, sessionDuration);
        }

        session.setFinish(calendar.getTime());

        return session;
    }
}
