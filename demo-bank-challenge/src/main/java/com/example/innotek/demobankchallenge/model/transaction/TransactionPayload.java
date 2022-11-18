package com.example.innotek.demobankchallenge.model.transaction;

import java.util.List;

public class TransactionPayload{
	private List<Transaction> list ;

	public List<Transaction> getList() {
		return list;
	}

	public void setList(List<Transaction> list) {
		this.list = list;
	}

}