package Component;

import java.util.List;

public class DayComponent{
	private boolean dayCheck=false;
	private String name;
	private String date;
	private String parchaseDate;
	private FoodComponent stapleFood,mainCourse,sideDishOne,sideDishSecond,soup;
	private List<IngredientComponent> acceptanceArray;
	public boolean isDayCheck() {
		return dayCheck;
	}
	public String getName() {
		return name;
	}
	public String getDate() {
		return date;
	}
	public String getParchaseDate() {
		return parchaseDate;
	}
	public FoodComponent getStapleFood() {
		return stapleFood;
	}
	public FoodComponent getMainCourse() {
		return mainCourse;
	}
	public FoodComponent getSideDishOne() {
		return sideDishOne;
	}
	public FoodComponent getSideDishSecond() {
		return sideDishSecond;
	}
	public FoodComponent getSoup() {
		return soup;
	}
	public List<IngredientComponent> getAcceptanceArray() {
		return acceptanceArray;
	}
	public void setDayCheck(boolean dayCheck) {
		this.dayCheck = dayCheck;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setParchaseDate(String parchaseDate) {
		this.parchaseDate = parchaseDate;
	}
	public void setStapleFood(FoodComponent stapleFood) {
		this.stapleFood = stapleFood;
	}
	public void setMainCourse(FoodComponent mainCourse) {
		this.mainCourse = mainCourse;
	}
	public void setSideDishOne(FoodComponent sideDishOne) {
		this.sideDishOne = sideDishOne;
	}
	public void setSideDishSecond(FoodComponent sideDishSecond) {
		this.sideDishSecond = sideDishSecond;
	}
	public void setSoup(FoodComponent soup) {
		this.soup = soup;
	}
	public void setAcceptanceArray(List<IngredientComponent> acceptanceArray) {
		this.acceptanceArray = acceptanceArray;
	}

	
}