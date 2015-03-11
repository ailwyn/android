package net.burgess1.meal_ideas;

import java.util.List;

import android.os.Bundle;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import android.widget.Toast;

public class MealsActivity extends ListActivity {

	Meal_Ideas_DataSource datasource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_meals);
		
	    datasource = new Meal_Ideas_DataSource(this);
	    datasource.open();
	    
	    List<Meal_Object> completeCommentList = datasource.getAllComments();

	    // Use the SimpleCursorAdapter to show the
	    // elements in a ListView
	    final ArrayAdapter<Meal_Object> adapter = new ArrayAdapter<Meal_Object>(this, android.R.layout.simple_list_item_1, completeCommentList);
	    setListAdapter(adapter);
		
	    getListView().setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
		        Toast.makeText(getApplicationContext(), "OnClick postion: " +    position, Toast.LENGTH_SHORT).show();
			}
		});    
	    
	    getListView().setOnItemLongClickListener(new OnItemLongClickListener(){
	    	@Override
	    	public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
//		        Toast.makeText(getApplicationContext(), "OnLongClickpostion: " +    position, Toast.LENGTH_SHORT).show();
		        
		        new AlertDialog.Builder(getListView().getContext())
		        	.setTitle("Delete entry")
		        	.setMessage("Are you sure you want to delete this entry?")
		        		.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
		        			public void onClick(DialogInterface dialog, int which) { 
		        				// continue with delete
		        		        Meal_Object selectedMeal = (Meal_Object) getListAdapter().getItem(position);
		        		        datasource.deleteMeal(selectedMeal);
		        		        adapter.remove(selectedMeal);
		        			}
		        		})
				        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				            public void onClick(DialogInterface dialog, int which) { 
				                // do nothing
				            }
				         })
				    .show();
		        
		        return true;
	    	}


	    });
	    
	    
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.meals, menu);
		return true;
	}
	
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	switch (item.getItemId())
    	{
	    	case R.id.action_addMeal:
	    		addMeal();
	    		return true;
	    	case R.id.action_settings:
	    		return true;
	    	default:
	    		return false;
    	}
    	
    }

    private void addMeal(){
       	Intent intent = new Intent(this, NewMealActivity.class);
        startActivity(intent);
    }
    
    
}
