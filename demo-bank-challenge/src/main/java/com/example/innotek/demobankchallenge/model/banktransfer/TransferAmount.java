package com.example.innotek.demobankchallenge.model.banktransfer;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class  TransferAmount{
		private BigDecimal debtorAmount;
		private String debtorCurrency;
		private BigDecimal creditorAmount;
		private String creditorCurrency;

		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
		private LocalDate creditorCurrencyDate;
		
		private int exchangeRate;
		public BigDecimal getDebtorAmount() {
			return debtorAmount;
		}
		public void setDebtorAmount(BigDecimal debtorAmount) {
			this.debtorAmount = debtorAmount;
		}
		public String getDebtorCurrency() {
			return debtorCurrency;
		}
		public void setDebtorCurrency(String debtorCurrency) {
			this.debtorCurrency = debtorCurrency;
		}
		public BigDecimal getCreditorAmount() {
			return creditorAmount;
		}
		public void setCreditorAmount(BigDecimal creditorAmount) {
			this.creditorAmount = creditorAmount;
		}
		public String getCreditorCurrency() {
			return creditorCurrency;
		}
		public void setCreditorCurrency(String creditorCurrency) {
			this.creditorCurrency = creditorCurrency;
		}
		public LocalDate getCreditorCurrencyDate() {
			return creditorCurrencyDate;
		}
		public void setCreditorCurrencyDate(LocalDate creditorCurrencyDate) {
			this.creditorCurrencyDate = creditorCurrencyDate;
		}
		public int getExchangeRate() {
			return exchangeRate;
		}
		public void setExchangeRate(int exchangeRate) {
			this.exchangeRate = exchangeRate;
		}

}
