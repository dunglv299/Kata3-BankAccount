package com.example.bankaccounttest;

import android.test.AndroidTestCase;

import com.example.bankaccount.BankAccountDTO;
import com.example.bankaccount.BankAccountDao;

public class BankAccountDaoTest extends AndroidTestCase {
	private BankAccountDao bankAccountDao;

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		bankAccountDao = new BankAccountDao(getContext(), null);
	}

	public void testInsertData() {
		BankAccountDTO accountDTO = new BankAccountDTO("123456789", 0);
		long rowID = bankAccountDao.insert(accountDTO);
		assertEquals(1, bankAccountDao.getNumberOfRecord());
		assertEquals(1, rowID);
	}

	public void testGetAccountByNumber() {
		String accountNumber = "123456789";
		BankAccountDTO accountDTO = new BankAccountDTO(accountNumber, 100);
		// insert account to DB
		bankAccountDao.insert(accountDTO);
		BankAccountDTO accDTOFromDB = bankAccountDao
				.getAccountDao(accountNumber);
		assertTrue(accDTOFromDB != null);
		assertEquals(100, accDTOFromDB.getBalance(), 0.01);
	}

	public void testUpdateAccount() {
		String accountNumber = "123456789";
		BankAccountDTO accountDTO = new BankAccountDTO(accountNumber, 0);
		// insert account to DB
		bankAccountDao.insert(accountDTO);

		// change balance of account and save to DB
		accountDTO.setBalance(100);
		bankAccountDao.save(accountDTO);

		// get account againt to compare
		BankAccountDTO accDTOFromDB = bankAccountDao
				.getAccountDao(accountNumber);
		assertEquals(100, accDTOFromDB.getBalance(), 0.01);
	}

	public void testInsertDuplicateAccount() {
		// insert account1 to DB
		String accountNumber = "123456789";
		BankAccountDTO accountDTO = new BankAccountDTO(accountNumber, 0);
		bankAccountDao.insert(accountDTO);

		// insert account2 to DB
		String accountNumber2 = "123456789";
		BankAccountDTO accountDTO2 = new BankAccountDTO(accountNumber2, 0);
		long rowID = bankAccountDao.insert(accountDTO2);
		assertEquals(-1, rowID);
	}
}
