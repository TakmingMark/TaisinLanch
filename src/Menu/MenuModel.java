package Menu;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import Component.MenuDataComponent;

public class MenuModel {
	MenuDataComponent menuComponent;

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
		menuComponent = new Gson().fromJson(bufferedReader, MenuDataComponent.class);
		menuComponent.print();
	}
}
