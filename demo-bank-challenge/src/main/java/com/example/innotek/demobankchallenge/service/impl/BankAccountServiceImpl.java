package com.example.innotek.demobankchallenge.service.impl;

import com.example.innotek.demobankchallenge.model.balance.ServerResponseBalance;
import com.example.innotek.demobankchallenge.model.banktransfer.BankTransfer;
import com.example.innotek.demobankchallenge.model.banktransfer.ServerResponseBankTransferResult;
import com.example.innotek.demobankchallenge.model.transaction.ServerResponseTransactions;
import com.example.innotek.demobankchallenge.service.BankAccountService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final WebClient webClient;

    public BankAccountServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<ServerResponseBalance> getBalance(int accountId) {

        return webClient
                .get()
                .uri("accounts/{accountId}/balance", accountId)
                .retrieve()
                .bodyToMono(ServerResponseBalance.class);
    }

    @Override
    public Mono<ServerResponseBankTransferResult> moneyTransfers(int accountId, String timeZone, BankTransfer moneyTransfer) {
        return webClient
                .get()
                .uri("accounts/{accountId}/payments/money-transfers", accountId)
                .header("X-Time-Zone", timeZone)
                .retrieve()
                .bodyToMono(ServerResponseBankTransferResult.class);
    }

    @Override
    public Mono<ServerResponseTransactions> getTransactions(int accountId, LocalDate from, LocalDate to) {
        return webClient
                .get()
                .uri("accounts/{accountId}/transactions", accountId)
                .retrieve()
                .bodyToMono(ServerResponseTransactions.class);
    }

}
