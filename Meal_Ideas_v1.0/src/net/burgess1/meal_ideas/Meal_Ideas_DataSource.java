package net.burgess1.meal_ideas;

import java.util.ArrayList;
import java.util.List;

import net.burgess1.meal_ideas.Meal_Ideas_Contract.Meal_Idea;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class Meal_Ideas_DataSource {

	private DatabaseHelper dbHelper;
	private SQLiteDatabase database;

	public Meal_Ideas_DataSource(Context context) {
		dbHelper = new DatabaseHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Meal_Object createMeal(String meal, String cals, String syns) {

		ContentValues values = new ContentValues();
		values.put(Meal_Idea.COLUMN_MEAL, meal);
		values.put(Meal_Idea.COLUMN_CALS, cals);
		values.put(Meal_Idea.COLUMN_SYNS, syns);
		
		long insertId = database.insert(Meal_Idea.TABLE_NAME, null, values);
		Cursor cursor = database.query(Meal_Idea.TABLE_NAME,
				Meal_Idea.ALL_FIELDS, Meal_Idea.COLUMN_ID + " = " + insertId,
				null, null, null, null);
		cursor.moveToFirst();
		Meal_Object newMeal = cursorToMeal(cursor);
		cursor.close();
		return newMeal;
	}

	private Meal_Object cursorToMeal(Cursor cursor) {
		Meal_Object return_meal = new Meal_Object();
		return_meal.setId(cursor.getLong(0));
		return_meal.setMeal(cursor.getString(1));
		return return_meal;
	}

	public void deleteMeal(Meal_Object delMeal) {
		long id = delMeal.getId();
		System.out.println("Meal deleted with id: " + id);
		database.delete(Meal_Idea.TABLE_NAME, Meal_Idea.COLUMN_ID + " = " + id,
				null);
	}

	public List<Meal_Object> getAllComments() {
		List<Meal_Object> meals_list = new ArrayList<Meal_Object>();
		Cursor cursor = database.query(Meal_Idea.TABLE_NAME,
				Meal_Idea.ALL_FIELDS, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Meal_Object curr_meal = cursorToMeal(cursor);
			meals_list.add(curr_meal);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return meals_list;
	}

}
