package com.cgi.soa.masterclass.zahlando.model;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class UserEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	
	@Basic
	String email;
	
	@Basic
	String firstname;
	
	@Basic
	String lastname;
	
	@Basic
	Integer accNumber;
	
	@Basic
	String accFirstname;
	
	@Basic
	String accLastname;
	
	@Basic
	BigDecimal balance;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Integer getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(Integer accNumber) {
		this.accNumber = accNumber;
	}

	public String getAccFirstname() {
		return accFirstname;
	}

	public void setAccFirstname(String accFirstname) {
		this.accFirstname = accFirstname;
	}

	public String getAccLastname() {
		return accLastname;
	}

	public void setAccLastname(String accLastname) {
		this.accLastname = accLastname;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
