package com.example.innotek.demobankchallenge.service.impl;

import com.example.innotek.demobankchallenge.model.balance.ServerResponseBalance;
import com.example.innotek.demobankchallenge.model.banktransfer.BankTransfer;
import com.example.innotek.demobankchallenge.model.banktransfer.ServerResponseBankTransferResult;
import com.example.innotek.demobankchallenge.model.transaction.ServerResponseTransactions;
import com.example.innotek.demobankchallenge.service.BankAccountService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriTemplate;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDate;
import java.util.function.Function;

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
                .post()
                .uri("accounts/{accountId}/payments/money-transfers", accountId)
                .header("X-Time-Zone", timeZone)
                .retrieve()
                .bodyToMono(ServerResponseBankTransferResult.class);
    }

    @Override
    public Mono<ServerResponseTransactions> getTransactions(int accountId, LocalDate from, LocalDate to) {
        Function<UriBuilder, URI> uriFunction = (uriBuilder) -> {

            UriTemplate uriTemplate =   new UriTemplate("accounts/{accountId}/transactions");
            URI uri	= uriTemplate.expand(accountId);
            String uriPath = uri.getPath();
            return uriBuilder
                    .path(uriPath)
                    .queryParam("fromAccountingDate", from)
                    .queryParam("toAccountingDate", to)
                    .build();
        };

        return webClient
                .get()
                .uri(uriFunction)
                .retrieve()
                .bodyToMono(ServerResponseTransactions.class);
    }

}
