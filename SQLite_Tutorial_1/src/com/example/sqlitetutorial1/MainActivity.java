package com.example.sqlitetutorial1;

import java.util.List;
import java.util.Random;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {

	  private CommentsDataSource datasource;
	
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);

	    datasource = new CommentsDataSource(this);
	    datasource.open();

	    List<CommentObject> completeCommentList = datasource.getAllComments();

	    // Use the SimpleCursorAdapter to show the
	    // elements in a ListView
	    ArrayAdapter<CommentObject> adapter = new ArrayAdapter<CommentObject>(this, android.R.layout.simple_list_item_1, completeCommentList);
	    setListAdapter(adapter);
	  }
	
	  // Will be called via the onClick attribute
	  // of the buttons in main.xml
	  public void buttonClick(View view) {
	    int newInt;	    
	    
	    @SuppressWarnings("unchecked")
	    ArrayAdapter<CommentObject> adapter = (ArrayAdapter<CommentObject>) getListAdapter();
	    CommentObject comment = null;
	    switch (view.getId()) {
	    case R.id.add_button:
	      String[] comments = new String[] { "Cool", "Very nice", "Hate it" };
	      newInt = new Random().nextInt(3);
	      // Save the new comment to the database
	      comment = datasource.createComment(comments[newInt]);
	      adapter.add(comment);
	      break;
	    case R.id.del_button:
	      if (getListAdapter().getCount() > 0) {
	    	newInt = new Random().nextInt(getListAdapter().getCount());  
	        comment = (CommentObject) getListAdapter().getItem(newInt);
	        datasource.deleteComment(comment);
	        adapter.remove(comment);
	      }
	      break;
	    }
	    adapter.notifyDataSetChanged();
	  }	
	
	  
	  @Override
	  protected void onResume() {
	    datasource.open();
	    super.onResume();
	  }

	  @Override
	  protected void onPause() {
	    datasource.close();
	    super.onPause();
	  }

	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }
	    
	    public boolean onOptionsItemSelected(MenuItem item)
	    {
	    	switch (item.getItemId())
	    	{
		    	case R.id.action_settings:
		    		return true;
		    	default:
		    		return false;
	    	}
	    }
}
