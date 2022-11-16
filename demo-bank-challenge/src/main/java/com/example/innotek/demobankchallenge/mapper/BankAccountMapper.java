package com.example.innotek.demobankchallenge.mapper;

import com.example.innotek.demobankchallenge.model.balance.Balance;
import com.example.innotek.demobankchallenge.model.balance.ServerResponseBalance;
import com.example.innotek.demobankchallenge.model.banktransfer.BankTransferResult;
import com.example.innotek.demobankchallenge.model.banktransfer.ServerResponseBankTransferResult;
import com.example.innotek.demobankchallenge.model.transaction.ServerResponseTransactions;
import com.example.innotek.demobankchallenge.model.transaction.TransactionPayload;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI,
		uses = BankAccountMapper.class,
		injectionStrategy = InjectionStrategy.CONSTRUCTOR)
@Component
public interface BankAccountMapper {

	ServerResponseBalance toResponseBalance(Balance sourceResponse);
	ServerResponseTransactions toResponseTransactions(TransactionPayload sourceResponse);
	ServerResponseBankTransferResult toResponseBankTransfer(BankTransferResult sourceResponse);
	
	//TransactionHistory toTransactionHistory (Transaction source, int accountId);
	
}
