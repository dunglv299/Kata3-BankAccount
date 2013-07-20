package com.example.bankaccounttest;

import java.util.List;

import android.test.AndroidTestCase;

import com.example.bankaccount.TransactionDTO;
import com.example.bankaccount.TransactionDao;

public class TransactionDaoTest extends AndroidTestCase {
	private TransactionDao transactionDao;

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		transactionDao = new TransactionDao(getContext(), null);
	}

	public void testInsertTransaction() {
		String accountNumber = "0123456789";
		float amount = 100;
		String description = "Deposit";
		Long timestamp = System.currentTimeMillis();
		TransactionDTO transaction = new TransactionDTO(accountNumber, amount,
				timestamp, description);
		long result = transactionDao.insert(transaction);
		assertEquals(1, result);
	}

	public void testGetTransactionOccurred() {
		String accountNumber = "0123456789";
		float amount = 100;
		String description = "Deposit";
		Long timestamp = System.currentTimeMillis();
		TransactionDTO transaction = new TransactionDTO(accountNumber, amount,
				timestamp, description);
		transactionDao.insert(transaction);
		List<TransactionDTO> listTransaction = transactionDao
				.getTransactionDaoOccurred(accountNumber);
		assertEquals(1, listTransaction.size());
	}

	public void testGetTransactionOccurredInTime() {
		String accountNumber = "0123456789";
		float amount = 100;
		String description = "Deposit";
		long startTime = 1L;
		long stopTime = 3L;
		Long timestamp = 2L;
		TransactionDTO transaction = new TransactionDTO(accountNumber, amount,
				timestamp, description);
		transactionDao.insert(transaction);
		List<TransactionDTO> listTransaction = transactionDao
				.getTransactionDaoOccurredInTime(accountNumber, startTime,
						stopTime);
		assertEquals(1, listTransaction.size());
	}

	public void testGetNumberOfTransactionDao() {
		String accountNumber = "0123456789";
		float amount = 100;
		String description = "Deposit";
		Long timestamp = System.currentTimeMillis();
		TransactionDTO transaction = new TransactionDTO(accountNumber, amount,
				timestamp, description);
		transactionDao.insert(transaction);

		TransactionDTO transaction2 = new TransactionDTO(accountNumber, amount,
				timestamp, description);
		transactionDao.insert(transaction2);

		List<TransactionDTO> listTransaction = transactionDao
				.getNumberOfTransactionDao(accountNumber, 1);
		assertEquals(1, listTransaction.size());
	}

}
