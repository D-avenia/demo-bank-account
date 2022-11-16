package com.example.innotek.demobankchallenge;

import com.example.innotek.demobankchallenge.controller.BankAccountController;
import com.example.innotek.demobankchallenge.service.BankAccountService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class DemoBankChallengeApplicationTests {

    @Autowired
    private BankAccountController controller;

    @Autowired
    private BankAccountService service;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
        assertThat(service).isNotNull();
    }

}
