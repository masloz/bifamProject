package com.yjp.bifam.util.excelIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class ExcelIO {
	@RequestMapping(value = "excelIO.bf", method = RequestMethod.GET)
	public String contactView(){
		return "utilTest/excelIO";
	}
	
//	refinement.bf
	@RequestMapping(value = "refinement.bf", method = RequestMethod.POST)
	public String refinementExcel(MultipartHttpServletRequest msr, String path){	// path는 기존 경로
		MultipartFile mf = msr.getFile("file");
		String fullname = msr.getSession().getServletContext().getRealPath("/") + mf.getOriginalFilename();
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(fullname);
			fos.write(mf.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		File file = new File(fullname);
		
		FileInputStream fis = null;
		XSSFWorkbook workbook = null;
		XSSFWorkbook newWorkbook = new XSSFWorkbook();
		
		// Style (change background color & font)
		CellStyle colorStyle = newWorkbook.createCellStyle();
		colorStyle.setFillBackgroundColor((short)1);
		colorStyle.setFillPattern((short) 1);
		XSSFFont font = newWorkbook.createFont();
		font.setColor(IndexedColors.WHITE.getIndex());
		colorStyle.setFont(font);
		
		
		try {
			fis = new FileInputStream(file);
			workbook = new XSSFWorkbook(fis);
			int colIdx = 0;
			XSSFSheet sheet = workbook.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			System.out.println(rows);
			// 새로운 Sheet 생성
			XSSFSheet newSheet = newWorkbook.createSheet("Presentation Data");
			
			// 이름 바뀔 때마다 집계할 변수들
			String compareName = "";
			int newRowIdx = 0;
			double[] sum = new double[15];
			List<Double[]> list = new ArrayList<>();
			
			int cnt = 0;
			// row 시작
			for(int rowIdx = 0; rowIdx < rows; rowIdx++){
				// 기존 DATA row
				XSSFRow row = sheet.getRow(rowIdx);
				
				// 새로운 Sheet DATA row
				XSSFRow newRow = newSheet.createRow(newRowIdx + rowIdx);
				if(rowIdx > 3)
					list.add(new Double[15]);
				System.out.print(rowIdx + "\t");
				if(row != null){
					int cells = row.getPhysicalNumberOfCells();
					for(colIdx = 0; colIdx < cells; colIdx++){
						XSSFCell cell = row.getCell(colIdx);
						
						XSSFCell newCell = newRow.createCell(colIdx);
						
						if(colIdx == 0){
							compareName = cell.getStringCellValue();
						}
						String value = "";
						if(cell == null){
							continue;
						}else{
							switch (cell.getCellType()){
							case XSSFCell.CELL_TYPE_FORMULA:
								value = cell.getCellFormula();
								newCell.setCellFormula(String.valueOf(value));
								break;
							case XSSFCell.CELL_TYPE_NUMERIC:
								value = cell.getNumericCellValue() + "";
								newCell.setCellValue(Double.parseDouble(value));
								break;
							case XSSFCell.CELL_TYPE_STRING:
								value = cell.getStringCellValue();
								newCell.setCellValue(value);
								break;
							case XSSFCell.CELL_TYPE_BLANK:
								value = cell.getBooleanCellValue() + "";
								newCell.setCellValue(Boolean.parseBoolean(value));
								break;
							case XSSFCell.CELL_TYPE_ERROR:
								value = cell.getErrorCellValue() + "";
								break;
							}
						}
						
						if(colIdx > 8 && rowIdx > 3){
							double dTemp = Double.parseDouble(value);
							sum[colIdx - 9] += dTemp;
							list.get(cnt)[colIdx - 9] = dTemp;
						}
						System.out.print("("+colIdx+")"+value + "\t");
					}	// cell
				}
				if(rowIdx > 3)
					cnt++;
				System.out.println();
				
				
				// 이름 바뀔 때마다 집계
				if(rowIdx > 3 && rowIdx != rows && rowIdx < rows){
					String temp;
					if(rowIdx == rows - 1)
						temp = "";
					else
						temp = sheet.getRow(rowIdx + 1).getCell(0).getStringCellValue();
					if(!compareName.equals(temp)){
						// 평균
						XSSFRow avgRow1 = newSheet.createRow(++newRowIdx + rowIdx);
						avgRow1.createCell(7).setCellStyle(colorStyle);
						avgRow1.getCell(7).setCellValue("average");
						avgRow1.createCell(8).setCellValue(compareName);
						for(int n = 0; n < sum.length; n++){
							// 9 부터
							XSSFCell newCell = avgRow1.createCell(9 + n);
							newCell.setCellValue(sum[n] / cnt);
						}
						XSSFRow avgRow2 = newSheet.createRow(++newRowIdx + rowIdx);
						avgRow2.createCell(7).setCellStyle(colorStyle);
						avgRow2.createCell(8).setCellStyle(colorStyle);
						
						// Standard error (S.E)
						XSSFRow SERow1 = newSheet.createRow(++newRowIdx + rowIdx);
						SERow1.createCell(7).setCellStyle(colorStyle);
						SERow1.getCell(7).setCellValue("S.E");
						SERow1.createCell(8).setCellValue(compareName);
						
						for(int k = 0; k < 15; k++){
							XSSFCell newCell = SERow1.createCell(9 + k);
							double se = 0;
							for(int l = 0; l < list.size(); l++){
								se += Math.pow((sum[k] / cnt) - list.get(l)[k], 2);
							}
							newCell.setCellValue(Math.sqrt(se / Math.pow(list.size(), 2)));
						}
						
						XSSFRow SERow2 = newSheet.createRow(++newRowIdx + rowIdx);
						SERow2.createCell(7).setCellStyle(colorStyle);
						SERow2.createCell(8).setCellStyle(colorStyle);
						
						// 다시 초기화
						Arrays.fill(sum, 0);
						cnt = 0;
						list = new ArrayList<>();
					}
				}
			} // row
			
			for(int i = 13; i < 23; i++){
				newSheet.setColumnHidden(i, true);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try{
				if(workbook != null)
					workbook.close();
				if(fis != null)
					fis.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 출력
		fos = null;
		try {
			System.out.println(mf.getOriginalFilename());
			System.out.println(path.substring(0, path.lastIndexOf("\\") + 1) + mf.getOriginalFilename().substring(0, mf.getOriginalFilename().lastIndexOf(".")) + " (1).xlsx");
			fos = new FileOutputStream(path.substring(0, path.lastIndexOf("\\") + 1) + mf.getOriginalFilename().substring(0, mf.getOriginalFilename().lastIndexOf(".")) + " (1).xlsx");
			newWorkbook.write(fos);
			fos.close();
			newWorkbook.close();
			System.out.println("엑셀 파일 생성");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "utilTest/excelIO";
	}
	
}
