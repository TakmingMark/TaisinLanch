package Parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.channels.NonReadableChannelException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import Component.DayComponent;
import Component.FoodComponent;
import Component.IngredientComponent;
import Component.MenuDataComponent;

public class MenuDataAndFileConverter {
	public static MenuDataAndFileConverter getMenuDataAndFileConverterObject() {
		return new MenuDataAndFileConverter();
	}

	public MenuDataComponent getMenuDataFromMenuFile(String foodFliePath) {
		JsonReader jsonReader = null;
		try {
			jsonReader = new JsonReader(
					new BufferedReader(new InputStreamReader(new FileInputStream(foodFliePath), "UTF-8")));
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
		return new Gson().fromJson(jsonReader, MenuDataComponent.class);
	}

	public void mergeFoodDataToFoodFile(MenuDataComponent menuDataOutput, String foodFliePath) {
		Map<String, List<IngredientComponent>> oldFoodMap, newFoodMap;
		oldFoodMap = getFoodMapFromFoodFile(foodFliePath);
		newFoodMap = getFoodMapFromMenuDataOutput(menuDataOutput);
		oldFoodMap.putAll(newFoodMap);

		writeFoodFileFromFoodMap(oldFoodMap);
	}

	public Map<String, List<IngredientComponent>> getFoodMapFromFoodFile(String foodFliePath) {
		Map<String, List<IngredientComponent>> foodMap = new HashMap();
		JsonReader jsonReader = null;
		try {
			jsonReader = new JsonReader(
					new BufferedReader(new InputStreamReader(new FileInputStream(foodFliePath), "UTF-8")));

			jsonReader.beginArray();
			while (jsonReader.hasNext()) {
				FoodComponent food = getFoodFromJSONReader(jsonReader);
				foodMap.put(food.getName(), food.getIngredientArray());
			}
			jsonReader.endArray();

		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return foodMap;
	}

	public void getHistoryFileFromMenuData(MenuDataComponent menuDataOutput, String historyFliePath) {
		Gson gson = new Gson();
		String json = gson.toJson(menuDataOutput);
		
		//Convert object to JSON string and save into a file directly
		try (BufferedWriter writer = 
				new BufferedWriter(
						new OutputStreamWriter(
								new FileOutputStream(
										historyFliePath), "UTF-8"))) {
			gson.toJson(menuDataOutput, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private FoodComponent getFoodFromJSONReader(JsonReader jsonReader) {
		FoodComponent food = new FoodComponent();
		ArrayList<IngredientComponent> ingredientArray = new ArrayList<>();
		String filedName = null;
		try {
			jsonReader.beginObject();
			while (jsonReader.hasNext()) {
				filedName = jsonReader.nextName();
				if (filedName.equals("name")) {
					food.setName(jsonReader.nextString());
				} else if (filedName.equals("ingredientArray")) {
					jsonReader.beginArray();
					while (jsonReader.hasNext()) {
						ingredientArray.add(getIngredientFromJSONReader(jsonReader));
					}
					jsonReader.endArray();
				}
			}
			jsonReader.endObject();
		} catch (IOException e) {
			e.printStackTrace();
		}
		food.setIngredientArray(ingredientArray);
		return food;
	}

	private IngredientComponent getIngredientFromJSONReader(JsonReader jsonReader) {
		String filedName = null;
		IngredientComponent ingredient = new IngredientComponent();
		try {
			jsonReader.beginObject();
			while (jsonReader.hasNext()) {
				filedName = jsonReader.nextName();
				if (filedName.equals("name")) {
					ingredient.setName(jsonReader.nextString());
				} else if (filedName.equals("unit")) {
					ingredient.setUnit(jsonReader.nextString());
				}
			}
			jsonReader.endObject();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ingredient;
	}

	private Map<String, List<IngredientComponent>> getFoodMapFromMenuDataOutput(MenuDataComponent menuDataOutput) {
		Map<String, List<IngredientComponent>> foodMap = new HashMap();

		for (DayComponent day : menuDataOutput.getDayArray()) {
			foodMap.put(day.getMainCourse().getName(), day.getMainCourse().getIngredientArray());
			foodMap.put(day.getSideDishOne().getName(), day.getSideDishOne().getIngredientArray());
			foodMap.put(day.getSideDishSecond().getName(), day.getSideDishSecond().getIngredientArray());
			foodMap.put(day.getSoup().getName(), day.getSoup().getIngredientArray());
		}
		return foodMap;
	}

	private void writeFoodFileFromFoodMap(Map<String, List<IngredientComponent>> foodMap) {
		try {
			JsonWriter jsonWriter = new JsonWriter(
					new BufferedWriter(new OutputStreamWriter(new FileOutputStream("json/food.json"), "UTF-8")));

			Iterator<Entry<String, List<IngredientComponent>>> iterator = foodMap.entrySet().iterator();
			jsonWriter.beginArray();
			while (iterator.hasNext()) {
				Map.Entry<String, List<IngredientComponent>> pair = (Map.Entry) iterator.next();
				jsonWriter.beginObject();
				jsonWriter.name("name").value(pair.getKey());
				jsonWriter.name("ingredientArray");
				jsonWriter.beginArray();
				for (IngredientComponent ingredient : pair.getValue()) {
					jsonWriter.beginObject();
					jsonWriter.name("name").value(ingredient.getName());
					jsonWriter.name("unit").value(ingredient.getUnit());
					jsonWriter.endObject();
				}
				jsonWriter.endArray();
				jsonWriter.endObject();
			}
			jsonWriter.endArray();

			jsonWriter.close();
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
