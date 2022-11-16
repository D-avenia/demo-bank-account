package com.example.innotek.demobankchallenge.model.banktransfer;


import com.example.innotek.demobankchallenge.model.Fee;
import com.example.innotek.demobankchallenge.model.PersonCreditor;
import com.example.innotek.demobankchallenge.model.PersonDebtor;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class  BankTransferResult{
	
		private String moneyTransferId;
		
		
		private String status;
		private String direction;
		private PersonCreditor creditor;
		private PersonDebtor debtor;
		private String cro;
		private String trn;
		
		private String uri;
		
		private String description;
		
		
		
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
		private LocalDateTime createdDatetime;
		
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
		private LocalDateTime accountedDatetime;
		
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
		private LocalDate debtorValueDate;
		
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
		private LocalDate creditorValueDate;
		
		
		private TransferAmount amount;
		private boolean isUrgent;
		private boolean isInstant;
		private String feeType;
		private String feeAccountId;
		private List<Fee> fees;
		private boolean hasTaxRelief;
		
		
		
		public String getMoneyTransferId() {
			return moneyTransferId;
		}
		public void setMoneyTransferId(String moneyTransferId) {
			this.moneyTransferId = moneyTransferId;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getDirection() {
			return direction;
		}
		public void setDirection(String direction) {
			this.direction = direction;
		}
		public PersonCreditor getCreditor() {
			return creditor;
		}
		public void setCreditor(PersonCreditor creditor) {
			this.creditor = creditor;
		}
		public PersonDebtor getDebtor() {
			return debtor;
		}
		public void setDebtor(PersonDebtor debtor) {
			this.debtor = debtor;
		}
		public String getCro() {
			return cro;
		}
		public void setCro(String cro) {
			this.cro = cro;
		}
		public String getTrn() {
			return trn;
		}
		public void setTrn(String trn) {
			this.trn = trn;
		}
		public String getUri() {
			return uri;
		}
		public void setUri(String uri) {
			this.uri = uri;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public LocalDateTime getCreatedDatetime() {
			return createdDatetime;
		}
		public void setCreatedDatetime(LocalDateTime createdDatetime) {
			this.createdDatetime = createdDatetime;
		}
		public LocalDateTime getAccountedDatetime() {
			return accountedDatetime;
		}
		public void setAccountedDatetime(LocalDateTime accountedDatetime) {
			this.accountedDatetime = accountedDatetime;
		}
		public LocalDate getDebtorValueDate() {
			return debtorValueDate;
		}
		public void setDebtorValueDate(LocalDate debtorValueDate) {
			this.debtorValueDate = debtorValueDate;
		}
		public LocalDate getCreditorValueDate() {
			return creditorValueDate;
		}
		public void setCreditorValueDate(LocalDate creditorValueDate) {
			this.creditorValueDate = creditorValueDate;
		}
		public TransferAmount getAmount() {
			return amount;
		}
		public void setAmount(TransferAmount amount) {
			this.amount = amount;
		}
		public boolean getIsUrgent() {
			return isUrgent;
		}
		public void setIsUrgent(boolean isUrgent) {
			this.isUrgent = isUrgent;
		}
		public boolean getIsInstant() {
			return isInstant;
		}
		public void setIsInstant(boolean isInstant) {
			this.isInstant = isInstant;
		}
		public String getFeeType() {
			return feeType;
		}
		public void setFeeType(String feeType) {
			this.feeType = feeType;
		}
		public String getFeeAccountId() {
			return feeAccountId;
		}
		public void setFeeAccountId(String feeAccountId) {
			this.feeAccountId = feeAccountId;
		}
		public List<Fee> getFees() {
			return fees;
		}
		public void setFees(List<Fee> fees) {
			this.fees = fees;
		}
		public boolean getIsHasTaxRelief() {
			return hasTaxRelief;
		}
		public void setIsHasTaxRelief(boolean hasTaxRelief) {
			this.hasTaxRelief = hasTaxRelief;
		}
				
		
		

}
