package com.cgi.soa.masterclass.zahlando.service;

import javax.ejb.Stateless;
import javax.xml.ws.WebServiceRef;

import com.cgi.soa.masterclass.zahlando.samplebank.ws.BankWeb;
import com.cgi.soa.masterclass.zahlando.samplebank.ws.BankWebService;

/**
 * Session Bean implementation class BankWS
 */
@Stateless
public class BankWS {

	@WebServiceRef
	BankWebService service;

	public BankWeb getWebService(){
		return service.getBankWebPort();
	}

}


