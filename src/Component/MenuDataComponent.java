package Component;

import java.util.List;

public class MenuDataComponent {
	private String schoolName;
	private String date;
	private String supplierName;
	private String week;
	private List<DayComponent> dayArray;

	public String getSchoolName() {
		return schoolName;
	}

	public String getDate() {
		return date;
	}

	public List<DayComponent> getDayArray() {
		return dayArray;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setDayArray(List<DayComponent> dayArray) {
		this.dayArray = dayArray;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public String getWeek() {
		return week;
	}

}
