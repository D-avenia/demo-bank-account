package com.example.innotek.demobankchallenge;

import com.example.innotek.demobankchallenge.controller.BankAccountController;
import com.example.innotek.demobankchallenge.model.balance.Balance;
import com.example.innotek.demobankchallenge.model.balance.ServerResponseBalance;
import com.example.innotek.demobankchallenge.model.transaction.ServerResponseTransactions;
import com.example.innotek.demobankchallenge.model.transaction.Transaction;
import com.example.innotek.demobankchallenge.model.transaction.TransactionPayload;
import com.example.innotek.demobankchallenge.service.BankAccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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

    @DisplayName("Context Loads")
    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
        assertThat(service).isNotNull();
    }

    Mono<ServerResponseBalance> getExpectedBalance() {
        Balance expected = new Balance();
        expected.setDate(LocalDate.of(2022, 9, 11));
        expected.setBalance(BigDecimal.valueOf(7.27));
        expected.setAvailableBalance(BigDecimal.valueOf(7.27));
        expected.setCurrency("EUR");

        ServerResponseBalance serverResponseBalanceExpected = new ServerResponseBalance();
        serverResponseBalanceExpected.setPayload(expected);

        return Mono.just(serverResponseBalanceExpected);
    }

    Mono<ServerResponseTransactions> getExpectedTransactions() {
        Transaction expected = new Transaction();
        expected.setAccountingDate(LocalDate.of(2022, 9, 11));
        expected.setAmount(BigDecimal.valueOf(7.27));
        expected.setCurrency("EUR");

        TransactionPayload transactionPayloadExpected = new TransactionPayload();
        transactionPayloadExpected.setList(new ArrayList<>());
        transactionPayloadExpected.getList().add(expected);

        ServerResponseTransactions serverResponseTransactionsExpected = new ServerResponseTransactions();
        serverResponseTransactionsExpected.setRequestID("1");
        serverResponseTransactionsExpected.setStatus("OK");
        serverResponseTransactionsExpected.setPayload(transactionPayloadExpected);

        return Mono.just(serverResponseTransactionsExpected);
    }

    @BeforeEach
    void init() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate from = LocalDate.parse("2022-09-11",df);
        LocalDate to = LocalDate.parse("2022-09-11",df);

        Mockito.when(service.getBalance(12345)).thenReturn(getExpectedBalance().block());
        Mockito.when(service.getTransactions(12345, from, to)).thenReturn(getExpectedTransactions().block());
    }

    @Test
    void givenAccount_whenGetBalance_thenExpectedBalance(){
        Assertions.assertNotNull(controller.getBalance(12345).getBody());
    }

    @Test
    void givenAccount_whenGetTransactions_thenExpectedBalance(){
        Assertions.assertNotNull(controller.getTransactions(12345,"2022-09-11","2022-09-11").getBody());
    }
}
