package com.demoapp.votingpoll.service;

import com.demoapp.votingpoll.dto.SubjectDto;
import com.demoapp.votingpoll.entity.Subject;
import com.demoapp.votingpoll.repository.SubjectRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SubjectServiceTest {

    @InjectMocks
    private SubjectService subjectService;

    @Mock
    private SubjectRepository repository;

    @Test
    public void shouldCreateSubjectSuccessfully() {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setName("TestSubject1");
        subjectService.createSubject(subjectDto);
        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(Subject.class));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerException() {
        subjectService.createSubject(null);
    }

}
