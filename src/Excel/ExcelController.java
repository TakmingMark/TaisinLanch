package Excel;

public class ExcelController {
	private ExcelModel excelModel=null;
	private String schoolMenuExcelPath="excel/123.xls";
	protected ExcelController() {
		initExcelController();

	}
	
	public static ExcelController getExcelControllerObject() {
		return new ExcelController();
	}
	
	private void initExcelController() {
		excelModel=ExcelModel.getExcelModelObject();
		excelModel.setSchoolMenuExcelPath(schoolMenuExcelPath);
		
		excelModel.readSchoolMenuExcel();
	}
	
	public ExcelModel getExcelModel() {
		return excelModel;
	} 
}
