package com.example.innotek.demobankchallenge;

import com.example.innotek.demobankchallenge.controller.BankAccountController;
import com.example.innotek.demobankchallenge.service.BankAccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles(profiles = "test")
@MockBeans({ @MockBean(BankAccountService.class) })
@SpringBootTest
@ExtendWith(SpringExtension.class)
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
