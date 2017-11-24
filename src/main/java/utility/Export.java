package utility;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import entity.DetailReimbursement;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

public class Export {
	private Workbook workbook = new XSSFWorkbook();
	private int firstRow;
	private int lastRow;
	
	private CellStyle setCellStyle() {
		CellStyle style = workbook.createCellStyle();
		style.setBorderBottom(BorderStyle.MEDIUM);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(BorderStyle.MEDIUM);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(BorderStyle.MEDIUM);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderRight(BorderStyle.MEDIUM);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		return style;
	}
	
	private CellStyle setHeaderCellStyle() {
		CellStyle headerStyle = setCellStyle();
		Font font = workbook.createFont();
		font.setBold(true);
		headerStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerStyle.setFont(font);
		return headerStyle;
	}

	public void exportDataToExel(String filename,List<DetailReimbursement> rem) throws Exception{
		CellStyle headerStyle = setHeaderCellStyle();
		CellStyle style = setCellStyle();
		int rowIndex=1;
		if(!filename.endsWith("xlsx")) {
			throw new Exception("invalid filename or format");
		}
		
		Sheet sheet = workbook.createSheet("DataSource");
		Iterator<DetailReimbursement> reimbursement= rem.iterator();
		Row rowH = sheet.createRow(0);
		Cell cellH0 = rowH.createCell(0);
		cellH0.setCellValue("Nama Karyawan");
		cellH0.setCellStyle(headerStyle);
		Cell cellH1 = rowH.createCell(1);
		cellH1.setCellValue("Nama Project");
		cellH1.setCellStyle(headerStyle);
		Cell cellH2 = rowH.createCell(2);
		cellH2.setCellValue("Tipe Claim");
		cellH2.setCellStyle(headerStyle);
		Cell cellH3 = rowH.createCell(3);
		cellH3.setCellValue("Subtotal");
		cellH3.setCellStyle(headerStyle);
		while(reimbursement.hasNext()) {
			DetailReimbursement re = reimbursement.next();
			
			Row row0 = sheet.createRow(rowIndex++);
			Cell cellt0 = row0.createCell(0);
			cellt0.setCellValue(re.getMstKaryawan().getNamaKaryawan());
			cellt0.setCellStyle(style);
			Cell cellt1 = row0.createCell(1);
			cellt1.setCellValue(re.getMstProject().getNamaProject());
			cellt1.setCellStyle(style);
			Cell cellt2 = row0.createCell(2);
			cellt2.setCellValue("Transport");
			cellt2.setCellStyle(style);
			Cell cellt3 = row0.createCell(3);
			cellt3.setCellValue(re.getTransport());
			cellt3.setCellStyle(style);
			
			Row row1 = sheet.createRow(rowIndex++);
			Cell cellp0 = row1.createCell(0);
			cellp0.setCellValue(re.getMstKaryawan().getNamaKaryawan());
			cellp0.setCellStyle(style);
			Cell cellp1 = row1.createCell(1);
			cellp1.setCellValue(re.getMstProject().getNamaProject());
			cellp1.setCellStyle(style);
			Cell cellp2 = row1.createCell(2);
			cellp2.setCellValue("Parkir");
			cellp2.setCellStyle(style);
			Cell cellp3 = row1.createCell(3);
			cellp3.setCellValue(re.getParkir());
			cellp3.setCellStyle(style);
			
			Row row2 = sheet.createRow(rowIndex++);
			Cell cellk0 = row2.createCell(0);
			cellk0.setCellValue(re.getMstKaryawan().getNamaKaryawan());
			cellk0.setCellStyle(style);
			Cell cellk1 = row2.createCell(1);
			cellk1.setCellValue(re.getMstProject().getNamaProject());
			cellk1.setCellStyle(style);
			Cell cellk2 = row2.createCell(2);
			cellk2.setCellValue("Kesehatan");
			cellk2.setCellStyle(style);
			Cell cellk3 = row2.createCell(3);
			cellk3.setCellValue(re.getKesehatan());
			cellk3.setCellStyle(style);
			
			Row row3 = sheet.createRow(rowIndex++);
			Cell cellb0 = row3.createCell(0);
			cellb0.setCellValue(re.getMstKaryawan().getNamaKaryawan());
			cellb0.setCellStyle(style);
			Cell cellb1 = row3.createCell(1);
			cellb1.setCellValue(re.getMstProject().getNamaProject());
			cellb1.setCellStyle(style);
			Cell cellb2 = row3.createCell(2);
			cellb2.setCellValue("BPJS");
			cellb2.setCellStyle(style);
			Cell cellb3 = row3.createCell(3);
			cellb3.setCellValue(re.getBpjs());
			cellb3.setCellStyle(style);
			
			Row row4 = sheet.createRow(rowIndex++);
			Cell cellrm0 = row4.createCell(0);
			cellrm0.setCellValue(re.getMstKaryawan().getNamaKaryawan());
			cellrm0.setCellStyle(style);
			Cell cellrm1 = row4.createCell(1);
			cellrm1.setCellValue(re.getMstProject().getNamaProject());
			cellrm1.setCellStyle(style);
			Cell cellrm2 = row4.createCell(2);
			cellrm2.setCellValue("Reward Monthly");
			cellrm2.setCellStyle(style);
			Cell cellrm3 = row4.createCell(3);
			cellrm3.setCellValue(re.getRewardMonthly());
			cellrm3.setCellStyle(style);
			
			Row row5 = sheet.createRow(rowIndex++);
			Cell cellrt0 = row5.createCell(0);
			cellrt0.setCellValue(re.getMstKaryawan().getNamaKaryawan());
			cellrt0.setCellStyle(style);
			Cell cellrt1 = row5.createCell(1);
			cellrt1.setCellValue(re.getMstProject().getNamaProject());
			cellrt1.setCellStyle(style);
			Cell cellrt2 = row5.createCell(2);
			cellrt2.setCellValue("Reward Triwulan");
			cellrt2.setCellStyle(style);
			Cell cellrt3 = row5.createCell(3);
			cellrt3.setCellValue(re.getRewardTriwulan());
			cellrt3.setCellStyle(style);
			
			Row row6 = sheet.createRow(rowIndex++);
			Cell celltx0 = row6.createCell(0);
			celltx0.setCellValue(re.getMstKaryawan().getNamaKaryawan());
			celltx0.setCellStyle(style);
			Cell celltx1 = row6.createCell(1);
			celltx1.setCellValue(re.getMstProject().getNamaProject());
			celltx1.setCellStyle(style);
			Cell celltx2 = row6.createCell(2);
			celltx2.setCellValue("Taxi");
			celltx2.setCellStyle(style);
			Cell celltx3 = row6.createCell(3);
			celltx3.setCellValue(re.getTaxi());
			celltx3.setCellStyle(style);
			
			Row row7 = sheet.createRow(rowIndex++);
			Cell celll0 = row7.createCell(0);
			celll0.setCellValue(re.getMstKaryawan().getNamaKaryawan());
			celll0.setCellStyle(style);
			Cell celll1 = row7.createCell(1);
			celll1.setCellValue(re.getMstProject().getNamaProject());
			celll1.setCellStyle(style);
			Cell celll2 = row7.createCell(2);
			celll2.setCellValue("Lembur");
			celll2.setCellStyle(style);
			Cell celll3 = row7.createCell(3);
			celll3.setCellValue(re.getLembur());
			celll3.setCellStyle(style);
			
			Row row8 = sheet.createRow(rowIndex++);
			Cell cellei0 = row8.createCell(0);
			cellei0.setCellValue(re.getMstKaryawan().getNamaKaryawan());
			cellei0.setCellStyle(style);
			Cell cellei1 = row8.createCell(1);
			cellei1.setCellValue(re.getMstProject().getNamaProject());
			cellei1.setCellStyle(style);
			Cell cellei2 = row8.createCell(2);
			cellei2.setCellValue("Entertain Internal");
			cellei2.setCellStyle(style);
			Cell cellei3 = row8.createCell(3);
			cellei3.setCellValue(re.getEntertainInternal());
			cellei3.setCellStyle(style);
			
			Row row9 = sheet.createRow(rowIndex++);
			Cell cellex0 = row9.createCell(0);
			cellex0.setCellValue(re.getMstKaryawan().getNamaKaryawan());
			cellex0.setCellStyle(style);
			Cell cellex1 = row9.createCell(1);
			cellex1.setCellValue(re.getMstProject().getNamaProject());
			cellex1.setCellStyle(style);
			Cell cellex2 = row9.createCell(2);
			cellex2.setCellValue("Entertain External");
			cellex2.setCellStyle(style);
			Cell cellex3 = row9.createCell(3);
			cellex3.setCellValue(re.getEntertainEksternal());
			cellex3.setCellStyle(style);
			
			Row row10 = sheet.createRow(rowIndex++);
			Cell cello0 = row10.createCell(0);
			cello0.setCellValue(re.getMstKaryawan().getNamaKaryawan());
			cello0.setCellStyle(style);
			Cell cello1 = row10.createCell(1);
			cello1.setCellValue(re.getMstProject().getNamaProject());
			cello1.setCellStyle(style);
			Cell cello2 = row10.createCell(2);
			cello2.setCellValue(re.getDeskripsiOther());
			cello2.setCellStyle(style);
			Cell cello3 = row10.createCell(3);
			cello3.setCellValue(re.getNilaiOther());
			cello3.setCellStyle(style);
		}
		setFirstRow(sheet.getFirstRowNum());
		setLastRow(sheet.getLastRowNum());
		for(int i=0;i<4;i++) {
			sheet.autoSizeColumn(i);
		}
		FileOutputStream fos = new FileOutputStream(new File(filename));
		workbook.write(fos);
		fos.close();
		System.out.println(filename+" telah berhasil dibuat");
		
	}
	
	public int getFirstRow() {
		return firstRow;
	}

	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}

	public int getLastRow() {
		return lastRow;
	}

	public void setLastRow(int lastRow) {
		this.lastRow = lastRow;
	}
	
	

}
