package Component;

import java.util.List;

public class FoodComponent{
	private String name;
	private List<String> ingredient;
	public String getName() {
		return name;
	}
	public List<String> getIngredient() {
		return ingredient;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setIngredient(List<String> ingredient) {
		this.ingredient = ingredient;
	}
}