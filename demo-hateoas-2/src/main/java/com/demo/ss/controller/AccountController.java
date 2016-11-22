package com.demo.ss.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.ss.model.Account;
import com.demo.ss.service.BankService;


@Controller
public class AccountController {
	
	@Autowired
	private BankService bankService;
	
	@RequestMapping(value = "/accounts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Collection<Resource<Account>> getAccounts(){
		
		Collection<Account> accounts = bankService.getAllAccounts();
		List<Resource<Account>> resources = new LinkedList<Resource<Account>>();
		
		for(Account account: accounts){
			
			resources.add(getAccountResource(account));
			
		}
		
		return resources;
		
	}
	
	@RequestMapping(value = "/account/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Resource<Account> getAccount(@PathVariable(value="id")Integer accountNo){
		
		Account account = bankService.getAccount(accountNo);
		return getAccountResource(account);
		
	}
	
	private Resource<Account> getAccountResource(Account account){
		
		Resource<Account> resource = new Resource<Account>(account);
		
		resource.add(linkTo(methodOn(AccountController.class).getAccount(account.getAccountNo())).withSelfRel());
		
		return resource;
	}
	
	
	

}
