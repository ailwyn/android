package com.example.sqlitetutorial1;

public class CommentObject {

//	public Comment() {
//		// TODO Auto-generated constructor stub
//	}

	  private long id;
	  private String comment;

	  public long getId() {
	    return id;
	  }

	  public void setId(long id) {
	    this.id = id;
	  }

	  public String getComment() {
	    return comment;
	  }

	  public void setComment(String comment) {
	    this.comment = comment;
	  }

	  // Will be used by the ArrayAdapter in the ListView
	  @Override
	  public String toString() {
	    
		  return id + " - " + comment;
	  }
	
}
