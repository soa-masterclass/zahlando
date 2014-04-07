package com.cgi.soa.masterclass.zahlando.faces;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.cgi.soa.masterclass.zahlando.model.UserEntity;
import com.cgi.soa.masterclass.zahlando.samplebank.ws.BankWeb;
import com.cgi.soa.masterclass.zahlando.service.PersistenceDAO;

@Named
@RequestScoped
public class UserManagedBean {

	@Inject
	private PersistenceDAO persistence;

	@Inject
	private FacesContext context;
	
	@Inject
	private BankWeb bankService;

	private UserEntity user;
	
	public UserManagedBean(){
		user = new UserEntity();
	}

	public List<UserEntity> getUsers() {
		return persistence.getAllUser();
	}

	public String createUser() {
		persistence.createUser(user);
		return "/users/index.html?faces-redirect=true";
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public PersistenceDAO getPersistence() {
		return persistence;
	}

	public void setPersistence(PersistenceDAO persistence) {
		this.persistence = persistence;
	}

	public FacesContext getContext() {
		return context;
	}

	public void setContext(FacesContext context) {
		this.context = context;
	}

	public BankWeb getBankService() {
		return bankService;
	}

	public void setBankService(BankWeb bankService) {
		this.bankService = bankService;
	}

	public void validateAccount(ComponentSystemEvent event) {
		UIComponent components = event.getComponent();

		UIInput accountNumberUi = (UIInput) components
				.findComponent("accountNumber");
		UIInput accountFirstNameUi = (UIInput) components
				.findComponent("accountFirstName");
		UIInput accountLastNameUi = (UIInput) components
				.findComponent("accountLastName");

		String accountNumber = accountNumberUi.getLocalValue() == null ? ""
				: accountNumberUi.getLocalValue().toString();
		String accountFirstName = accountNumberUi.getLocalValue() == null ? ""
				: accountFirstNameUi.getLocalValue().toString();
		String accountLastName = accountNumberUi.getLocalValue() == null ? ""
				: accountLastNameUi.getLocalValue().toString();

		if (accountNumber.isEmpty() || accountFirstName.isEmpty()
				|| accountLastName.isEmpty()) {
			return;
		}
		
		if(!bankService.isAccountOwner(Integer.valueOf(accountNumber), accountFirstName, accountLastName)){
			FacesMessage msg = new FacesMessage("This account is not belong to the correct owner.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(accountNumberUi.getClientId(), msg);
			context.renderResponse();
		}
	}
}