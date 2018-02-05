package Parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
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

public class FoodListJSONParser {

	public static FoodListJSONParser getFoodListJSONParserObject() {
		return new FoodListJSONParser();
	}

	public MenuDataComponent parseJSONFromMenuFile() {
		JsonReader jsonReader = null;
		try {
			jsonReader = new JsonReader(
					new BufferedReader(new InputStreamReader(new FileInputStream("json/menu.json"), "UTF-8")));
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
		return new Gson().fromJson(jsonReader, MenuDataComponent.class);
	}
	
	public Map<String, List<IngredientComponent>> parseJSONFromFoodFile() {
		Map<String, List<IngredientComponent>> foodMap = new HashMap();
		JsonReader jsonReader = null;
		try {
			jsonReader = new JsonReader(
					new BufferedReader(new InputStreamReader(new FileInputStream("json/food.json"), "UTF-8")));

			jsonReader.beginArray();
			while (jsonReader.hasNext()) {
				FoodComponent food = jsonReaderConverterFood(jsonReader);
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

	private FoodComponent jsonReaderConverterFood(JsonReader jsonReader) {
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
						ingredientArray.add(jsonReaderConverterIngredient(jsonReader));
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

	private IngredientComponent jsonReaderConverterIngredient(JsonReader jsonReader) {
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

	public Map<String, List<IngredientComponent>> parseMenuDataOutputToFoodList(MenuDataComponent menuDataOutput) {
		Map<String, List<IngredientComponent>> foodMap = new HashMap();

		for (DayComponent day : menuDataOutput.getDayArray()) {
			foodMap.put(day.getMainCourse().getName(), day.getMainCourse().getIngredientArray());
			foodMap.put(day.getSideDishOne().getName(), day.getSideDishOne().getIngredientArray());
			foodMap.put(day.getSideDishSecond().getName(), day.getSideDishSecond().getIngredientArray());
			foodMap.put(day.getSoup().getName(), day.getSoup().getIngredientArray());
		}
		return foodMap;
	}

	public void writeFoodFileFromFoodMap(Map<String, List<IngredientComponent>> foodMap) {
		try {
			JsonWriter jsonWriter = new JsonWriter(
					new BufferedWriter(new OutputStreamWriter(new FileOutputStream("json/food3.json"), "UTF-8")));

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
