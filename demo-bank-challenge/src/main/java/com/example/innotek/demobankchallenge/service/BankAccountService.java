package com.example.innotek.demobankchallenge.service;

import com.example.innotek.demobankchallenge.model.balance.Balance;
import com.example.innotek.demobankchallenge.model.banktransfer.BankTransfer;
import com.example.innotek.demobankchallenge.model.banktransfer.BankTransferResult;
import com.example.innotek.demobankchallenge.model.transaction.Transaction;
import com.example.innotek.demobankchallenge.model.transaction.TransactionPayload;

import java.time.LocalDate;
import java.util.List;

public interface BankAccountService {
    Balance getBalance(int accountId);

    BankTransferResult moneyTransfers(int accountId, String timeZone, BankTransfer moneyTransfer);

    TransactionPayload getTransactions(int accountId, LocalDate from, LocalDate to);

    void persistTransactions(int accountId, List<Transaction> list);
}
