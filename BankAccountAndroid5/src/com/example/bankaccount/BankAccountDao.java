package com.example.bankaccount;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

public class BankAccountDao {
	private SQLiteDatabase db;

	public BankAccountDao(Context context, String dbName) {
		super();
		DatabaseHelper databaseHelper = new DatabaseHelper(context, dbName);
		this.db = databaseHelper.getWritableDatabase();
	}

	// public void save(BankAccountDTO bankAccountDTO) {
	// // TODO Auto-generated method stub
	//
	// }

	public BankAccountDTO getAccountDao(String accountNumber) {
		// TODO Auto-generated method stub
		BankAccountDTO accountDTO = new BankAccountDTO();
		String whereClasue = DatabaseHelper.ACCOUNT_NUMBER + "= ?";
		Cursor cursor = db.query(DatabaseHelper.TABLE_ACCOUNT, new String[] {
				DatabaseHelper.ID, DatabaseHelper.ACCOUNT_NUMBER,
				DatabaseHelper.BALANCE, DatabaseHelper.OPEN_TIMESTAMP },
				whereClasue, new String[] { accountNumber }, null, null, null);

		// there will be only record
		if (cursor.moveToFirst()) {
			do {
				accountDTO.setAccountNumber(cursor.getString(1));
				accountDTO.setBalance(cursor.getLong(2));
				accountDTO.setTimeStamp(cursor.getLong(3));
			} while (cursor.moveToNext());

		}
		return accountDTO;
	}

	public long insert(BankAccountDTO accountDTO) {
		// TODO Auto-generated method stub

		ContentValues values = new ContentValues();
		long rowID = -1;
		if (!checkExistsAccount(accountDTO)) {
			values.put(DatabaseHelper.ACCOUNT_NUMBER,
					accountDTO.getAccountNumber());
			values.put(DatabaseHelper.BALANCE, accountDTO.getBalance());
			values.put(DatabaseHelper.OPEN_TIMESTAMP, accountDTO.getTimeStamp());
			rowID = db.insert(DatabaseHelper.TABLE_ACCOUNT, null, values);
		}
		return rowID;
	}

	public boolean checkExistsAccount(BankAccountDTO accountDTO) {
		Cursor cursor = db.rawQuery("SELECT * FROM "
				+ DatabaseHelper.TABLE_ACCOUNT + " WHERE "
				+ DatabaseHelper.ACCOUNT_NUMBER + "=?",
				new String[] { accountDTO.getAccountNumber() });
		boolean exists = (cursor.getCount() > 0);
		cursor.close();
		return exists;
	}

	public int getNumberOfRecord() {
		int numRows = (int) DatabaseUtils.queryNumEntries(db,
				DatabaseHelper.TABLE_ACCOUNT);
		return numRows;
	}

	public void save(BankAccountDTO accDTOFromDB) {
		// TODO Auto-generated method stub
		ContentValues contentValues = new ContentValues();
		contentValues.put(DatabaseHelper.ACCOUNT_NUMBER,
				accDTOFromDB.getAccountNumber());
		contentValues.put(DatabaseHelper.BALANCE, accDTOFromDB.getBalance());
		contentValues.put(DatabaseHelper.OPEN_TIMESTAMP,
				accDTOFromDB.getTimeStamp());
		db.update(
				DatabaseHelper.TABLE_ACCOUNT,
				contentValues,
				DatabaseHelper.ACCOUNT_NUMBER + "="
						+ accDTOFromDB.getAccountNumber(), null);
	}

}
