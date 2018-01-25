package Excel;

public interface ExcelTextContent {

	public String menuSheetName="菜單";
	public String[] menuColumnNames= {"學校","日期","主食一","主食二","主菜","主菜一","主菜二","主菜三","副菜一","副菜二","副菜三","副菜四",	"副菜五","副菜六","蔬菜","湯品","附餐一","附餐二","全榖根莖","豆魚肉蛋","蔬菜","油脂與堅果種子","水果","乳品","熱量"};

	public String ingredientSheetName="食材";
	public String[] ingredientColumnNames= {"供餐日期","學校","菜色名稱","食材名稱","進貨日期","生產日期","有效日期","批號","製造商","供應商名稱","食材驗證標章","驗證號碼","產品名稱","重量(公斤)","非基改玉米","非基改黃豆","加工品"};
	public String ingredientBatchNumber="1";
	public String ingredientSupplier="新加南食品行";
	public String ingredientO="Y";
	public String ingredientP="Y";
	public String ingredinetQ="N";
	
}
