package com.example.innotek.demobankchallenge.model;

public class PersonCreditor extends Person {

	private Account account;
	private Address address;

	public Account getAccount() {
		return account;
	}

	public void setAccount(final Account account) {
		this.account = account;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(final Address address) {
		this.address = address;
	}
}
