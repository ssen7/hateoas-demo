package com.demo.ss.model;

public class Account {
	
	private Integer accountNo;
	private Integer balance;
	
	public Account(Integer accountNo, Integer balance){
		this.accountNo = accountNo;
		this.balance = balance;
	}
	
	
	public Integer getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(Integer accountNo) {
		this.accountNo = accountNo;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	

}
