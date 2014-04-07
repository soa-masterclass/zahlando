package com.cgi.soa.masterclass.zahlando.faces.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.cgi.soa.masterclass.zahlando.model.UserEntity;
import com.cgi.soa.masterclass.zahlando.service.PersistenceDAO;

@FacesConverter(value="userConverter")
public class UserConverter implements Converter{
	
	@Inject
	PersistenceDAO persistence;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String id) {
		return persistence.findUserById(Integer.valueOf(id));
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object userEntity) {
		return ((UserEntity)userEntity).getId().toString();
	}
	
}
