package com.example.tublar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Sqlite extends SQLiteOpenHelper {
	
	
	public static final String DB_NAME="Tabular";
	
	//create table woohoo with a column for id n column ln to store text this is standard sql syntax
	String sql="CREATE TABLE IF NOT EXISTS route(_id INTEGER PRIMARY KEY AUTOINCREMENT,Route_no TEXT,Route_location TEXT,Route_fare TEXT,Route_director TEXT,Hotline TEXT)";
	
	//String sql="sqlite>.read<vehicledoc.sql>";
	
	
	public Sqlite(Context c
			) {
		super(c, DB_NAME, null, 4);//Context, name ,cursor factory, version
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(sql);
		
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS route ");
		onCreate(db);
		
	}
	
	}	
