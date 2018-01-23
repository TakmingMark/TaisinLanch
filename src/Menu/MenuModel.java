package Menu;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import Component.Day;
import Component.MenuDataComponent;

public class MenuModel {
	MenuDataComponent menuDataInput,menuDataOutput;

	private MenuModel() {
		parseJSONFromMenuFile();
	}

	public static MenuModel getMenuModelObject() {
		return new MenuModel();
	}

	private void parseJSONFromMenuFile() {
		JsonReader bufferedReader = null;
		try {
			bufferedReader = new JsonReader(
					new BufferedReader(new InputStreamReader(new FileInputStream("json/menu.json"), "UTF-8")));
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
		menuDataInput = new Gson().fromJson(bufferedReader, MenuDataComponent.class);
	}
	
	public void menuDataInputToMenuView(MenuView menuView) {
		menuView.getSchoolNameTextField().setText(menuDataInput.getSchoolName());
		menuView.getYearComboBox().setSelectedItem(menuDataInput.getDate().split("\\/")[0]);
		menuView.getMonthComboBox().setSelectedItem(menuDataInput.getDate().split("\\/")[1]);
		menuView.getDayComboBox().setSelectedItem(menuDataInput.getDate().split("\\/")[2]);
		
		for(Day dayElement:menuDataInput.getDay()) {
			switch (dayElement.getName()) {
			case "星期一":
				menuView.getMondy().getDayCheckBox().setSelected(true);
				menuView.getMondy().getStapleFoodTextField().setText(dayElement.getStapleFood().getName());
				
				menuView.getMondy().getMainCourseTextField().setText(dayElement.getMainCourse().getName());
				for(String ingredientElement:dayElement.getMainCourse().getIngredient())
					menuView.getMondy().getMainCourseIngredientView().testInsertZoomRowInput(ingredientElement);
				
				menuView.getMondy().getSideDishOneTextField().setText(dayElement.getSideDishOne().getName());
				for(String ingredientElement:dayElement.getSideDishOne().getIngredient())
					menuView.getMondy().getSideDishOneIngredientView().testInsertZoomRowInput(ingredientElement);
		
				menuView.getMondy().getSideDishSecondTextField().setText(dayElement.getSideDishSecond().getName());
				for(String ingredientElement:dayElement.getSideDishSecond().getIngredient())
					menuView.getMondy().getSideDishSecondIngredientView().testInsertZoomRowInput(ingredientElement);
		
				menuView.getMondy().getSoupTextField().setText(dayElement.getSoup().getName());
				for(String ingredientElement:dayElement.getSoup().getIngredient())
					menuView.getMondy().getSoupIngredientView().testInsertZoomRowInput(ingredientElement);
		
				for(String acceptanceElement:dayElement.getAcceptance()) {
					menuView.getMondy().getAcceptanceView().testInsertZoomRowInput(acceptanceElement);
				}
				break;
			case "星期二":
				menuView.getTuesday().getDayCheckBox().setSelected(true);
				menuView.getTuesday().getStapleFoodTextField().setText(dayElement.getStapleFood().getName());
				
				menuView.getTuesday().getMainCourseTextField().setText(dayElement.getMainCourse().getName());
				for(String ingredientElement:dayElement.getMainCourse().getIngredient())
					menuView.getTuesday().getMainCourseIngredientView().testInsertZoomRowInput(ingredientElement);
				
				menuView.getTuesday().getSideDishOneTextField().setText(dayElement.getSideDishOne().getName());
				for(String ingredientElement:dayElement.getSideDishOne().getIngredient())
					menuView.getTuesday().getSideDishOneIngredientView().testInsertZoomRowInput(ingredientElement);
		
				menuView.getTuesday().getSideDishSecondTextField().setText(dayElement.getSideDishSecond().getName());
				for(String ingredientElement:dayElement.getSideDishSecond().getIngredient())
					menuView.getTuesday().getSideDishSecondIngredientView().testInsertZoomRowInput(ingredientElement);
		
				menuView.getTuesday().getSoupTextField().setText(dayElement.getSoup().getName());
				for(String ingredientElement:dayElement.getSoup().getIngredient())
					menuView.getTuesday().getSoupIngredientView().testInsertZoomRowInput(ingredientElement);
		
				for(String acceptanceElement:dayElement.getAcceptance()) {
					menuView.getTuesday().getAcceptanceView().testInsertZoomRowInput(acceptanceElement);
				}
				break;
			case "星期三":
				menuView.getWednesday().getDayCheckBox().setSelected(true);
				menuView.getWednesday().getStapleFoodTextField().setText(dayElement.getStapleFood().getName());
				
				menuView.getWednesday().getMainCourseTextField().setText(dayElement.getMainCourse().getName());
				for(String ingredientElement:dayElement.getMainCourse().getIngredient())
					menuView.getWednesday().getMainCourseIngredientView().testInsertZoomRowInput(ingredientElement);
				
				menuView.getWednesday().getSideDishOneTextField().setText(dayElement.getSideDishOne().getName());
				for(String ingredientElement:dayElement.getSideDishOne().getIngredient())
					menuView.getWednesday().getSideDishOneIngredientView().testInsertZoomRowInput(ingredientElement);
		
				menuView.getWednesday().getSideDishSecondTextField().setText(dayElement.getSideDishSecond().getName());
				for(String ingredientElement:dayElement.getSideDishSecond().getIngredient())
					menuView.getWednesday().getSideDishSecondIngredientView().testInsertZoomRowInput(ingredientElement);
		
				menuView.getWednesday().getSoupTextField().setText(dayElement.getSoup().getName());
				for(String ingredientElement:dayElement.getSoup().getIngredient())
					menuView.getWednesday().getSoupIngredientView().testInsertZoomRowInput(ingredientElement);
		
				for(String acceptanceElement:dayElement.getAcceptance()) {
					menuView.getWednesday().getAcceptanceView().testInsertZoomRowInput(acceptanceElement);
				}
				break;
			case "星期四":
				menuView.getThursday().getDayCheckBox().setSelected(true);
				menuView.getThursday().getStapleFoodTextField().setText(dayElement.getStapleFood().getName());
				
				menuView.getThursday().getMainCourseTextField().setText(dayElement.getMainCourse().getName());
				for(String ingredientElement:dayElement.getMainCourse().getIngredient())
					menuView.getThursday().getMainCourseIngredientView().testInsertZoomRowInput(ingredientElement);
				
				menuView.getThursday().getSideDishOneTextField().setText(dayElement.getSideDishOne().getName());
				for(String ingredientElement:dayElement.getSideDishOne().getIngredient())
					menuView.getThursday().getSideDishOneIngredientView().testInsertZoomRowInput(ingredientElement);
		
				menuView.getThursday().getSideDishSecondTextField().setText(dayElement.getSideDishSecond().getName());
				for(String ingredientElement:dayElement.getSideDishSecond().getIngredient())
					menuView.getThursday().getSideDishSecondIngredientView().testInsertZoomRowInput(ingredientElement);
		
				menuView.getThursday().getSoupTextField().setText(dayElement.getSoup().getName());
				for(String ingredientElement:dayElement.getSoup().getIngredient())
					menuView.getThursday().getSoupIngredientView().testInsertZoomRowInput(ingredientElement);
		
				for(String acceptanceElement:dayElement.getAcceptance()) {
					menuView.getThursday().getAcceptanceView().testInsertZoomRowInput(acceptanceElement);
				}
				break;
			case "星期五":
				menuView.getFriday().getDayCheckBox().setSelected(true);
				menuView.getFriday().getStapleFoodTextField().setText(dayElement.getStapleFood().getName());
				
				menuView.getFriday().getMainCourseTextField().setText(dayElement.getMainCourse().getName());
				for(String ingredientElement:dayElement.getMainCourse().getIngredient())
					menuView.getFriday().getMainCourseIngredientView().testInsertZoomRowInput(ingredientElement);
				
				menuView.getFriday().getSideDishOneTextField().setText(dayElement.getSideDishOne().getName());
				for(String ingredientElement:dayElement.getSideDishOne().getIngredient())
					menuView.getFriday().getSideDishOneIngredientView().testInsertZoomRowInput(ingredientElement);
		
				menuView.getFriday().getSideDishSecondTextField().setText(dayElement.getSideDishSecond().getName());
				for(String ingredientElement:dayElement.getSideDishSecond().getIngredient())
					menuView.getFriday().getSideDishSecondIngredientView().testInsertZoomRowInput(ingredientElement);
		
				menuView.getFriday().getSoupTextField().setText(dayElement.getSoup().getName());
				for(String ingredientElement:dayElement.getSoup().getIngredient())
					menuView.getFriday().getSoupIngredientView().testInsertZoomRowInput(ingredientElement);
		
				for(String acceptanceElement:dayElement.getAcceptance()) {
					menuView.getFriday().getAcceptanceView().testInsertZoomRowInput(acceptanceElement);
				}
				break;
			default:
				break;
			}
		}
	
	}
}
