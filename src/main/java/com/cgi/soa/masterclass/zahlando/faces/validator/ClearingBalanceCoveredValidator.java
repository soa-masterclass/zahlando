package com.cgi.soa.masterclass.zahlando.faces.validator;

import java.math.BigDecimal;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewParameter;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.cgi.soa.masterclass.zahlando.model.UserEntity;

@FacesValidator("clearingBalanceCoveredValidator")
public class ClearingBalanceCoveredValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent ui, Object amount)
			throws ValidatorException {
		UserEntity user = (UserEntity) ((UIViewParameter)context.getViewRoot().findComponent("userId")).getLocalValue();
		if (user.getBalance().compareTo((BigDecimal) amount) < 0) {
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Account balance is not covered for this amount of money.", ""));
		}
	}

}
