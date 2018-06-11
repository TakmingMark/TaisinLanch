package word;

import java.awt.Dimension;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.ArrayList;

import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;

public class testCreateDocument {

	public static void main(String[] args) throws Exception {
		FileOutputStream fileOutputStream = new FileOutputStream(new File("file/taisin.docx"));
		XWPFDocument document = new XWPFDocument();
		setMargin(document);
	
		setTitle(document);
		setTitleColumn(document);
		
		
		XWPFTable table=document.createTable(1,4);
		table.getRow(0).getCell(0).getParagraphs().get(0).createRun().setText("test");
		table.getRow(0).getCell(1).getParagraphs().get(0).createRun().setText("test2");
		table.getRow(0).getCell(2).getParagraphs().get(0).createRun().setText("test2");
		table.getRow(0).getCell(3).getParagraphs().get(0).createRun().setText("test2");
		
		setNewLine(document);
		
		
		document.write(fileOutputStream);
		fileOutputStream.close();
		System.out.println("createdocument.docx written successully");
	}

	private static void setMargin(XWPFDocument document) {
		CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
		CTPageMar pageMar = sectPr.addNewPgMar();
		pageMar.setLeft(BigInteger.valueOf(720L));
		pageMar.setTop(BigInteger.valueOf(1440L));
		pageMar.setRight(BigInteger.valueOf(720L));
		pageMar.setBottom(BigInteger.valueOf(1440L));
	}
	
	private static void setTitle(XWPFDocument document) {
		XWPFParagraph paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun run = paragraph.createRun();
		run.setFontSize(16);
		run.setText("苗栗縣泰興國民小學學生午餐第週菜單食材驗收(106下)");
	}
	
	private static void setTitleColumn(XWPFDocument document) {
		ArrayList<XWPFRun> runRow1=new ArrayList<XWPFRun>();
		int[] cols = { 300, 100, 200, 1000, 200, 1000, 1000, 800 };
		String[] row1ColName= {"日期","星期","主食","副食","湯","材料/n(合格v，不合格x)","","驗收或說明"};
		XWPFTable table = document.createTable(1, 8);

		for (int i = 0; i < table.getNumberOfRows(); i++) {
			XWPFTableRow row = table.getRow(i);
			int numCells = row.getTableCells().size();
			for (int j = 0; j < numCells; j++) {
				XWPFTableCell cell = row.getCell(j);
				cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf((long) (cols[j] * 3)));
			}
		}
		
		for(int i=0;i<8;i++) {
			XWPFRun run=table.getRow(0).getCell(i).getParagraphs().get(0).createRun();
			run.setFontSize(16);
			run.setText(row1ColName[i]);
			runRow1.add(table.getRow(0).getCell(i).getParagraphs().get(0).createRun());
		}
		
		mergeCellsHorizontal(table,0,5,6);

		for(int i=0;i<table.getRow(0).getTableICells().size();i++) {
			table.getRow(0).getCell(i).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
			table.getRow(0).getTableCells().get(i).setVerticalAlignment( XWPFTableCell.XWPFVertAlign.CENTER);
		}
	}
	
	public static void setMealMenuOfDay(XWPFDocument document) {
		
	}
	
	public static void setNewLine(XWPFDocument document) {
		
		for(XWPFTable table:document.getTables()) {
			for(XWPFTableRow tableRow:table.getRows()) {
				for(XWPFTableCell tableCell:tableRow.getTableCells()) {
					XWPFParagraph paragraph=tableCell.getParagraphs().get(0);
					XWPFRun beforeRun=paragraph.getRuns().get(0);
					XWPFRun afterRun=paragraph.createRun();
					String[] texts=tableCell.getText().split("/n");
					int fontSize=beforeRun.getFontSize();
					if(texts.length>1) {
						paragraph.removeRun(0);
						for(int i=0;i<texts.length;i++) {
							afterRun.setText(texts[i]);
							if(texts.length-1!=i)
								afterRun.addBreak();
						}
						afterRun.setFontSize(fontSize);
					}
						
				}
			}
		}
	}
	
	public static void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {
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
}
