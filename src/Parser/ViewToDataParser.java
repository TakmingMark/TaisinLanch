package Parser;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

import Component.MenuDataComponent;
import Menu.MenuView;
import View.DayView;
import View.RowView;

public class ViewToDataParser {
	public static ViewToDataParser getViewToDataParserObject() {
		return new ViewToDataParser();
	}
	
	public MenuDataComponent menuViewToMenuDataOutput(MenuView menuView) {
		JSONObject menuJSONObject = menuViewToJSONFormat(menuView);
		return new Gson().fromJson(menuJSONObject.toJSONString(), MenuDataComponent.class);
	}

	@SuppressWarnings("unchecked")
	private JSONObject menuViewToJSONFormat(MenuView menuView) {
		JSONObject menuJSONObject = new JSONObject();
		menuJSONObject.put("schoolName", menuView.getSchoolNameTextField().getText());
		menuJSONObject.put("date", menuView.getYearComboBox().getSelectedItem() + "/"
				+ menuView.getMonthComboBox().getSelectedItem() + "/" + menuView.getDayComboBox().getSelectedItem());
		JSONArray dayJSONArray = new JSONArray();
		menuJSONObject.put("dayArray", dayJSONArray);
		if (menuView.getMonday().getDayCheckBox().isSelected() == true) {
		}
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
	private JSONObject dayViewToJSONFormat(DayView day) {
		JSONObject dayJSONObject = new JSONObject();
		dayJSONObject.put("dayCheck", true);
		dayJSONObject.put("name", day.getDayCheckBox().getText());
		JSONObject stapleFoodJSONObject = foodViewToJSONFormat(day.getStapleFoodTextField().getText(),
				day.getStapleFoodIngredientView().getRowViewArrayList());

		JSONObject mainCourseJSONObject = foodViewToJSONFormat(day.getMainCourseTextField().getText(),
				day.getMainCourseIngredientView().getRowViewArrayList());

		JSONObject sideDishOneJSONObject = foodViewToJSONFormat(day.getSideDishOneTextField().getText(),
				day.getSideDishOneIngredientView().getRowViewArrayList());

		JSONObject sideDishSecondJSONObject = foodViewToJSONFormat(day.getSideDishSecondTextField().getText(),
				day.getSideDishSecondIngredientView().getRowViewArrayList());

		JSONObject soupJSONObject = foodViewToJSONFormat(day.getSoupTextField().getText(),
				day.getSoupIngredientView().getRowViewArrayList());

		JSONArray acceptanceJSONArray = new JSONArray();
		JSONObject acceptanceJSONObject = new JSONObject();

		for (RowView rowView : day.getAcceptanceView().getRowViewArrayList()) {
			if (!rowView.getNameTextField().getText().equals("")) {
				acceptanceJSONObject.put("name", rowView.getNameTextField().getText());
				acceptanceJSONObject.put("unit", rowView.getUnitTextField().getText());
				acceptanceJSONArray.add(acceptanceJSONObject);
				acceptanceJSONObject = new JSONObject();
			}
		}

		dayJSONObject.put("stapleFood", stapleFoodJSONObject);
		dayJSONObject.put("mainCourse", mainCourseJSONObject);
		dayJSONObject.put("sideDishOne", sideDishOneJSONObject);
		dayJSONObject.put("sideDishSecond", sideDishSecondJSONObject);
		dayJSONObject.put("soup", soupJSONObject);
		dayJSONObject.put("acceptanceArray", acceptanceJSONArray);
		return dayJSONObject;
	}

	@SuppressWarnings("unchecked")
	private JSONObject foodViewToJSONFormat(String food, ArrayList<RowView> rowViewArrayList) {
		JSONObject foodJSONObject = new JSONObject();
		foodJSONObject.put("name", food);

		JSONArray ingredientJSONArray = new JSONArray();
		JSONObject ingredientJSONObject = new JSONObject();

		for (RowView rowView : rowViewArrayList) {
			if (!rowView.getNameTextField().getText().equals("")) {
				ingredientJSONObject.put("name", rowView.getNameTextField().getText());
				ingredientJSONObject.put("unit", rowView.getUnitTextField().getText());
				ingredientJSONArray.add(ingredientJSONObject);

				ingredientJSONObject = new JSONObject();
			}
		}

		foodJSONObject.put("ingredientArray", ingredientJSONArray);

		return foodJSONObject;
	}
}
