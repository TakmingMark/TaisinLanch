package word;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;

import Component.DayComponent;
import Component.IngredientComponent;
import Component.MenuDataComponent;

public class Word {
	private static final int maxClsCount = 8;

	private Word() {

	}

	public static Word getWordObject() {
		return new Word();
	}

	public void exportDataToExcel(MenuDataComponent menuOutputData) {
		writeWord(menuOutputData);
	}

	private void writeWord(MenuDataComponent menuOutputData) {
		String filePath = "word/" + menuOutputData.getSchoolName() + "第" + menuOutputData.getWeek() + "週" + ".docx";
		XWPFDocument document = new XWPFDocument();

		setMargin(document);
		setTitle(document, menuOutputData);
		setTitleColumn(document);
		setMealMenuOfWeek(document, menuOutputData);
		setBottomLine(document);
		setTableAlign(document, ParagraphAlignment.CENTER);
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
		String schoolYear = calSchoolYear(menuOutputData.getDate());
		String title = menuOutputData.getSchoolName() + "午餐第" + menuOutputData.getWeek() + "週菜單食材驗收(" + schoolYear
				+ ")";
		setRunInWord(document, 18, title);
	}

	private void setTitleColumn(XWPFDocument document) {
		int[] cols = { 300, 100, 200, 600, 200, 1200, 1200, 800 };

		XWPFTable table = document.createTable(1, maxClsCount);
		XWPFTableRow row = table.getRow(0);

		int numCells = row.getTableCells().size();
		for (int j = 0; j < numCells; j++) {
			XWPFTableCell cell = row.getCell(j);
			cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf((long) (cols[j] * 3)));
		}

		for (int i = 0; i < maxClsCount; i++) {
			setRunInField(table, 0, i, WordTextContent.colNames[i]);
		}

