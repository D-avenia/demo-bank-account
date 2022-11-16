package com.example.innotek.demobankchallenge.model.banktransfer;


import com.example.innotek.demobankchallenge.model.PersonCreditor;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class  BankTransfer{
		private PersonCreditor creditor;
		
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
		private LocalDate executionDate;
		
		private String uri;
		private String description;
		private BigDecimal  amount;
		
		private String currency;
		
		
		private boolean isUrgent;
		private boolean isInstant;
		private String feeType;
		private String feeAccountId;
		private TaxRelief taxRelief;
		public PersonCreditor getCreditor() {
			return creditor;
		}
		public void setCreditor(PersonCreditor creditor) {
			this.creditor = creditor;
		}
		public LocalDate getExecutionDate() {
			return executionDate;
		}
		public void setExecutionDate(LocalDate executionDate) {
			this.executionDate = executionDate;
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
		public BigDecimal getAmount() {
			return amount;
		}
		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}
		public String getCurrency() {
			return currency;
		}
		public void setCurrency(String currency) {
			this.currency = currency;
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
		public TaxRelief getTaxRelief() {
			return taxRelief;
		}
		public void setTaxRelief(TaxRelief taxRelief) {
			this.taxRelief = taxRelief;
		}
		
		
		
		
		
		

}
