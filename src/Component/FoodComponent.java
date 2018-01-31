package Component;

import java.util.List;

public class FoodComponent{
	private String name;
	private List<IngredientComponent> ingredientArray;
	public String getName() {
		return name;
	}
	public List<IngredientComponent> getIngredientArray() {
		return ingredientArray;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setIngredientArray(List<IngredientComponent> ingredientArray) {
		this.ingredientArray = ingredientArray;
	}
}