package com.cgi.soa.masterclass.zahlando.service.producer;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import com.cgi.soa.masterclass.zahlando.samplebank.ws.BankWeb;
import com.cgi.soa.masterclass.zahlando.service.BankWS;

public class BankWebServiceProducer {
	
	@Inject
	BankWS bankWs;
	
	@Produces
	@RequestScoped
	public BankWeb getBankWebService() {
		return bankWs.getWebService();
	}
}
