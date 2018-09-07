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
				dayDataToDayView(menuView.getMonday(), day);
				break;
			case "星期二":
				dayDataToDayView(menuView.getTuesday(), day);
				break;
			case "星期三":
				dayDataToDayView(menuView.getWednesday(), day);
				break;
			case "星期四":
				dayDataToDayView(menuView.getThursday(), day);
				break;
			case "星期五":
				dayDataToDayView(menuView.getFriday(), day);
				break;
			default:
				break;
			}
		}
	}

	private void dayDataToDayView(DayView dayView, DayComponent day) {
		dayView.getDayCheckBox().setSelected(true);

		foodDataToFoodView(dayView.getStapleFoodTextField(), day.getStapleFood(),
				dayView.getStapleFoodIngredientView());

		foodDataToFoodView(dayView.getMainCourseTextField(), day.getMainCourse(),
				dayView.getMainCourseIngredientView());

		foodDataToFoodView(dayView.getSideDishOneTextField(), day.getSideDishOne(),
				dayView.getSideDishOneIngredientView());

		foodDataToFoodView(dayView.getSideDishSecondTextField(), day.getSideDishSecond(),
				dayView.getSideDishSecondIngredientView());

		foodDataToFoodView(dayView.getSoupTextField(), day.getSoup(), dayView.getSoupIngredientView());

		if(day.getAcceptanceArray()!=null)
			for (IngredientComponent accpetance : day.getAcceptanceArray()) {
				dayView.getAcceptanceView().insertDataToZoomRowView(accpetance);
			}
	}

	private void foodDataToFoodView(JTextField foodName, FoodComponent food, ZoomRowView zoomRowView) {
		foodName.setText(food.getName());
		ingredientDataToIngredientView(food.getIngredientArray(), zoomRowView);
	}

	public void ingredientDataToIngredientView(List<IngredientComponent> ingredientArray, ZoomRowView zoomRowView) {
		if (!zoomRowView.isExistData() && ingredientArray!=null)
			for (IngredientComponent ingredientElement : ingredientArray)
				zoomRowView.insertDataToZoomRowView(ingredientElement);
	}

}