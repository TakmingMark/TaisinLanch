package word;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.ArrayList;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTable.XWPFBorderType;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.w3c.dom.css.RGBColor;

import Component.DayComponent;
import Component.IngredientComponent;
import Component.MenuDataComponent;

public class Word {
	private static int maxClsCount = 8;

	private Word() {

	}

	public static Word getWordObject() {
		return new Word();
	}

	public void exportDataToExcel(MenuDataComponent menuOutputData) {
		writeWord(menuOutputData);
	}

	private void writeWord(MenuDataComponent menuOutputData) {
		String filePath = "word/taisin" + "第" + menuOutputData.getWeek() + "週" + ".docx";
		XWPFDocument document = new XWPFDocument();

		setMargin(document);
		setTitle(document,menuOutputData);
		setTitleColumn(document);
		setMealMenuOfWeek(document, menuOutputData);
		setBottomLine(document);
		mergeTitleColumn(document);
		setNewLine(document);
		setTableBoderSize(document);

		try {
			FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));
			document.write(fileOutputStream);
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setMargin(XWPFDocument document) {
		CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
		CTPageMar pageMar = sectPr.addNewPgMar();
		pageMar.setLeft(BigInteger.valueOf(720L));
		pageMar.setTop(BigInteger.valueOf(1440L));
		pageMar.setRight(BigInteger.valueOf(720L));
		pageMar.setBottom(BigInteger.valueOf(1440L));
	}

	private void setTitle(XWPFDocument document, MenuDataComponent menuOutputData) {
		String title=menuOutputData.getSchoolName()+"午餐第"+menuOutputData.getWeek()+"週菜單食材驗收";
		String schoolYear=calSchoolYear(menuOutputData.getDate());
		XWPFParagraph paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun run = paragraph.createRun();
		run.setFontSize(18);
		run.setFontFamily("標楷體");
		run.setText(menuOutputData.getSchoolName()+"午餐第"+menuOutputData.getWeek()+"週菜單食材驗收("+schoolYear+")");
	}

	private void setTitleColumn(XWPFDocument document) {
		ArrayList<XWPFRun> runRow1 = new ArrayList<XWPFRun>();
		int[] cols = { 300, 100, 200, 600, 200, 1200, 1200, 800 };
		String[] colName = { "日期", "星期", "主食", "副食", "湯", "材料/n(合格v，不合格x)", "", "驗收或說明" };
		XWPFTable table = document.createTable(1, 8);

		for (int i = 0; i < table.getNumberOfRows(); i++) {
			XWPFTableRow row = table.getRow(i);
			int numCells = row.getTableCells().size();
			for (int j = 0; j < numCells; j++) {
				XWPFTableCell cell = row.getCell(j);
				cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf((long) (cols[j] * 3)));
			}
		}

		for (int i = 0; i < maxClsCount; i++) {
			XWPFRun run = table.getRow(0).getCell(i).getParagraphs().get(0).createRun();
			run.setFontSize(18);
			run.setFontFamily("標楷體");
			run.setText(colName[i]);
			runRow1.add(table.getRow(0).getCell(i).getParagraphs().get(0).createRun());
		}

