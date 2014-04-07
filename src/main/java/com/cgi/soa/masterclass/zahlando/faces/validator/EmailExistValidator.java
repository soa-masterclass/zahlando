package com.cgi.soa.masterclass.zahlando.faces.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import com.cgi.soa.masterclass.zahlando.model.UserEntity;
import com.cgi.soa.masterclass.zahlando.service.PersistenceDAO;

@FacesValidator("emailExistValidator")
public class EmailExistValidator implements Validator {
	
	@Inject PersistenceDAO persistence;

	@Override
	public void validate(FacesContext context, UIComponent ui, Object email)
			throws ValidatorException {
		if (!persistence.isEmailExist(((UserEntity) email).getEmail())) {
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"User of this email is not exist.", ""));
		}
	}

}
