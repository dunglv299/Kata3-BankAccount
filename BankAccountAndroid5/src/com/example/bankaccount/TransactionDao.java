package com.example.bankaccount;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TransactionDao {
	private SQLiteDatabase db;

	public TransactionDao(Context context, String dbName) {
		// TODO Auto-generated constructor stub
		super();
		DatabaseHelper databaseHelper = new DatabaseHelper(context, dbName);
		this.db = databaseHelper.getWritableDatabase();
	}

	public long insert(TransactionDTO transactionDTO) {
		// TODO Auto-generated method stub
		ContentValues values = new ContentValues();
		long rowID = -1;
		values.put(DatabaseHelper.ACCOUNT_NUMBER,
				transactionDTO.getAccountNumber());
		values.put(DatabaseHelper.AMOUNT, transactionDTO.getAmount());
		values.put(DatabaseHelper.OPEN_TIMESTAMP, transactionDTO.getTimeStamp());
		values.put(DatabaseHelper.TRANSACTION_DESCRIPTION,
				transactionDTO.getDescription());
		rowID = db.insert(DatabaseHelper.TABLE_TRANSACTION, null, values);
		return rowID;
	}

	public List<TransactionDTO> getTransactionDaoOccurred(String accountNumber) {
		// TODO Auto-generated method stub
		List<TransactionDTO> listTransaction = new ArrayList<TransactionDTO>();
		TransactionDTO transactionDTO = new TransactionDTO();
		String whereClasue = DatabaseHelper.ACCOUNT_NUMBER + "= ?";
		Cursor cursor = db.query(DatabaseHelper.TABLE_TRANSACTION,
				new String[] { DatabaseHelper.ID,
						DatabaseHelper.ACCOUNT_NUMBER, DatabaseHelper.AMOUNT,
						DatabaseHelper.OPEN_TIMESTAMP,
						DatabaseHelper.TRANSACTION_DESCRIPTION }, whereClasue,
				new String[] { accountNumber }, null, null, null);

		// there will be only record
		if (cursor.moveToFirst()) {
			do {
				transactionDTO.setAccountNumber(cursor.getString(1));
				transactionDTO.setAmount(cursor.getLong(2));
				transactionDTO.setTimeStamp(cursor.getLong(3));
				transactionDTO.setDescription(cursor.getString(4));
				listTransaction.add(transactionDTO);
			} while (cursor.moveToNext());

		}
		return listTransaction;
	}

	public List<TransactionDTO> getTransactionDaoOccurredInTime(
			String accountNumber, long startTime, long stopTime) {
		// TODO Auto-generated method stub
		List<TransactionDTO> listTransaction = new ArrayList<TransactionDTO>();
		TransactionDTO transactionDTO = new TransactionDTO();
		String whereClasue = DatabaseHelper.ACCOUNT_NUMBER + "= ? AND "
				+ DatabaseHelper.OPEN_TIMESTAMP + " BETWEEN " + "?" + " AND "
				+ "?";
		Cursor cursor = db.query(DatabaseHelper.TABLE_TRANSACTION,
				new String[] { DatabaseHelper.ID,
						DatabaseHelper.ACCOUNT_NUMBER, DatabaseHelper.AMOUNT,
						DatabaseHelper.OPEN_TIMESTAMP,
						DatabaseHelper.TRANSACTION_DESCRIPTION }, whereClasue,
				new String[] { accountNumber, startTime + "", stopTime + "" },
				null, null, null);

		// there will be only record
		if (cursor.moveToFirst()) {
			do {
				transactionDTO.setAccountNumber(cursor.getString(1));
				transactionDTO.setAmount(cursor.getLong(2));
				transactionDTO.setTimeStamp(cursor.getLong(3));
				transactionDTO.setDescription(cursor.getString(4));
				listTransaction.add(transactionDTO);
			} while (cursor.moveToNext());

		}
		return listTransaction;
	}

	public List<TransactionDTO> getNumberOfTransactionDao(String accountNumber,
			int numberOfTransaction) {
		// TODO Auto-generated method stub
		List<TransactionDTO> listTransaction = new ArrayList<TransactionDTO>();
		TransactionDTO transactionDTO = new TransactionDTO();
		String whereClasue = DatabaseHelper.ACCOUNT_NUMBER + "= ?";
		Cursor cursor = db.query(DatabaseHelper.TABLE_TRANSACTION,
				new String[] { DatabaseHelper.ID,
						DatabaseHelper.ACCOUNT_NUMBER, DatabaseHelper.AMOUNT,
						DatabaseHelper.OPEN_TIMESTAMP,
						DatabaseHelper.TRANSACTION_DESCRIPTION }, whereClasue,
				new String[] { accountNumber }, null, null,
				DatabaseHelper.OPEN_TIMESTAMP + " DESC", numberOfTransaction
						+ "");

		// there will be only record
		if (cursor.moveToFirst()) {
			do {
				transactionDTO.setAccountNumber(cursor.getString(1));
				transactionDTO.setAmount(cursor.getLong(2));
				transactionDTO.setTimeStamp(cursor.getLong(3));
				transactionDTO.setDescription(cursor.getString(4));
				listTransaction.add(transactionDTO);
			} while (cursor.moveToNext());

		}
		return listTransaction;
	}
}
