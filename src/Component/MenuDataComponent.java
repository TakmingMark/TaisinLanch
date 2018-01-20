package Component;
import java.util.List;
public class MenuDataComponent {
	private String schoolName;
	private String date;
	private List<Day> day;
	
	public void print() {
		System.out.println(schoolName);
	}
}
class Day{
	private String name;
	private Foodtest stapleFood,mainCourse,sideDishOne,sideDishSecond,soup;
	private List<String> acceptance;
}
class Food{
	private String name;
	private List<String> Ingredient;
}
