package com.example.sqlitetutorial1;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CommentsDataSource {

//	public CommentsDataSource() {
//		// TODO Auto-generated constructor stub
//	}

	  // Database fields
	  private SQLiteDatabase database;
	  private DatabaseHelper dbHelper;
	  private String[] allColumns = { DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_COMMENT };

	  public CommentsDataSource(Context context) {
	    dbHelper = new DatabaseHelper(context);
	  }

	  public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }

	  public CommentObject createComment(String comment) {
	    ContentValues values = new ContentValues();
	    values.put(DatabaseHelper.COLUMN_COMMENT, comment);
	    long insertId = database.insert(DatabaseHelper.TABLE_COMMENTS, null,
	        values);
	    Cursor cursor = database.query(DatabaseHelper.TABLE_COMMENTS,
	        allColumns, DatabaseHelper.COLUMN_ID + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();
	    CommentObject newComment = cursorToComment(cursor);
	    cursor.close();
	    return newComment;
	  }

	  public void deleteComment(CommentObject comment) {
	    long id = comment.getId();
	    System.out.println("Comment deleted with id: " + id);
	    database.delete(DatabaseHelper.TABLE_COMMENTS, DatabaseHelper.COLUMN_ID
	        + " = " + id, null);
	  }

	  public List<CommentObject> getAllComments() {
	    List<CommentObject> comments = new ArrayList<CommentObject>();

	    Cursor cursor = database.query(DatabaseHelper.TABLE_COMMENTS,
	        allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      CommentObject comment = cursorToComment(cursor);
	      comments.add(comment);
	      cursor.moveToNext();
	    }
	    // Make sure to close the cursor
	    cursor.close();
	    return comments;
	  }

	  private CommentObject cursorToComment(Cursor cursor) {
	    CommentObject comment = new CommentObject();
	    comment.setId(cursor.getLong(0));
	    comment.setComment(cursor.getString(1));
	    return comment;
	  }	
	
}
