package Menu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.RequestingUserName;
import javax.swing.JTextField;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import Component.DayComponent;
import Component.FoodComponent;
import Component.IngredientComponent;
import Component.MenuDataComponent;
import Component.RowView;
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
		excel = Excel.getExcelObject();
	}

	public void menuDataInputToMenuView(MenuView menuView) {
		parseJSONFromMenuFile();
		menuView.getSchoolNameTextField().setText(menuDataInput.getSchoolName());
		menuView.getYearComboBox().setSelectedItem(menuDataInput.getDate().split("\\/")[0]);
		menuView.getMonthComboBox().setSelectedItem(menuDataInput.getDate().split("\\/")[1]);
		menuView.getDayComboBox().setSelectedItem(menuDataInput.getDate().split("\\/")[2]);

		for (DayComponent day : menuDataInput.getDayArray()) {
			switch (day.getName()) {
			case "星期一":
				jSONFormatToDayView(menuView.getMonday(), day);
				break;
			case "星期二":
				jSONFormatToDayView(menuView.getTuesday(), day);
				break;
			case "星期三":
				jSONFormatToDayView(menuView.getWednesday(), day);
				break;
			case "星期四":
				jSONFormatToDayView(menuView.getThursday(), day);
				break;
			case "星期五":
				jSONFormatToDayView(menuView.getFriday(), day);
				break;
			default:
				break;
			}
		}
	}

	private void parseJSONFromMenuFile() {
		JsonReader jsonReader = null;
		try {
			jsonReader = new JsonReader(
					new BufferedReader(new InputStreamReader(new FileInputStream("json/menu.json"), "UTF-8")));
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
		menuDataInput = new Gson().fromJson(jsonReader, MenuDataComponent.class);
	}

	private void jSONFormatToDayView(DayView dayView, DayComponent day) {
		dayView.getDayCheckBox().setSelected(true);

		jSONFormatToFoodView(dayView.getStapleFoodTextField(), day.getStapleFood(),
				dayView.getStapleFoodIngredientView());

		jSONFormatToFoodView(dayView.getMainCourseTextField(), day.getMainCourse(),
				dayView.getMainCourseIngredientView());

		jSONFormatToFoodView(dayView.getSideDishOneTextField(), day.getSideDishOne(),
				dayView.getSideDishOneIngredientView());

		jSONFormatToFoodView(dayView.getSideDishSecondTextField(), day.getSideDishSecond(),
				dayView.getSideDishSecondIngredientView());

		jSONFormatToFoodView(dayView.getSoupTextField(), day.getSoup(), dayView.getSoupIngredientView());

		for (IngredientComponent accpetance : day.getAcceptanceArray()) {
			dayView.getAcceptanceView().insertDataToZoomRowView(accpetance);
		}
	}

	private void jSONFormatToFoodView(JTextField foodName, FoodComponent food, ZoomRowView zoomRowView) {
		foodName.setText(food.getName());
		jSONFormatToIngredientView(food.getIngredientArray(), zoomRowView);

	}

	private void jSONFormatToIngredientView(List<IngredientComponent> ingredientArray, ZoomRowView zoomRowView) {
		for (IngredientComponent ingredientElement : ingredientArray)
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
	public JSONObject dayViewToJSONFormat(DayView day) {
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
	public JSONObject foodViewToJSONFormat(String food, ArrayList<RowView> rowViewArrayList) {
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

	public void exportDataToExcel() {
		excel.exportDataToExcel(menuDataOutput);
	}

	public void recordDataToJsonFile() {
		parseJSONFromFoodFile();
		// FileOutputStream fileOutputStream = null;
		// File file;
		// new Gson().
		// try {
		//
		// file = new File("c:/newfile.txt");
		// fop = new FileOutputStream(file);
		//
		// if (!file.exists()) {
		// file.createNewFile();
		// }
		//
		// // get the content in bytes
		//
		// fileOutputStream.write(contentInBytes);
		// fileOutputStream.flush();
		// fileOutputStream.close();
		//
		// System.out.println("Done");
		//
		// } catch (IOException e) {
		// e.printStackTrace();
		// } finally {
		// try {
		// if (fileOutputStream != null) {
		// fileOutputStream.close();
		// }
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
	}

	private void parseJSONFromFoodFile() {
		Map<String, List<IngredientComponent>> foodMap = new HashMap();
		JsonReader bufferedReader = null;
		JSONParser parser = new JSONParser();

//		Object obj;
//		try {
//			 obj = (JSONArray)parser.parse(new FileReader(new FileInputStream("json/food.json", "UTF-8"));
////			obj = parser
////					.parse(new BufferedReader(new InputStreamReader(new FileInputStream("json/food.json"), "UTF-8")));
//
//			JSONArray food = (JSONArray) obj;
//			Iterator<String> iterator = food.iterator();
//			while (iterator.hasNext()) {
//				System.out.println(iterator.next());
//			}
//		} catch (IOException | ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

	public void getFoodListFromMenuDataOutput() {
		Map<String, List<IngredientComponent>> foodMap = new HashMap();

		for (DayComponent day : menuDataOutput.getDayArray()) {
			foodMap.put(day.getMainCourse().getName(), day.getMainCourse().getIngredientArray());
			foodMap.put(day.getSideDishOne().getName(), day.getSideDishOne().getIngredientArray());
			foodMap.put(day.getSideDishSecond().getName(), day.getSideDishSecond().getIngredientArray());
			foodMap.put(day.getSoup().getName(), day.getSoup().getIngredientArray());
		}
	}

	public void analysisIngredient() {

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
