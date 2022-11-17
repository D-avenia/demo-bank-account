package com.example.innotek.demobankchallenge.controller;

import com.example.innotek.demobankchallenge.mapper.BankAccountMapper;
import com.example.innotek.demobankchallenge.model.balance.Balance;
import com.example.innotek.demobankchallenge.model.banktransfer.BankTransfer;
import com.example.innotek.demobankchallenge.model.banktransfer.BankTransferResult;
import com.example.innotek.demobankchallenge.model.transaction.TransactionPayload;
import com.example.innotek.demobankchallenge.service.BankAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping("/bank/account/{accountId}")
public class BankAccountController {
    @Autowired
    private BankAccountService service;

    @Autowired
    private BankAccountMapper mapper;

    @GetMapping("/balance")
    @Operation(summary = "Return account balance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The actual balance for the account is retrieved", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE)})
    })
    public ResponseEntity<Mono<Balance>> getBalance(@PathVariable final int accountId) {
        Mono<Balance> resultBalance = service.getBalance(accountId);

        //ServerResponseBalance result = mapper.toResponseBalance(resultBalance);

        return ResponseEntity
                .ok()
                .body(resultBalance);
    }

    @GetMapping("/getTransactions")
    @Operation(summary = "Return account transactions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transactions retrieved", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE)})
    })

    public ResponseEntity<Flux<TransactionPayload>> getTransactions(
            @PathVariable int accountId,
            @PathVariable LocalDate from,
            @PathVariable LocalDate to) {
        Flux<TransactionPayload> resultService = service.getTransactions(accountId, from, to);

        //service.persistTransactions(accountId,resultService.);

        //ServerResponseTransactions result = mapper.toResponseTransactions(resultService);
        return ResponseEntity
                .ok()
                .body(resultService);
    }

    @PostMapping("/moneyTransfer")
    @Operation(summary = "Transfer from an account to another")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transfer from an account to another", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE)})
    })

    public ResponseEntity<Mono<BankTransferResult>> moneyTransfer(
            @Parameter(description = "The id of the account", example = "1234")
            @PathVariable final int accountId,
            @Parameter(description = "The time zone used to provide the request date fields", example = "Europe/Rome")
            @RequestHeader("X-Time-Zone") String timeZone,
            @Parameter(description = "The data to make a money transfer", required = true, schema = @Schema(implementation = BankTransfer.class))
            @RequestBody BankTransfer moneyTransfer
    ) {
        Mono<BankTransferResult> resultService  =  service.moneyTransfers(accountId,timeZone, moneyTransfer);

        //ServerResponseBankTransferResult result = mapper.toResponseBankTransfer(resultService);
        return ResponseEntity
                .ok()
                .body(resultService);

    }
}
