package com.cgi.soa.masterclass.zahlando.faces;

import java.math.BigDecimal;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.cgi.soa.masterclass.zahlando.model.FeeEntity;
import com.cgi.soa.masterclass.zahlando.service.PersistenceDAO;

@Named
@RequestScoped
public class FeeManagedBean {

	@Inject
	private PersistenceDAO persistence;

	public List<FeeEntity> getFees() {
		return persistence.getAllFee();
	}
	
	public BigDecimal getBalance(){
		return persistence.getFeeBalance();
	}
}