package net.burgess1.meal_ideas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.support.v4.app.NavUtils;

public class NewMealActivity extends Activity {

	  private Meal_Ideas_DataSource datasource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newmeal);
		// Show the Up button in the action bar.
		setupActionBar();
		datasource = new Meal_Ideas_DataSource(this);
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_meal, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
    	case R.id.action_saveMeal:
    		saveMeal();
    		return true;
    	case R.id.action_settings:
    		return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void saveMeal() {
    	EditText mealName = (EditText) findViewById(R.id.mealName);
    	String meal = mealName.getText().toString();
    	EditText mealCals = (EditText) findViewById(R.id.mealCal);
    	String cals = mealCals.getText().toString();
    	EditText mealSyns = (EditText) findViewById(R.id.mealSyns);
    	String syns = mealSyns.getText().toString();
    	
    	datasource.open();
		datasource.createMeal(meal, cals, syns);
    	datasource.close();	    

		NavUtils.navigateUpFromSameTask(this);    	
	}
	
}

