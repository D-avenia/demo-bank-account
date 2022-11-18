package com.example.innotek.demobankchallenge.service;

import com.example.innotek.demobankchallenge.model.balance.ServerResponseBalance;
import com.example.innotek.demobankchallenge.model.banktransfer.BankTransfer;
import com.example.innotek.demobankchallenge.model.banktransfer.ServerResponseBankTransferResult;
import com.example.innotek.demobankchallenge.model.transaction.ServerResponseTransactions;

import java.time.LocalDate;

public interface BankAccountService {
    ServerResponseBalance getBalance(int accountId);

    ServerResponseBankTransferResult moneyTransfers(int accountId, String timeZone, BankTransfer moneyTransfer);

    ServerResponseTransactions getTransactions(int accountId, LocalDate from, LocalDate to);

}
