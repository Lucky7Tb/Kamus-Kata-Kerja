package com.example.kamuinggris;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DBHelper extends SQLiteAssetHelper {
	public static final String databaseName = "kamus";
	public static final String tableName = "kamus_inggris";
	public static final String column1 = "ID";
	public static final String column2 = "kata_indo";   
	public static final String column3 = "kata_inggris";
	private static DBHelper dbInstance;
	private static SQLiteDatabase db;
	private String Kata;

	public void setKata(String Kata){
		this.Kata = Kata;
	}

	private DBHelper(Context context)
	{
		super(context, databaseName, null, 1);
	}

	public static DBHelper getInstance(Context context)
	{
		if (dbInstance == null)
		{
			dbInstance = new DBHelper(context);
			db = dbInstance.getWritableDatabase();
		}
		return dbInstance;
	}

	@Override
	public synchronized void close()
	{
		super.close();
		if (dbInstance != null)
		{
			dbInstance.close();
		}
	}
	
	public Cursor getKataInggris(){
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor res = db.rawQuery("SELECT "+ column3 +" FROM "+ tableName +" WHERE "+column2+" LIKE '"+this.Kata+"%'", null);
		return res;
	}
	
	public Cursor getKataIndo(){
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor res = db.rawQuery("SELECT "+ column2 +" FROM "+ tableName +" WHERE "+column3+" LIKE '"+this.Kata+"%'", null);
		return res;
	}

}