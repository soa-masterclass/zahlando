package com.cgi.soa.masterclass.zahlando.faces.validator;

import java.math.BigDecimal;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewParameter;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import com.cgi.soa.masterclass.zahlando.model.UserEntity;
import com.cgi.soa.masterclass.zahlando.samplebank.ws.BankWeb;
import com.cgi.soa.masterclass.zahlando.service.PersistenceDAO;

@FacesValidator("balanceCoveredValidator")
public class BalanceCoveredValidator implements Validator {

	@Inject
	private BankWeb bankService;
	
	@Inject PersistenceDAO persistence;

	@Override
	public void validate(FacesContext context, UIComponent ui, Object amount)
			throws ValidatorException {
		UserEntity user = (UserEntity) ((UIViewParameter)context.getViewRoot().findComponent("userId")).getLocalValue();
		if (!bankService.isBalanceCovered(user.getAccNumber(), (BigDecimal) amount)) {
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Account balance is not covered for this amount of money.", ""));
		}
	}

}
