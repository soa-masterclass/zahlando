package com.cgi.soa.masterclass.zahlando.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.cgi.soa.masterclass.zahlando.model.FeeEntity;
import com.cgi.soa.masterclass.zahlando.model.TransactionEntity;
import com.cgi.soa.masterclass.zahlando.model.UserEntity;
import com.cgi.soa.masterclass.zahlando.samplebank.ws.BankWeb;

@Stateless
public class PersistenceDAO {

	private static final Integer ACCOUNT_NUMBER = 35;

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private BankWeb bankService;

	public List<UserEntity> getAllUser() {
		return entityManager.createQuery(
				"SELECT user FROM " + UserEntity.class.getName() + " user ",
				UserEntity.class).getResultList();
	}

	public List<FeeEntity> getAllFee() {
		return entityManager.createQuery(
				"SELECT fee FROM " + FeeEntity.class.getName() + " fee ",
				FeeEntity.class).getResultList();
	}
	
	public BigDecimal getFeeBalance(){
		return entityManager.createQuery("SELECT SUM(fee.amount) FROM " + FeeEntity.class.getName()+" fee ", BigDecimal.class).getSingleResult();
	}

	public void createUser(UserEntity user) {
		user.setBalance(BigDecimal.ZERO);
		entityManager.persist(user);
		entityManager.flush();
	}

	public UserEntity findUserById(Integer id) {
		return entityManager
				.createQuery(
						"SELECT user FROM " + UserEntity.class.getName()
								+ " user WHERE user.id = :id ",
						UserEntity.class).setParameter("id", id)
				.getSingleResult();
	}

	public UserEntity findUserByEmail(String email) {
		return entityManager
				.createQuery(
						"SELECT user FROM " + UserEntity.class.getName()
								+ " user WHERE user.email = :email ",
						UserEntity.class).setParameter("email", email)
				.getSingleResult();
	}

	public void deposit(TransactionEntity transaction) {
		transaction.setTransDate(Calendar.getInstance().getTime());

		FeeEntity fee = new FeeEntity();
		fee.setAmount(BigDecimal.ZERO);
		fee.setTransaction(transaction);

		transaction.setFee(fee);

		UserEntity sender = transaction.getSender();

		sender.getTransactions().add(transaction);

		sender.setBalance(sender.getBalance().add(transaction.getAmount()));

		bankService.transfer(transaction.getSender().getAccNumber(),
				ACCOUNT_NUMBER, transaction.getPurpose(),
				transaction.getAmount());

		entityManager.merge(sender);
		entityManager.flush();
	}

	public void clearing(TransactionEntity transaction) {
		transaction.setTransDate(Calendar.getInstance().getTime());

		transaction.setAmount(transaction.getAmount().multiply(
				BigDecimal.valueOf(-1l)));

		FeeEntity fee = new FeeEntity();
		fee.setAmount(BigDecimal.ZERO);
		fee.setTransaction(transaction);

		transaction.setFee(fee);

		UserEntity sender = transaction.getSender();

		sender.getTransactions().add(transaction);

		sender.setBalance(sender.getBalance().add(transaction.getAmount()));

		bankService.transfer(ACCOUNT_NUMBER, transaction.getSender()
				.getAccNumber(), transaction.getPurpose(), transaction
				.getAmount().multiply(BigDecimal.valueOf(-1l)));

		entityManager.merge(sender);
		entityManager.flush();
	}

	public void pay(TransactionEntity transaction) {
		transaction.setTransDate(Calendar.getInstance().getTime());

		transaction.setAmount(transaction.getAmount().multiply(
				BigDecimal.valueOf(-1l)));

		FeeEntity feeSender = new FeeEntity();
		feeSender.setAmount(BigDecimal.ZERO);
		feeSender.setTransaction(transaction);

		transaction.setFee(feeSender);

		UserEntity sender = transaction.getSender();

		sender.getTransactions().add(transaction);

		sender.setBalance(sender.getBalance().add(transaction.getAmount()));


		TransactionEntity transactionRecipient = new TransactionEntity();

		transactionRecipient.setTransDate(transaction.getTransDate());
		transactionRecipient.setPurpose(transaction.getPurpose());
		transactionRecipient.setRecipient(transaction.getSender());
		transactionRecipient.setSender(transaction.getRecipient());

		transactionRecipient.setAmount(transaction.getAmount().multiply(
				BigDecimal.valueOf(-1l)));

		FeeEntity feeRecipient = new FeeEntity();
		BigDecimal feeAmount = transactionRecipient.getAmount().multiply(
				BigDecimal.valueOf(0.03));
		feeRecipient.setAmount(feeAmount);
		feeRecipient.setTransaction(transactionRecipient);

		transactionRecipient.setAmount(transactionRecipient.getAmount()
				.subtract(feeAmount));

		transactionRecipient.setFee(feeRecipient);

		UserEntity recipient = transactionRecipient.getSender();

		recipient.getTransactions().add(transactionRecipient);

		recipient.setBalance(recipient.getBalance().add(
				transactionRecipient.getAmount()));


		entityManager.merge(sender);
		entityManager.merge(recipient);

		entityManager.flush();
	}

	public Boolean isEmailExist(String email) {
		return entityManager
				.createQuery(
						"SELECT user FROM " + UserEntity.class.getName()
								+ " user WHERE user.email = :email",
						UserEntity.class).setParameter("email", email)
				.getResultList().size() > 0;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public BankWeb getBankService() {
		return bankService;
	}

	public void setBankService(BankWeb bankService) {
		this.bankService = bankService;
	}
}
