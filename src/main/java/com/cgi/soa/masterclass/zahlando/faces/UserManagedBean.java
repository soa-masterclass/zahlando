package com.cgi.soa.masterclass.zahlando.faces;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.cgi.soa.masterclass.zahlando.model.UserEntity;
import com.cgi.soa.masterclass.zahlando.service.PersistenceDAO;

@Named
@RequestScoped
public class UserManagedBean {

	@Inject
	PersistenceDAO persistence;

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
}