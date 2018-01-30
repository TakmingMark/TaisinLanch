package Component;

import java.util.List;

public class DayComponent{
	private boolean dayCheck=false;
	private String name;
	private String date;
	private String parchaseDate;
	private FoodComponent stapleFood,mainCourse,sideDishOne,sideDishSecond,soup;
	private List<String> acceptance;
	public String getName() {
		return name;
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
	public List<String> getAcceptance() {
		return acceptance;
	}
	public void setName(String name) {
		this.name = name;
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
	public void setAcceptance(List<String> acceptance) {
		this.acceptance = acceptance;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getParchaseDate() {
		return parchaseDate;
	}
	public void setParchaseDate(String parchaseDate) {
		this.parchaseDate = parchaseDate;
	}
	public boolean isDayCheck() {
		return dayCheck;
	}
	public void setDayCheck(boolean dayCheck) {
		this.dayCheck = dayCheck;
	}
}