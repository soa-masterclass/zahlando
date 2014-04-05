package com.cgi.soa.masterclass.zahlando.service;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.cgi.soa.masterclass.zahlando.model.UserEntity;

@Stateless
public class PersistenceDAO {

	@PersistenceContext
	EntityManager entityManager;
	
	public List<UserEntity> getAllUser(){
		return entityManager.createQuery("SELECT user FROM "+UserEntity.class.getName()+" user ", UserEntity.class).getResultList();
	}
	
	public void createUser(UserEntity user){
		user.setBalance(BigDecimal.ZERO);
		entityManager.persist(user);
		entityManager.flush();
	}
}
