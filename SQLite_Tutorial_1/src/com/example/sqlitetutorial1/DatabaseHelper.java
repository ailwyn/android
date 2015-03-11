package com.example.sqlitetutorial1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	public static final String TABLE_COMMENTS = "t_Comments";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_COMMENT = "f_comment";	
	
	  
	public DatabaseHelper(Context context)
	{
		super(context, TABLE_COMMENTS, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	
		  // Database creation sql statement
		  final String DATABASE_CREATE = "create table "
		      + TABLE_COMMENTS + "(" + COLUMN_ID
		      + " integer primary key autoincrement, " + COLUMN_COMMENT
		      + " text not null);";
		  
		  db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(DatabaseHelper.class.getName(),
	            "Upgrading database from version " + oldVersion + " to "
	                + newVersion + ", which will destroy all old data");
	    
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
	    onCreate(db);

	}

}
