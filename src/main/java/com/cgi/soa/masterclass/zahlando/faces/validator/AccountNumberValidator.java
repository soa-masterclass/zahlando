package com.cgi.soa.masterclass.zahlando.faces.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import com.cgi.soa.masterclass.zahlando.service.BankWS;

@FacesValidator("accountNumberValidator")
public class AccountNumberValidator implements Validator {

	@Inject
	private BankWS bankws;

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		// TODO Auto-generated method stub

		if (!bankws.getWebService().isAccountExist(Integer.parseInt((String) arg2))) {
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Account existiert nicht!", ""));
		}
	}

}
