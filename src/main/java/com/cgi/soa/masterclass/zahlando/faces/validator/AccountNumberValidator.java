package com.cgi.soa.masterclass.zahlando.faces.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import com.cgi.soa.masterclass.zahlando.samplebank.ws.BankWeb;

@FacesValidator("accountNumberValidator")
public class AccountNumberValidator implements Validator {

	@Inject
	private BankWeb bankService;

	@Override
	public void validate(FacesContext context, UIComponent ui, Object accNumber)
			throws ValidatorException {
		if (!bankService.isAccountExist((Integer) accNumber)) {
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Account existiert nicht!", ""));
		}
	}

}
