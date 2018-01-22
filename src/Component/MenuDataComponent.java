package Component;
import java.util.List;
public class MenuDataComponent {
	private String schoolName;
	private String date;
	private List<Day> day;
	public String getSchoolName() {
		return schoolName;
	}
	public String getDate() {
		return date;
	}
	public List<Day> getDay() {
		return day;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setDay(List<Day> day) {
		this.day = day;
	}
	
}



