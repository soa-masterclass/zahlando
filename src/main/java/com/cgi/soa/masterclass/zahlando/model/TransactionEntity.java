package com.cgi.soa.masterclass.zahlando.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class TransactionEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn
	private UserEntity sender;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn
	private UserEntity recipient;
	
	@Basic
	@Temporal(TemporalType.DATE)
	private Date transDate;
	
	@Basic
	private String purpose;
	
	@Basic
	private BigDecimal amount;
	
	@OneToOne(mappedBy="transaction", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private FeeEntity fee;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserEntity getSender() {
		return sender;
	}

	public void setSender(UserEntity sender) {
		this.sender = sender;
	}

	public UserEntity getRecipient() {
		return recipient;
	}

	public void setRecipient(UserEntity recipient) {
		this.recipient = recipient;
	}

	public Date getTransDate() {
		return transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public FeeEntity getFee() {
		return fee;
	}

	public void setFee(FeeEntity fee) {
		this.fee = fee;
	}
	
}
	
	

