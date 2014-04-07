package com.cgi.soa.masterclass.zahlando.faces;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.cgi.soa.masterclass.zahlando.model.TransactionEntity;
import com.cgi.soa.masterclass.zahlando.model.UserEntity;
import com.cgi.soa.masterclass.zahlando.samplebank.ws.BankWeb;
import com.cgi.soa.masterclass.zahlando.service.PersistenceDAO;

@Named
@ViewScoped
public class TransactionManagedBean {

	private UserEntity user;
	
	private TransactionEntity transaction;
	
	@Inject
	private PersistenceDAO persistence;
	
	@Inject
	private BankWeb bankService;
	
	@Inject 
	private FacesContext context;
	
	private Integer userId;
	
	public TransactionManagedBean(){
		transaction = new TransactionEntity();
	}
	
	public String deposit(){
		transaction.setSender(user);
		transaction.setRecipient(user);
		persistence.deposit(transaction);
		return "/transactions/index.xhtml?faces-redirect=true&user="+user.getId();
	}
	
	public String clearing(){
		transaction.setSender(user);
		transaction.setRecipient(user);
		persistence.clearing(transaction);
		return "/transactions/index.xhtml?faces-redirect=true&user="+user.getId();
	}
	
	public String pay(){
		transaction.setSender(user);
		persistence.pay(transaction);
		return "/transactions/index.xhtml?faces-redirect=true&user="+user.getId();
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
		this.userId = user.getId();
	}

	public TransactionEntity getTransaction() {
		return transaction;
	}

	public void setTransaction(TransactionEntity transaction) {
		this.transaction = transaction;
	}

	public PersistenceDAO getPersistence() {
		return persistence;
	}

	public void setPersistence(PersistenceDAO persistence) {
		this.persistence = persistence;
	}

	public BankWeb getBankService() {
		return bankService;
	}

	public void setBankService(BankWeb bankService) {
		this.bankService = bankService;
	}

	public FacesContext getContext() {
		return context;
	}

	public void setContext(FacesContext context) {
		this.context = context;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
