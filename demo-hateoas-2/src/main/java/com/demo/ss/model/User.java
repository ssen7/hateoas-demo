package com.demo.ss.model;

public class User {
	
	private Integer userId;
	private String name;
	private Account account;
	
	
	public User(Integer userId, String name, Account account) {
		this.userId = userId;
		this.name = name;
		this.account = account;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	
	

}
