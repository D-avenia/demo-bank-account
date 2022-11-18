package com.example.innotek.demobankchallenge.service.impl;

import com.example.innotek.demobankchallenge.exception.BankAccountException;
import com.example.innotek.demobankchallenge.model.balance.ServerResponseBalance;
import com.example.innotek.demobankchallenge.model.banktransfer.BankTransfer;
import com.example.innotek.demobankchallenge.model.banktransfer.ServerResponseBankTransferResult;
import com.example.innotek.demobankchallenge.model.server.ServerResponse;
import com.example.innotek.demobankchallenge.model.transaction.ServerResponseTransactions;
import com.example.innotek.demobankchallenge.service.BankAccountService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriTemplate;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDate;
import java.util.function.Function;

@Service
@Slf4j
public class BankAccountServiceImpl implements BankAccountService {

    private final static Logger logger = LoggerFactory.getLogger(BankAccountServiceImpl.class);

    private final WebClient webClient;

    public BankAccountServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public ServerResponseBalance getBalance(int accountId) {
        logger.debug("Start - getBalance in Service");
        Mono<ServerResponseBalance> response = webClient
                .get()
                .uri("accounts/{accountId}/balance", accountId)
                .retrieve()
                .bodyToMono(ServerResponseBalance.class);

        return responseChecker(response, "getBalance");
    }

    @Override
    public ServerResponseBankTransferResult moneyTransfers(int accountId, String timeZone, BankTransfer moneyTransfer) {
        logger.debug("Start - moneyTransfers in Service");
        Mono<ServerResponseBankTransferResult> response = webClient.post()
                .uri("accounts/{accountId}/payments/money-transfers", accountId)
                .header("X-Time-Zone", timeZone)
                .retrieve()
                .bodyToMono(ServerResponseBankTransferResult.class);
        return responseChecker(response, "moneyTransfers");
    }

    @Override
    public ServerResponseTransactions getTransactions(int accountId, LocalDate from, LocalDate to) {
        logger.debug("Start - getTransactions in Service");
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
        Mono<ServerResponseTransactions> response = webClient
                .get()
                .uri(uriFunction)
                .retrieve()
                .bodyToMono(ServerResponseTransactions.class);
        return responseChecker(response, "getTransactions");
    }

    private <T extends ServerResponse<?>> T responseChecker(Mono<T> toCheck, String operation){
        T response = toCheck.block();

        if(response.getStatus().equals("KO")){
            BankAccountException exception = new BankAccountException("STATUS IS KO");
            exception.getErrors().addAll(response.getErrors());
            logger.error("Error in " + operation + " in Service");
            throw exception;
        }
        logger.debug("End - " + operation + " in Service");
        return response;
    }
}
