package net.burgess1.meal_ideas;

import net.burgess1.meal_ideas.Meal_Ideas_Contract.Meal_Idea;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String TEXT_TYPE = " TEXT";
	
	private static final String SQL_CREATE_ENTRIES =
		    "CREATE TABLE " + Meal_Idea.TABLE_NAME + " (" + 
		    Meal_Idea.COLUMN_ID + " INTEGER PRIMARY KEY," + 
		    Meal_Idea.COLUMN_MEAL + TEXT_TYPE + "," + 
		    Meal_Idea.COLUMN_CALS + TEXT_TYPE + "," +
		    Meal_Idea.COLUMN_SYNS + TEXT_TYPE +
		    " )";
		    
	private static final String SQL_DELETE_ENTRIES = 
			"DROP TABLE IF EXISTS " + Meal_Idea.TABLE_NAME;
	
	public DatabaseHelper(Context context)
	{
		super(context, Meal_Ideas_Contract.DATABASE, null, Meal_Ideas_Contract.VERSION);
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		 db.execSQL(SQL_CREATE_ENTRIES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	    Log.w(DatabaseHelper.class.getName(),
	            "Upgrading database from version " + oldVersion + " to "
	                + newVersion + ", which will destroy all old data");
	    
		db.execSQL(SQL_DELETE_ENTRIES);
	    onCreate(db);
	}

}




