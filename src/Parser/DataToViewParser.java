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

	public void menuDataInputToMenuView(MenuDataComponent menuDataInput, MenuView menuView) {
		menuView.getSchoolNameTextField().setText(menuDataInput.getSchoolName());
		menuView.getYearComboBox().setSelectedItem(menuDataInput.getDate().split("\\/")[0]);
		menuView.getMonthComboBox().setSelectedItem(menuDataInput.getDate().split("\\/")[1]);
		menuView.getDayComboBox().setSelectedItem(menuDataInput.getDate().split("\\/")[2]);

		for (DayComponent day : menuDataInput.getDayArray()) {
			switch (day.getName()) {
			case "星期一":
				DayDatatToDayView(menuView.getMonday(), day);
				break;
			case "星期二":
				DayDatatToDayView(menuView.getTuesday(), day);
				break;
			case "星期三":
				DayDatatToDayView(menuView.getWednesday(), day);
				break;
			case "星期四":
				DayDatatToDayView(menuView.getThursday(), day);
				break;
			case "星期五":
				DayDatatToDayView(menuView.getFriday(), day);
				break;
			default:
				break;
			}
		}
	}

	private void DayDatatToDayView(DayView dayView, DayComponent day) {
		dayView.getDayCheckBox().setSelected(true);

		FoodDataToFoodView(dayView.getStapleFoodTextField(), day.getStapleFood(),
				dayView.getStapleFoodIngredientView());

		FoodDataToFoodView(dayView.getMainCourseTextField(), day.getMainCourse(),
				dayView.getMainCourseIngredientView());

		FoodDataToFoodView(dayView.getSideDishOneTextField(), day.getSideDishOne(),
				dayView.getSideDishOneIngredientView());

		FoodDataToFoodView(dayView.getSideDishSecondTextField(), day.getSideDishSecond(),
				dayView.getSideDishSecondIngredientView());

		FoodDataToFoodView(dayView.getSoupTextField(), day.getSoup(), dayView.getSoupIngredientView());

		for (IngredientComponent accpetance : day.getAcceptanceArray()) {
			dayView.getAcceptanceView().insertDataToZoomRowView(accpetance);
		}
	}

	private void FoodDataToFoodView(JTextField foodName, FoodComponent food, ZoomRowView zoomRowView) {
		foodName.setText(food.getName());
		ingredientDataToIngredientView(food.getIngredientArray(), zoomRowView);
	}

	public void ingredientDataToIngredientView(List<IngredientComponent> ingredientArray, ZoomRowView zoomRowView) {
		if (!zoomRowView.isExistData())
			for (IngredientComponent ingredientElement : ingredientArray)
				zoomRowView.insertDataToZoomRowView(ingredientElement);
	}

}
