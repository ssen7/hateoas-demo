package com.demo.ss.service;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.demo.ss.model.Account;
import com.demo.ss.model.User;
@Service
public class BankService {

	Map<Integer, Account> accounts = new LinkedHashMap<Integer, Account>();
	Map<Integer, User> users = new LinkedHashMap<Integer, User>();

	public BankService(){

		Account account1 = new Account(1, 1000);
		Account account2 = new Account(2, 1000);
		Account account3 = new Account(3, 1000);
		accounts.put(account1.getAccountNo(), account1);
		accounts.put(account2.getAccountNo(), account2);
		accounts.put(account3.getAccountNo(), account3);

		User user1 = new User(1, "Jon", account1);
		User user2 = new User(2, "Patrick", account2);
		User user3 = new User(3, "Harris", account3);
		users.put(user1.getUserId(), user1);
		users.put(user2.getUserId(), user2);
		users.put(user3.getUserId(), user3);

	}

	public Collection<User> getAllUsers(){

		return users.values();

	}

	public User getUser(Integer userId){

		return users.get(userId);
	}

	public Collection<Account> getAllAccounts(){

		return accounts.values();

	}

	public Account getAccount(Integer accountNo){

		return accounts.get(accountNo);
	}

	public User getUserWithBalance(Integer id, Integer amount){

		User user = users.get(id);
		if(user.getAccount().getBalance()>0 && amount<=user.getAccount().getBalance()){
			user.getAccount().setBalance(user.getAccount().getBalance() - amount);
		}
		return user;
	}

	public User getUserWithBalanceOnDeposit(Integer id, Integer amount){

		User user = users.get(id);

		user.getAccount().setBalance(user.getAccount().getBalance() + amount);

		return user;
	}

}