		for (int i = 0; i < table.getRow(0).getTableICells().size(); i++) {
			table.getRow(0).getCell(i).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
			table.getRow(0).getTableCells().get(i).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
		}
	}

	private void setMealMenuOfWeek(XWPFDocument document, MenuDataComponent menuOutputData) {
		for (DayComponent day : menuOutputData.getDayArray()) {
			setMealMenuOfDay(document, day, menuOutputData.getDate());
		}
	}

	private void setMealMenuOfDay(XWPFDocument document, DayComponent day, String startDay) {
		ArrayList<IngredientComponent> ingredientList = calIngredientArrayList(day);
		int rows = ingredientList.size() / 2 + ingredientList.size() % 2;
		XWPFTable table = document.createTable(rows, maxClsCount);
		setDayDateOfDay(table, day, startDay);
		setNameOfDay(table, day);
		setStapleFoodOfDay(table, day);
		setSideDishOfDay(table, day);
		setSoupOfDay(table, day);
		setIngredientOfDay(table, ingredientList);
		setAcceptanceOfDay(table, day);
		mergeCellsVerticallyInDay(table, rows);
		sortCellTextPositionInDay(table);
	}

	private ArrayList<IngredientComponent> calIngredientArrayList(DayComponent day) {
		ArrayList<IngredientComponent> ingredientList = new ArrayList<>();

		ingredientList.addAll(day.getMainCourse().getIngredientArray());
		ingredientList.addAll(day.getSideDishOne().getIngredientArray());
		ingredientList.addAll(day.getSideDishSecond().getIngredientArray());
		ingredientList.addAll(day.getSoup().getIngredientArray());

		return ingredientList;
	}

	private void setDayDateOfDay(XWPFTable table, DayComponent day, String startDay) {
		String dayDate = calculateExcelDayDate(startDay, day.getName());
		dayDate = convertVerticalText(dayDate);
		setRunInCell(table, 0, 0, dayDate);
	}

	private void setNameOfDay(XWPFTable table, DayComponent day) {
		String name = day.getName().substring(2);
		setRunInCell(table, 0, 1, name);
	}

	private void setStapleFoodOfDay(XWPFTable table, DayComponent day) {
		String staple = day.getStapleFood().getName();
		staple = convertVerticalText(staple);
		setRunInCell(table, 0, 2, staple);
	}

	private void setSideDishOfDay(XWPFTable table, DayComponent day) {
		String mainCourse = day.getMainCourse().getName();
		String sideDishone = day.getSideDishOne().getName();
		String sideDishSecond = day.getSideDishSecond().getName();
		String sideDish = mainCourse + "/n" + sideDishone + "/n" + sideDishSecond;
		setRunInCell(table, 0, 3, sideDish);
	}

	private void setSoupOfDay(XWPFTable table, DayComponent day) {
		String soup = day.getSoup().getName();
		soup = convertVerticalText(soup);
		setRunInCell(table, 0, 4, soup);
	}

	private void setIngredientOfDay(XWPFTable table, ArrayList<IngredientComponent> ingredientList) {
		int rows = ingredientList.size() / 2 + ingredientList.size() % 2;

		for (int i = 0; i < ingredientList.size(); i++) {
			String ingredient = ingredientList.get(i).getName() + ingredientList.get(i).getUnit();
			if (i >= rows)
				setRunInCell(table, i % rows, 6, ingredient);
			else
				setRunInCell(table, i % rows, 5, ingredient);
		}
	}

	private void setAcceptanceOfDay(XWPFTable table, DayComponent day) {
		String acceptance = "";
		for (int i = 0; i < day.getAcceptanceArray().size(); i++) {
			acceptance = acceptance + day.getAcceptanceArray().get(i).getName()
					+ day.getAcceptanceArray().get(i).getUnit();
			if (i != day.getAcceptanceArray().size() - 1) {
				acceptance = acceptance + "/n";
			}
		}
		setRunInCell(table, 0, 7, acceptance);
	}

	private void setRunInCell(XWPFTable table, int row, int cls, String text) {
		XWPFRun run = table.getRow(row).getCell(cls).getParagraphs().get(0).createRun();
		run.setFontSize(14);
		run.setFontFamily(WordTextContent.fontStyle);
		run.setText(text);
	}

	private void setRunInField(XWPFTable table, int row, int cls, String text) {
		XWPFRun run = table.getRow(row).getCell(cls).getParagraphs().get(0).createRun();
		run.setFontSize(18);
		run.setFontFamily(WordTextContent.fontStyle);
		run.setText(text);
	}

	private void setRunInWord(XWPFDocument document, int fontSize, String text) {
		XWPFParagraph paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);

		XWPFRun run = paragraph.createRun();
		run.setFontSize(fontSize);
		run.setFontFamily(WordTextContent.fontStyle);
		run.setText(text);
	}

	private void mergeCellsVerticallyInDay(XWPFTable table, int rowDistance) {
		mergeCellsVertically(table, 0, 0, rowDistance - 1);
		mergeCellsVertically(table, 1, 0, rowDistance - 1);
		mergeCellsVertically(table, 2, 0, rowDistance - 1);
		mergeCellsVertically(table, 3, 0, rowDistance - 1);
		mergeCellsVertically(table, 4, 0, rowDistance - 1);
		mergeCellsVertically(table, 7, 0, rowDistance - 1);
	}

	private void sortCellTextPositionInDay(XWPFTable table) {
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
		setRunInWord(document, 16, WordTextContent.buttomText);
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
							afterRun.setFontFamily(WordTextContent.fontStyle);
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

	private void setTableAlign(XWPFDocument document, ParagraphAlignment align) {
		for (XWPFTable table : document.getTables()) {
			CTTblPr tblPr = table.getCTTbl().getTblPr();
			CTJc jc = (tblPr.isSetJc() ? tblPr.getJc() : tblPr.addNewJc());
			STJc.Enum en = STJc.Enum.forInt(align.getValue());
			jc.setVal(en);
		}

	}

	private String calculateExcelDayDate(String date, String day) {
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
		int year = Integer.valueOf(date.split("\\/")[0]) - 1911;
		int month = Integer.valueOf(date.split("\\/")[1]);

		if (month < 7)
			return year - 1 + "下";
		else
			return year + "上";
	}
}
