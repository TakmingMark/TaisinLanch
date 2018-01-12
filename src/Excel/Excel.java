package Excel;

public class Excel extends ExcelController{

	private Excel() {
		super();
		initExcel();
	}
	
	public static Excel getExcelObject() {
		return new Excel();
	}
	
	private void initExcel() {
		
	}
}
