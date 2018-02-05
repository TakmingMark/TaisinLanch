package Parser;

import java.util.List;

import javax.swing.JTextField;

import Component.DayComponent;
import Component.FoodComponent;
import Component.IngredientComponent;
import Component.MenuDataComponent;
import Menu.MenuView;
import View.DayView;
import View.ZoomRowView;



public class DataToViewParser {
	public static DataToViewParser getDataToViewParserObject() {
		return new DataToViewParser();
	}
	
	public void menuDataInputToMenuView(MenuDataComponent menuDataInput,MenuView menuView) {
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
}
