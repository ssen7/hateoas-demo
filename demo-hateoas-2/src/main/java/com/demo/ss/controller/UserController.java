package com.demo.ss.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.ss.model.User;
import com.demo.ss.service.BankService;

@Controller
public class UserController {
	
	@Autowired
	private BankService bankService;
	
	
	@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody 
	public Collection<Resource<User>> getAllUsers(){
		
		Collection<User> users = bankService.getAllUsers();
		List<Resource<User>> resources = new LinkedList<Resource<User>>();
		for(User user : users){
			resources.add(getUserResource(user));
		}
		return resources;
	}
	
	@RequestMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public Resource<User> getUser(@PathVariable(value = "id")Integer userId){
		
		User user = bankService.getUser(userId);
		return getUserResource(user);
		
	}
	
	private Resource<User> getUserResource(User user){
		Resource<User> resource = new Resource<User>(user);
		
		//link with itself
		resource.add(linkTo(methodOn(UserController.class).getUser(user.getUserId())).withSelfRel());
		
		resource.add(linkTo(methodOn(AccountController.class).getAccount(user.getAccount().getAccountNo())).withRel("account Number"));
		
		if(user.getAccount().getBalance()>0)
			resource.add(linkTo(methodOn(UserController.class).withdraw(user.getUserId(), 0)).withRel("withdrawable"));//TO DO
		else{
			resource.add(linkTo(methodOn(UserController.class).deposit(user.getUserId(), 0)).withRel("deposit.first!"));//TO DO
		}
		
		resource.add(linkTo(methodOn(UserController.class).deposit(user.getUserId(), 0)).withRel("deposit"));
		
		return resource;
		
		
	}
	
	@RequestMapping(value = "/user/{id}/withdraw/{amount}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	@ResponseBody
	public Resource<User> withdraw(@PathVariable(value = "id")Integer id, @PathVariable(value = "amount")Integer amount){
		
		User user = bankService.getUserWithBalance(id, amount);
		return getUserResource(user);
				
	}
	
	@RequestMapping(value = "/user/{id}/deposit/{amount}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	@ResponseBody
	public Resource<User> deposit(@PathVariable(value = "id")Integer id, @PathVariable(value = "amount")Integer amount){
		
		User user = bankService.getUserWithBalanceOnDeposit(id, amount);
		return getUserResource(user);
				
	}
	

}
