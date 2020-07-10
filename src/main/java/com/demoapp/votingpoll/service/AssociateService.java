package com.demoapp.votingpoll.service;

import com.demoapp.votingpoll.entity.Associate;
import com.demoapp.votingpoll.repository.AssociateRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Data
public class AssociateService {

    @Autowired
    private AssociateRepository repository;

    public Associate findById(String cpf) {
        return repository.findById(cpf).orElse(null);
    }
}