		for (int i = 0; i < table.getRow(0).getTableICells().size(); i++) {
			table.getRow(0).getCell(i).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
			table.getRow(0).getTableCells().get(i).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
		}
	}

	private void setMealMenuOfWeek(XWPFDocument document, MenuDataComponent menuOutputData) {
		// setMealMenuOfDay(document, menuOutputData.getDayArray().get(0),
		// menuOutputData.getDate());
		for (DayComponent day : menuOutputData.getDayArray()) {
			setMealMenuOfDay(document, day, menuOutputData.getDate());
		}
	}

	private void setMealMenuOfDay(XWPFDocument document, DayComponent day, String startDay) {
		ArrayList<IngredientComponent> ingredientList = new ArrayList<>();
		ArrayList<XWPFRun> runClosList = new ArrayList<XWPFRun>();
		String dayDate = calculateExcelDayDate(startDay, day.getName());
		String name = day.getName().substring(2);
		String stapleFood = day.getStapleFood().getName();
		String mainCourse = day.getMainCourse().getName();
		String sideDishone = day.getSideDishOne().getName();
		String sideDishSecond = day.getSideDishSecond().getName();
		String soup = day.getSoup().getName();
		String acceptance = "";
		int rowSize = 0;
		XWPFTable table;

		ingredientList.addAll(day.getMainCourse().getIngredientArray());
		ingredientList.addAll(day.getSideDishOne().getIngredientArray());
		ingredientList.addAll(day.getSideDishSecond().getIngredientArray());
		ingredientList.addAll(day.getSoup().getIngredientArray());

		rowSize = ingredientList.size() / 2 + ingredientList.size() % 2;
		table = document.createTable(rowSize, maxClsCount);

		for (int i = 0; i < maxClsCount; i++) {
			XWPFRun run = table.getRow(0).getCell(i).getParagraphs().get(0).createRun();
			run.setFontSize(14);
			run.setFontFamily("標楷體");
			runClosList.add(run);
		}

		for (int i = 0; i < ingredientList.size(); i += 2) {
			XWPFRun run1 = table.getRow(i / 2).getCell(5).getParagraphs().get(0).createRun();
			run1.setFontSize(14);
			run1.setFontFamily("標楷體");
			run1.setText(ingredientList.get(i).getName() + ingredientList.get(i).getUnit() + "斤");
			if (i + 1 < ingredientList.size()) {
				XWPFRun run2 = table.getRow(i / 2).getCell(6).getParagraphs().get(0).createRun();
				run2.setFontSize(14);
				run2.setFontFamily("標楷體");
				run2.setText(ingredientList.get(i + 1).getName() + ingredientList.get(i + 1).getUnit() + "斤");
			}
		}

		for (int i = 0; i < day.getAcceptanceArray().size(); i++) {
			acceptance = acceptance + day.getAcceptanceArray().get(i).getName()
					+ day.getAcceptanceArray().get(i).getUnit();
			if (i != day.getAcceptanceArray().size() - 1) {
				acceptance = acceptance + "/n";
			}
		}

		runClosList.get(0).setText(convertVerticalText(dayDate));
		runClosList.get(1).setText(name);
		runClosList.get(2).setText(convertVerticalText(stapleFood));
		runClosList.get(3).setText(mainCourse + "/n" + sideDishone + "/n" + sideDishSecond);
		runClosList.get(4).setText(convertVerticalText(soup));
		runClosList.get(7).setText(acceptance);

		mergeCellsVertically(table, 0, 0, rowSize - 1);
		mergeCellsVertically(table, 1, 0, rowSize - 1);
		mergeCellsVertically(table, 2, 0, rowSize - 1);
		mergeCellsVertically(table, 3, 0, rowSize - 1);
		mergeCellsVertically(table, 4, 0, rowSize - 1);
		mergeCellsVertically(table, 7, 0, rowSize - 1);

		for (int i = 0; i < table.getRows().size(); i++) {
			for (int j = 0; j < table.getRows().get(i).getTableCells().size(); j++) {
				if (i == 0 && (j == 0 || j == 1 || j == 2 || j == 4)) {
					table.getRow(i).getCell(j).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
					table.getRow(i).getTableCells().get(j).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
				} else if (j == 3 || j == 4 || j == 7) {
					table.getRow(i).getCell(j).getParagraphs().get(0).setAlignment(ParagraphAlignment.LEFT);
					table.getRow(i).getTableCells().get(j).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
				} else {
					table.getRow(i).getCell(j).getParagraphs().get(0).setAlignment(ParagraphAlignment.LEFT);
				}

			}
		}
	}

	private void setBottomLine(XWPFDocument document) {
		XWPFParagraph paragraph = document.createParagraph();
		XWPFRun run = paragraph.createRun();
		run.setFontSize(16);
		run.setFontFamily("標楷體");
		run.addBreak();
		run.setText("午餐秘書:                    主任:                    校長:                    ");
	}

	private void setNewLine(XWPFDocument document) {

		for (XWPFTable table : document.getTables()) {
			for (XWPFTableRow tableRow : table.getRows()) {
				for (XWPFTableCell tableCell : tableRow.getTableCells()) {
					XWPFParagraph paragraph = tableCell.getParagraphs().get(0);
					if (paragraph.getRuns().size() > 0) {
						XWPFRun beforeRun = paragraph.getRuns().get(0);
						String[] texts = tableCell.getText().split("/n");
						if (texts.length > 1) {
							int fontSize = beforeRun.getFontSize();
							while (paragraph.getRuns().size() != 0) {
								paragraph.removeRun(0);
							}
							XWPFRun afterRun = paragraph.createRun();
							for (int i = 0; i < texts.length; i++) {
								afterRun.setText(texts[i]);
								if (texts.length - 1 != i)
									afterRun.addBreak();
							}
							afterRun.setFontSize(fontSize);
							afterRun.setFontFamily("標楷體");
						}
					}
				}
			}
		}
	}

	private void setTableBoderSize(XWPFDocument document) {
		for (XWPFTable table : document.getTables()) {
			table.getCTTbl().getTblPr().getTblBorders().getTop().setSz(BigInteger.valueOf(14));
			table.getCTTbl().getTblPr().getTblBorders().getLeft().setSz(BigInteger.valueOf(14));
			table.getCTTbl().getTblPr().getTblBorders().getRight().setSz(BigInteger.valueOf(14));
			table.getCTTbl().getTblPr().getTblBorders().getBottom().setSz(BigInteger.valueOf(14));
			table.getCTTbl().getTblPr().getTblBorders().getInsideH().setSz(BigInteger.valueOf(14));
			table.getCTTbl().getTblPr().getTblBorders().getInsideV().setSz(BigInteger.valueOf(14));
		}
	}

	private void mergeTitleColumn(XWPFDocument document) {
		XWPFTable table = document.getTables().get(0);
		mergeCellsHorizontal(table, 0, 5, 6);
	}

	private void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {
		for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
			XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
			if (cellIndex == fromCell) {
				// The first merged cell is set with RESTART merge value
				cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
			} else {
				// Cells which join (merge) the first one, are set with CONTINUE
				cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
			}
		}
	}

	private void mergeCellsVertically(XWPFTable table, int col, int fromRow, int toRow) {
		for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
			XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
			if (rowIndex == fromRow) {
				// The first merged cell is set with RESTART merge value
				cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
			} else {
				// Cells which join (merge) the first one, are set with CONTINUE
				cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
			}
		}
	}

	String calculateExcelDayDate(String date, String day) {
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
		date = (String.valueOf(calMonth) + "月" + String.valueOf(calDay) + "日");
		return date;
	}

	private String convertVerticalText(String text) {
		String newText = "";
		for (int i = 0; i < text.length(); i++) {
			newText = newText + text.substring(i, i + 1) + "/n";
		}
		return newText;
	}

	private String calSchoolYear(String date) {
		int year=Integer.valueOf(date.split("\\/")[0])-1911;
		int month=Integer.valueOf(date.split("\\/")[1]);
		
		if(month<7)
			return year-1+"下";
		else
			return year+"上";
	}
}
