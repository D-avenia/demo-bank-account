package com.example.innotek.demobankchallenge.model;

import java.math.BigDecimal;

public class  Fee{
		private String feeCode;
		private String description;
		private BigDecimal amount;
		private String currency;
		
		
		public String getFeeCode() {
			return feeCode;
		}
		public void setFeeCode(String feeCode) {
			this.feeCode = feeCode;
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
		
		
}
