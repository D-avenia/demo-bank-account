package com.example.innotek.demobankchallenge.service.impl;

import com.example.innotek.demobankchallenge.exception.BankAccountException;
import com.example.innotek.demobankchallenge.model.balance.ServerResponseBalance;
import com.example.innotek.demobankchallenge.model.banktransfer.BankTransfer;
import com.example.innotek.demobankchallenge.model.banktransfer.ServerResponseBankTransferResult;
import com.example.innotek.demobankchallenge.model.server.ServerErrorResponse;
import com.example.innotek.demobankchallenge.model.transaction.ServerResponseTransactions;
import com.example.innotek.demobankchallenge.service.BankAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriTemplate;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
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
                .bodyToMono(ServerResponseBalance.class)
                .onErrorResume((e -> Mono.error(onError("API000","getBalance","Bilancio non visualizzabile"))));
        return response.block();
    }

    @Override
    public ServerResponseBankTransferResult moneyTransfers(int accountId, String timeZone, BankTransfer moneyTransfer) {
        logger.debug("Start - moneyTransfers in Service");
        Mono<ServerResponseBankTransferResult> response = webClient
                .post()
                .uri("accounts/{accountId}/payments/money-transfers", accountId)
                .header("X-Time-Zone", timeZone)
                .retrieve()
                .bodyToMono(ServerResponseBankTransferResult.class)
                .onErrorResume((e -> Mono.error(onError("API000",
                        "moneyTransfers",
                        "IbanBeneficiario è obbligatorio"))));
        return response.block();
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
                .bodyToMono(ServerResponseTransactions.class)
                .onErrorResume((e -> Mono.error(onError("API000","getTransactions","Transazioni non visualizzabili"))));
        return response.block();
    }

    private BankAccountException onError(String code,
                                         String operation,
                                         String description){
        String message = "Error on " + operation;
        List<ServerErrorResponse> errors = new ArrayList<>();
        errors.add(new ServerErrorResponse(code, description));

        BankAccountException exceptionToReturn = new BankAccountException(message,null,false,false);
        exceptionToReturn.getErrors().addAll(errors);

        logger.error(message);

        throw exceptionToReturn;
    }
}
