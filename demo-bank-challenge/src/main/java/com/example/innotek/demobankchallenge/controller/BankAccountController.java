package com.example.innotek.demobankchallenge.controller;

import com.example.innotek.demobankchallenge.model.balance.Balance;
import com.example.innotek.demobankchallenge.model.balance.ServerResponseBalance;
import com.example.innotek.demobankchallenge.model.banktransfer.BankTransfer;
import com.example.innotek.demobankchallenge.model.banktransfer.BankTransferResult;
import com.example.innotek.demobankchallenge.model.banktransfer.ServerResponseBankTransferResult;
import com.example.innotek.demobankchallenge.mapper.BankAccountMapper;
import com.example.innotek.demobankchallenge.model.transaction.ServerResponseTransactions;
import com.example.innotek.demobankchallenge.model.transaction.TransactionPayload;
import com.example.innotek.demobankchallenge.service.BankAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping("/bank/account/{accountId}")
@RequiredArgsConstructor
public class BankAccountController {
    private final BankAccountService service;

    private final BankAccountMapper mapper;

    @GetMapping("/balance")
    @Operation(summary = "Return account balance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The actual balance for the account is retrieved", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE)})
    })
    public ResponseEntity<ServerResponseBalance> getBalance(@PathVariable final int accountId) {
        Balance resultBalance = service.getBalance(accountId);

        ServerResponseBalance result = mapper.toResponseBalance(resultBalance);

        return ResponseEntity
                .ok()
                .body(result);
    }

    @GetMapping("/getTransactions")
    @Operation(summary = "Return account transactions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transactions retrieved", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE)})
    })

    public ResponseEntity<ServerResponseTransactions> getTransactions(
            @PathVariable int accountId,
            @PathVariable LocalDate from,
            @PathVariable LocalDate to) {
        TransactionPayload resultService = service.getTransactions(accountId, from, to);

        service.persistTransactions(accountId,resultService.getList());

        ServerResponseTransactions result = mapper.toResponseTransactions(resultService);
        return ResponseEntity
                .ok()
                .body(result);
    }

    @PostMapping("/moneyTransfer")
    @Operation(summary = "Transfer from an account to another")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transfer from an account to another", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE)})
    })

    public ResponseEntity<ServerResponseBankTransferResult> moneyTransfer(
            @Parameter(description = "The id of the account", example = "1234")
            @PathVariable final int accountId,
            @Parameter(description = "The time zone used to provide the request date fields", example = "Europe/Rome")
            @RequestHeader("X-Time-Zone") String timeZone,
            @Parameter(description = "The data to make a money transfer", required = true, schema = @Schema(implementation = BankTransfer.class))
            @RequestBody BankTransfer moneyTransfer
    ) {
        BankTransferResult resultService  =  service.moneyTransfers(accountId,timeZone, moneyTransfer);

        ServerResponseBankTransferResult result = mapper.toResponseBankTransfer(resultService);
        return ResponseEntity
                .ok()
                .body(result);

    }
}
