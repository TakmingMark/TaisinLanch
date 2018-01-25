package Excel;

public class ExcelModel {
	
	public String calculateMenuDate(String date, String day) {
		int calYear = Integer.valueOf(date.substring(0, 4));
		int calMonth = Integer.valueOf(date.substring(5, 7));
		int calDay = Integer.valueOf(date.substring(8, 10));

		switch (day) {
		case "星期一":
			calDay = calDay;
			break;
		case "星期二":
			calDay = calDay + 1;
			break;
		case "星期三":
			calDay = calDay + 2;
			break;
		case "星期四":
			calDay = calDay + 3;
			break;
		case "星期五":
			calDay = calDay + 4;
			break;
		default:
			break;
		}

		switch (calMonth) {
		case 4:
		case 6:
		case 9:
		case 11:
			if (calDay >= 31) {
				calMonth++;
				calDay -= 30;
			}
			break;
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
			if (calDay >= 32) {
				calMonth++;
				calDay -= 31;
			}
			break;
		case 2:
			if (calYear % 4 == 0) {
				if (calDay >= 30) {
					calMonth++;
					calDay -= 29;
				}
			} else {
				if (calDay >= 29) {
					calMonth++;
					calDay -= 28;

				}
			}

			break;
		case 12:
			if (calDay >= 32) {
				calYear++;
				calMonth = 01;
				calDay -= 31;
			}
			break;
		}
		date = (String.valueOf(calYear) + "/" + String.valueOf(calMonth) + "/" + String.valueOf(calDay));
		return date;
	}
	
	public String purchaseDate(String date, String day) {

		switch (day) {
		case "星期一":
			return calculateMenuDate(date,"星期一");
		case "星期二":
			return calculateMenuDate(date,"星期一");
		case "星期三":
			return calculateMenuDate(date,"星期一");
		case "星期四":
			return calculateMenuDate(date,"星期四");
		case "星期五":
			return calculateMenuDate(date,"星期五");
		default:
			return null;
		}
	}
	
	public String backSlashToDot(String string) {
		String[] stringArray=string.split("\\/");
		string="";
		for(String element:stringArray)
			string+=element;
		return string;
	}
}
