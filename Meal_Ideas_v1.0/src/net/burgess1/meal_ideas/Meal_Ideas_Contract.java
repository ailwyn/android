package net.burgess1.meal_ideas;

public class Meal_Ideas_Contract {

	public Meal_Ideas_Contract() {
		// To prevent someone from accidentally instantiating the contract
		// class,
		// give it an empty constructor.
	}

	public static final String DATABASE = "Meal_Ideas";
	public static final int VERSION = 1;

	/* Inner class that defines the table contents */
	public static abstract class Meal_Idea {

		public static final String TABLE_NAME = "Meals";

		public static final String COLUMN_ID = "_id";
		public static final String COLUMN_MEAL = "Meal";
		public static final String COLUMN_CALS = "Cals";
		public static final String COLUMN_SYNS = "Syns";
		
		
		public static final String[] ALL_FIELDS = { COLUMN_ID, COLUMN_MEAL, COLUMN_CALS, COLUMN_SYNS };
	}

}
