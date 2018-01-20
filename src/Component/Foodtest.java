package Component;

import java.util.ArrayList;

public class Foodtest {
	private String date=null;
	private String week=null;
	private String stapleFood=null;
	private ArrayList<String> nonStapleFoodList=null;
	private String soup=null;
	private ArrayList<String> ingredientList=null;
	private ArrayList<String> seasoningList=null;
	
	public Foodtest() {
		nonStapleFoodList=new ArrayList<>();
		ingredientList=new ArrayList<>();
		seasoningList=new ArrayList<>();
	}
	
	public void print() {
		System.out.println("date:"+date);
		System.out.println("week:"+week);
		System.out.println("stapleFood:"+stapleFood);
		System.out.println("soup:"+soup);
		System.out.println("nonStapleFoodList");
		for(String element:nonStapleFoodList)
			System.out.println(element);
		System.out.println("ingredientList");
		for(String element:ingredientList)
			System.out.println(element);
		System.out.println("seasoningList");
		for(String element:seasoningList)
			System.out.println(element);
	}
	
	public String getDate() {
		return date;
	}

	public String getWeek() {
		return week;
	}

	public String getStapleFood() {
		return stapleFood;
	}

	public ArrayList<String> getNonStapleFoodList() {
		return nonStapleFoodList;
	}

	public String getSoup() {
		return soup;
	}

	public ArrayList<String> getIngredientList() {
		return ingredientList;
	}

	public ArrayList<String> getSeasoningList() {
		return seasoningList;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public void setStapleFood(String stapleFood) {
		this.stapleFood = stapleFood;
	}

	public void addNonStapleFoodList(String nonStapleFood) {
		this.nonStapleFoodList.add(nonStapleFood);
	}

	public void setSoup(String soup) {
		this.soup = soup;
	}

	public void addIngredientList(String ingredient) {
		this.ingredientList.add(ingredient);
	}

	public void addSeasoningList(String seasoning) {
		this.seasoningList.add(seasoning);
	}
	
}
