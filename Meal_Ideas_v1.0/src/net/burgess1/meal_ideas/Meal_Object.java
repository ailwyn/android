package net.burgess1.meal_ideas;

public class Meal_Object {

	private long id;
	private String meal;
	private String cals;
	private String syns;	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMeal() {
		return meal;
	}

	public String getCals() {
		return cals;
	}
	
	public String getSyns() {
		return syns;
	}	
	
	public void setMeal(String meal) {
		this.meal = meal;
	}

	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
		return meal + " " + id;
	}
}