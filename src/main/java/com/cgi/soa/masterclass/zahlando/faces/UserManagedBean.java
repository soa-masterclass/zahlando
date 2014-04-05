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
import com.cgi.soa.masterclass.zahlando.service.BankWS;
import com.cgi.soa.masterclass.zahlando.service.PersistenceDAO;

@Named
@RequestScoped
public class UserManagedBean {

	@Inject
	PersistenceDAO persistence;

	@Inject
	FacesContext context;
	
	@Inject
	BankWS bankWs;

	UserEntity user;

	public List<UserEntity> getUsers() {
		return persistence.getAllUser();
	}

	public String createUser() {
		persistence.createUser(user);
		return "/users/index.html?faces-redirect=true";
	}

	public UserEntity getUser() {
		if (user == null) {
			user = new UserEntity();
		}
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
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
		
		if(!bankWs.getWebService().isAccountOwner(Integer.valueOf(accountNumber), accountFirstName, accountLastName)){
			FacesMessage msg = new FacesMessage("This account is not belong to the correct owner.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(accountNumberUi.getClientId(), msg);
			context.renderResponse();
		}
	}
}