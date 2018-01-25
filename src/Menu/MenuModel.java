package Menu;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import Component.Day;
import Component.Food;
import Component.MenuDataComponent;
import Component.ZoomRowInput;
import Component.ZoomRowView;

public class MenuModel {
	MenuDataComponent menuDataInput, menuDataOutput;

	private MenuModel() {

	}

	public static MenuModel getMenuModelObject() {
		return new MenuModel();
	}

	public void menuDataInputToMenuView(MenuView menuView) {
		parseJSONFromMenuFile();
		menuView.getSchoolNameTextField().setText(menuDataInput.getSchoolName());
		menuView.getYearComboBox().setSelectedItem(menuDataInput.getDate().split("\\/")[0]);
		menuView.getMonthComboBox().setSelectedItem(menuDataInput.getDate().split("\\/")[1]);
		menuView.getDayComboBox().setSelectedItem(menuDataInput.getDate().split("\\/")[2]);

		for (Day dayElement : menuDataInput.getDay()) {
			switch (dayElement.getName()) {
			case "星期一":
				jSONFormatToDayView(menuView.getMonday(), dayElement);
				break;
			case "星期二":
				jSONFormatToDayView(menuView.getTuesday(), dayElement);
				break;
			case "星期三":
				jSONFormatToDayView(menuView.getWednesday(), dayElement);
				break;
			case "星期四":
				jSONFormatToDayView(menuView.getThursday(), dayElement);
				break;
			case "星期五":
				jSONFormatToDayView(menuView.getFriday(), dayElement);
				break;
			default:
				break;
			}
		}
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

	private void jSONFormatToDayView(DayView dayView, Day dayElement) {
		dayView.getDayCheckBox().setSelected(true);
		
		jSONFormatToFoodView(dayView.getStapleFoodTextField(), dayElement.getStapleFood(),
				dayView.getStapleFoodIngredientView());

		jSONFormatToFoodView(dayView.getMainCourseTextField(), dayElement.getMainCourse(),
				dayView.getMainCourseIngredientView());
		
		jSONFormatToFoodView(dayView.getSideDishOneTextField(), dayElement.getSideDishOne(),
				dayView.getSideDishOneIngredientView());
		
		jSONFormatToFoodView(dayView.getSideDishSecondTextField(), dayElement.getSideDishSecond(),
				dayView.getSideDishSecondIngredientView());
		
		jSONFormatToFoodView(dayView.getSoupTextField(), dayElement.getSoup(),
				dayView.getSoupIngredientView());

		for (String acceptanceElement : dayElement.getAcceptance()) {
			dayView.getAcceptanceView().testInsertZoomRowInput(acceptanceElement);
		}
	}

	private void jSONFormatToFoodView(JTextField foodName, Food food, ZoomRowView zoomRowView) {
		foodName.setText(food.getName());
		for (String ingredientElement : food.getIngredient())
			zoomRowView.testInsertZoomRowInput(ingredientElement);
	}

	public void menuViewToMenuDataOutput(MenuView menuView) {
		JSONObject menuJSONObject = menuViewToJSONFormat(menuView);
		menuDataOutput = new Gson().fromJson(menuJSONObject.toJSONString(), MenuDataComponent.class);
	}

	public JSONObject menuViewToJSONFormat(MenuView menuView) {
		JSONObject menuJSONObject = new JSONObject();
		menuJSONObject.put("schoolName", menuView.getSchoolNameTextField().getText());
		menuJSONObject.put("date", menuView.getYearComboBox().getSelectedItem() + "/"
				+ menuView.getMonthComboBox().getSelectedItem() + "/" + menuView.getDayComboBox().getSelectedItem());
		JSONArray dayJSONArray = new JSONArray();
		menuJSONObject.put("day", dayJSONArray);
		if (menuView.getMonday().getDayCheckBox().isSelected() == true)
			dayJSONArray.add(dayViewToJSONFormat(menuView.getMonday()));

		if (menuView.getTuesday().getDayCheckBox().isSelected() == true)
			dayJSONArray.add(dayViewToJSONFormat(menuView.getTuesday()));

		if (menuView.getWednesday().getDayCheckBox().isSelected() == true)
			dayJSONArray.add(dayViewToJSONFormat(menuView.getWednesday()));

		if (menuView.getThursday().getDayCheckBox().isSelected() == true)
			dayJSONArray.add(dayViewToJSONFormat(menuView.getThursday()));

		if (menuView.getFriday().getDayCheckBox().isSelected() == true)
			dayJSONArray.add(dayViewToJSONFormat(menuView.getFriday()));

		return menuJSONObject;
	}

	public JSONObject dayViewToJSONFormat(DayView day) {
		JSONObject dayJSONObject = new JSONObject();
		dayJSONObject.put("name", day.getDayCheckBox().getText());

		JSONObject stapleFoodJSONObject = foodViewToJSONFormat(day.getStapleFoodTextField().getText(),
				day.getStapleFoodIngredientView().getZoomRowInputArrayList());

		JSONObject mainCourseJSONObject = foodViewToJSONFormat(day.getMainCourseTextField().getText(),
				day.getMainCourseIngredientView().getZoomRowInputArrayList());

		JSONObject sideDishOneJSONObject = foodViewToJSONFormat(day.getSideDishOneTextField().getText(),
				day.getSideDishOneIngredientView().getZoomRowInputArrayList());

		JSONObject sideDishSecondJSONObject = foodViewToJSONFormat(day.getSideDishSecondTextField().getText(),
				day.getSideDishSecondIngredientView().getZoomRowInputArrayList());

		JSONObject soupJSONObject = foodViewToJSONFormat(day.getSoupTextField().getText(),
				day.getSoupIngredientView().getZoomRowInputArrayList());

		JSONArray acceptanceJSONArray = new JSONArray();
		for (ZoomRowInput element : day.getAcceptanceView().getZoomRowInputArrayList()) {
			if (!element.getZoomRowTextField().getText().equals(""))
				acceptanceJSONArray.add(element.getZoomRowTextField().getText());
		}

		dayJSONObject.put("stapleFood", stapleFoodJSONObject);
		dayJSONObject.put("mainCourse", mainCourseJSONObject);
		dayJSONObject.put("sideDishOne", sideDishOneJSONObject);
		dayJSONObject.put("sideDishSecond", sideDishSecondJSONObject);
		dayJSONObject.put("soup", soupJSONObject);
		dayJSONObject.put("acceptance", acceptanceJSONArray);
		return dayJSONObject;
	}

	public JSONObject foodViewToJSONFormat(String food, ArrayList<ZoomRowInput> zoomRowInputArrayList) {
		JSONObject foodJSONObject = new JSONObject();
		foodJSONObject.put("name", food);
		JSONArray foodJSONArray = new JSONArray();
		for (ZoomRowInput element : zoomRowInputArrayList) {
			if (!element.getZoomRowTextField().getText().equals(""))
				foodJSONArray.add(element.getZoomRowTextField().getText());
		}
		foodJSONObject.put("ingredient", foodJSONArray);

		return foodJSONObject;
	}

	public MenuDataComponent getMenuDataInput() {
		return menuDataInput;
	}

	public MenuDataComponent getMenuDataOutput() {
		return menuDataOutput;
	}

	public void setMenuDataInput(MenuDataComponent menuDataInput) {
		this.menuDataInput = menuDataInput;
	}

	public void setMenuDataOutput(MenuDataComponent menuDataOutput) {
		this.menuDataOutput = menuDataOutput;
	}
	
	
}
