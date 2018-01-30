package Menu;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.JTextField;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import Component.DayComponent;
import Component.FoodComponent;
import Component.MenuDataComponent;
import Component.ZoomRowInput;
import Component.ZoomRowView;
import Excel.AcceptanceExcelModel;
import Excel.Excel;
import Excel.IngredientExcelModel;
import Excel.MenuExcelModel;

public class MenuModel {
	MenuDataComponent menuDataInput, menuDataOutput;
	Excel excel;
	private MenuModel() {

	}

	public static MenuModel getMenuModelObject() {
		return new MenuModel();
	}
	
	public void initMenuModel() {
		excel=excel.getExcelObject();
	}
	
	public void menuDataInputToMenuView(MenuView menuView) {
		parseJSONFromMenuFile();
		menuView.getSchoolNameTextField().setText(menuDataInput.getSchoolName());
		menuView.getYearComboBox().setSelectedItem(menuDataInput.getDate().split("\\/")[0]);
		menuView.getMonthComboBox().setSelectedItem(menuDataInput.getDate().split("\\/")[1]);
		menuView.getDayComboBox().setSelectedItem(menuDataInput.getDate().split("\\/")[2]);

		for (DayComponent dayElement : menuDataInput.getDay()) {
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

	private void jSONFormatToDayView(DayView dayView, DayComponent dayElement) {
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
			dayView.getAcceptanceView().insertDataToZoomRowView(acceptanceElement);
		}
	}

	private void jSONFormatToFoodView(JTextField foodName, FoodComponent food, ZoomRowView zoomRowView) {
		foodName.setText(food.getName());
		for (String ingredientElement : food.getIngredient())
			zoomRowView.insertDataToZoomRowView(ingredientElement);
	}

	public void menuViewToMenuDataOutput(MenuView menuView) {
		JSONObject menuJSONObject = menuViewToJSONFormat(menuView);
		menuDataOutput = new Gson().fromJson(menuJSONObject.toJSONString(), MenuDataComponent.class);
	}

	@SuppressWarnings("unchecked")
	public JSONObject menuViewToJSONFormat(MenuView menuView) {
		JSONObject menuJSONObject = new JSONObject();
		menuJSONObject.put("schoolName", menuView.getSchoolNameTextField().getText());
		menuJSONObject.put("date", menuView.getYearComboBox().getSelectedItem() + "/"
				+ menuView.getMonthComboBox().getSelectedItem() + "/" + menuView.getDayComboBox().getSelectedItem());
		JSONArray dayJSONArray = new JSONArray();
		menuJSONObject.put("day", dayJSONArray);
		if (menuView.getMonday().getDayCheckBox().isSelected() == true) {}
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

	@SuppressWarnings("unchecked")
	public JSONObject dayViewToJSONFormat(DayView day) {
		JSONObject dayJSONObject = new JSONObject();
		dayJSONObject.put("dayCheck",true);
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
			if (!element.getTextField().getText().equals(""))
				acceptanceJSONArray.add(element.getTextField().getText());
		}

		dayJSONObject.put("stapleFood", stapleFoodJSONObject);
		dayJSONObject.put("mainCourse", mainCourseJSONObject);
		dayJSONObject.put("sideDishOne", sideDishOneJSONObject);
		dayJSONObject.put("sideDishSecond", sideDishSecondJSONObject);
		dayJSONObject.put("soup", soupJSONObject);
		dayJSONObject.put("acceptance", acceptanceJSONArray);
		return dayJSONObject;
	}

	@SuppressWarnings("unchecked")
	public JSONObject foodViewToJSONFormat(String food, ArrayList<ZoomRowInput> zoomRowInputArrayList) {
		JSONObject foodJSONObject = new JSONObject();
		foodJSONObject.put("name", food);
		JSONArray foodJSONArray = new JSONArray();
		for (ZoomRowInput element : zoomRowInputArrayList) {
			if (!element.getTextField().getText().equals(""))
				foodJSONArray.add(element.getTextField().getText());
		}
		foodJSONObject.put("ingredient", foodJSONArray);

		return foodJSONObject;
	}
	
	public void exportDataToExcel() {
		excel.exportDataToExcel(menuDataOutput);
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
