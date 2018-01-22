package Component;

import java.util.List;

public class Day{
	private String name;
	private Food stapleFood,mainCourse,sideDishOne,sideDishSecond,soup;
	private List<String> acceptance;
	public String getName() {
		return name;
	}
	public Food getStapleFood() {
		return stapleFood;
	}
	public Food getMainCourse() {
		return mainCourse;
	}
	public Food getSideDishOne() {
		return sideDishOne;
	}
	public Food getSideDishSecond() {
		return sideDishSecond;
	}
	public Food getSoup() {
		return soup;
	}
	public List<String> getAcceptance() {
		return acceptance;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setStapleFood(Food stapleFood) {
		this.stapleFood = stapleFood;
	}
	public void setMainCourse(Food mainCourse) {
		this.mainCourse = mainCourse;
	}
	public void setSideDishOne(Food sideDishOne) {
		this.sideDishOne = sideDishOne;
	}
	public void setSideDishSecond(Food sideDishSecond) {
		this.sideDishSecond = sideDishSecond;
	}
	public void setSoup(Food soup) {
		this.soup = soup;
	}
	public void setAcceptance(List<String> acceptance) {
		this.acceptance = acceptance;
	}
	
	
}