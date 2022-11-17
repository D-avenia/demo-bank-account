package com.example.innotek.demobankchallenge.service.impl;

import com.example.innotek.demobankchallenge.model.balance.Balance;
import com.example.innotek.demobankchallenge.model.banktransfer.BankTransfer;
import com.example.innotek.demobankchallenge.model.banktransfer.BankTransferResult;
import com.example.innotek.demobankchallenge.model.transaction.Transaction;
import com.example.innotek.demobankchallenge.model.transaction.TransactionPayload;
import com.example.innotek.demobankchallenge.service.BankAccountService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final WebClient webClient;

    public BankAccountServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Balance getBalance(int accountId) {

        return webClient
                .get()
                .uri("accounts/{accountId}/balance", accountId)
                .retrieve()
                .bodyToMono(Balance.class).block();
    }

    @Override //Questo forse non funzionerà
    public BankTransferResult moneyTransfers(int accountId, String timeZone, BankTransfer moneyTransfer) {
        return webClient
                .get()
                .uri("accounts/{accountId}/payments/money-transfers", accountId)
                .retrieve()
                .bodyToMono(BankTransferResult.class).block();
    }

    @Override
    public TransactionPayload getTransactions(int accountId, LocalDate from, LocalDate to) {
        return webClient
                .get()
                .uri("accounts/{accountId}/transactions", accountId)
                .retrieve()
                .bodyToMono(TransactionPayload.class).block();
    }

    @Override
    public void persistTransactions(int accountId, List<Transaction> list) {
        //Qua serve la repository
    }
}
