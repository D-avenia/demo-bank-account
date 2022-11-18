package com.example.innotek.demobankchallenge.controller;

import com.example.innotek.demobankchallenge.model.balance.ServerResponseBalance;
import com.example.innotek.demobankchallenge.model.banktransfer.BankTransfer;
import com.example.innotek.demobankchallenge.model.banktransfer.ServerResponseBankTransferResult;
import com.example.innotek.demobankchallenge.model.transaction.ServerResponseTransactions;
import com.example.innotek.demobankchallenge.service.BankAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping("/bank/account/{accountId}")
public class BankAccountController {
    @Autowired
    private BankAccountService service;

    @GetMapping("/balance")
    @Operation(summary = "Return account balance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The actual balance for the account is retrieved", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE,
                             schema = @Schema(implementation = ServerResponseBalance.class))
                        }
                    )
    })
    public ResponseEntity<ServerResponseBalance> getBalance(@PathVariable final int accountId) {
        Mono<ServerResponseBalance> responseMono = service.getBalance(accountId);
        ServerResponseBalance response = responseMono.block();

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/transactions")
    @Operation(summary = "Return account transactions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transactions retrieved", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ServerResponseTransactions.class))
            })
    })

    public ResponseEntity<ServerResponseTransactions> getTransactions(
            @PathVariable int accountId,
            @RequestParam("from") String from,
            @RequestParam("to") String to
    ) {
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);

        Mono<ServerResponseTransactions> resultService = service.getTransactions(accountId, fromDate, toDate);
        ServerResponseTransactions response = resultService.block();

        return ResponseEntity
                .ok()
                .body(response);
    }

    @PostMapping(value = "/payments/money-transfers", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Transfer from an account to another")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transfer from an account to another", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ServerResponseBankTransferResult.class))})
    })

    public ResponseEntity<ServerResponseBankTransferResult> moneyTransfer(
            @Parameter(description = "The id of the account", example = "1234")
            @PathVariable final int accountId,
            @Parameter(description = "The time zone used to provide the request date fields", example = "Europe/Rome")
            @RequestHeader("X-Time-Zone") String timeZone,
            @Parameter(description = "The data to make a money transfer", required = true, schema = @Schema(implementation = BankTransfer.class))
            @RequestBody BankTransfer moneyTransfer
    ) {
        Mono<ServerResponseBankTransferResult> resultService = service.moneyTransfers(accountId, timeZone, moneyTransfer);
        ServerResponseBankTransferResult response = resultService.block();

        return ResponseEntity
                .ok()
                .body(response);

    }
}
