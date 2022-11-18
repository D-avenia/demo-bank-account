package com.example.innotek.demobankchallenge.service;

import com.example.innotek.demobankchallenge.model.balance.ServerResponseBalance;
import com.example.innotek.demobankchallenge.model.banktransfer.BankTransfer;
import com.example.innotek.demobankchallenge.model.banktransfer.ServerResponseBankTransferResult;
import com.example.innotek.demobankchallenge.model.transaction.ServerResponseTransactions;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface BankAccountService {
    Mono<ServerResponseBalance> getBalance(int accountId);

    Mono<ServerResponseBankTransferResult> moneyTransfers(int accountId, String timeZone, BankTransfer moneyTransfer);

    Mono<ServerResponseTransactions> getTransactions(int accountId, LocalDate from, LocalDate to);

}
