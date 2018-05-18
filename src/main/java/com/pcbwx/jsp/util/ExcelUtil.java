package com.pcbwx.jsp.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

/**
 * 文件处理工具类
 * @author USER-P5KPL_SE
 *
 */
public class ExcelUtil {
	
	/**
	 * 设置Excel局部样式
	 * @param sheet 
	 * @param cellStyle 单元格样式
	 * @param rowStart 起始行
	 * @param rowEnd 结束行
	 * @param colStart 起始列
	 * @param colEnd 结束列
	 */
	public static void setPartStyle(Sheet sheet , CellStyle cellStyle , int rowStart ,int rowEnd , int colStart,int colEnd) {
		for (int i = rowStart; i < rowEnd+1; i++) {
			for(int j = colStart; j<colEnd+1;j++){
				if(sheet.getRow(i).getCell(j) != null){
					sheet.getRow(i).getCell(j).setCellStyle(cellStyle);
				}else{
					sheet.getRow(i).createCell(j).setCellStyle(cellStyle);
				}
			}
		}
		
	}
	
	/**
	 * 设置Excel列表头
	 * @param sheet 
	 * @param columnName 列名的数组
	 * @param cellStyle 单元格样式
	 * @param rowNum 表头所在的行号(0是第一行)
	 */
	public static void setColumnTitle(Sheet sheet, String[] columnName, CellStyle cellStyle, int rowNum) {
		Row row = sheet.createRow(rowNum);
		Cell cell;
		for (int i = 0; i < columnName.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(columnName[i]);
			cell.setCellStyle(cellStyle);
		}
	}
	
	public static void setColumnTitle2(Sheet sheet, List<String> columnNameList, CellStyle cellStyle, int rowNum) {
		Row row = sheet.createRow(rowNum);
		Cell cell;
		for (int i = 0; i < columnNameList.size(); i++) {
			cell = row.createCell(i);
			cell.setCellValue(columnNameList.get(i));
			cell.setCellStyle(cellStyle);
		}
	}
		
	/***
	 * 文件复制
	 * @param inFilePath 输入路径
	 * @param outFilePath 输出路径
	 * @return
	 */
	public static boolean fileChannelCopy(String inFilePath, String outFilePath) {
		FileInputStream fi = null;
		FileOutputStream fo = null;
		FileChannel in = null;
		FileChannel out = null;
		if (fileExist(inFilePath, false)) {
			try {
				fi = new FileInputStream(inFilePath);
				fo = new FileOutputStream(outFilePath);
				in = fi.getChannel();//得到对应的文件通道
				out = fo.getChannel();//得到对应的文件通道
				in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			} finally {
				try {
					fi.close();
					in.close();
					fo.close();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			return false;
		}
		return true;
	}
	
	/****
	 * 文件Excel下载
	 * @param filePath 文件路径
	 * @param response
	 */
	public void downLoadExcel(String filePath, String downLoadFileName, HttpServletResponse response) {
		OutputStream toClient = null;
		try {
			// path是指欲下载的文件的路径。
			File file = new File(filePath);
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(downLoadFileName.getBytes("utf-8"),"iso8859-1"));
			response.addHeader("Content-Length", "" + file.length());
			toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/vnd.ms-excel");
			toClient.write(buffer);
			toClient.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (null != toClient) {
				try {
					toClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/****
	 * 文件Excel下载
	 * @param filePath 文件路径
	 * @param response
	 */
	public void downLoadExcel(Workbook workBook, String downLoadFileName, HttpServletResponse response) {
		OutputStream toClient = null;
		try {
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(downLoadFileName.getBytes("utf-8"),"iso8859-1"));
			toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/vnd.ms-excel");
			workBook.write(toClient);
			toClient.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (null != toClient) {
				try {
					toClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/** 
	 * 判断文件是否存在
	 * @param filePath 文件路径
	 * @param noExistToCreate 如果路径不存在是否创建(true=>创建，false=>不创建)
	 * @return
	 */
	private static Boolean fileExist(String filePath, Boolean noExistToCreate) {
		File file = new File(filePath);
		if (!file.exists() && noExistToCreate) {
			file.mkdirs();
		}
		return true;
	}
	
	/** 
	 * 组合set数组的getName方法的字符串
	 * @param set
	 * @return 字符串
	 */
	public static String combineSet(Set<Object> set){
		String str = "";
		if(set.size()>0){
			for(Object obj : set){
				try {
					if(StringUtil.isBlank(str)){
						str = obj.getClass().getMethod("getName").invoke(obj).toString();
					}else{
						str = str + "," + obj.getClass().getMethod("getName").invoke(obj).toString();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return str;
	}
	/** 
	 * 组合set数组的getName方法的字符串
	 * @param set
	 * @return 字符串
	 */
	public static String combineSetById(Set<Object> set){
		String str = "";
		if(set.size()>0){
			for(Object obj : set){
				try {
					if(StringUtil.isBlank(str)){
						str = obj.getClass().getMethod("getId").invoke(obj).toString();
					}else{
						str = str + "," + obj.getClass().getMethod("getId").invoke(obj).toString();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return str;
	}
	
	/**
	 * excle文件：设置列宽度自适应(POI)
	 * @param sheet工作薄
	 */
	public static void setColumnAdaptation(Sheet sheet){
		//总行数
		int rowNum = sheet.getLastRowNum();
		int columnNum = sheet.getRow(0).getPhysicalNumberOfCells();
		Map<Integer,Integer> map = new HashMap<Integer, Integer>();
		for(int j=0;j<columnNum;j++){
			map.put(j, 0);
		}
		Row row = null;
		Cell cell = null;
		for(int i=0;i<rowNum+1;i++){
			row = sheet.getRow(i);
			for(int j=0;j<columnNum;j++){
				cell = row.getCell(j);
				int cellLength = 0;
				if(StringUtil.isNotBlank(getStringValueFromCell(cell))){
					cellLength = (int)(getStringValueFromCell(cell).getBytes().length+0.72)*256;
				}
				if(cellLength>map.get(j)){
					map.put(j, cellLength);
				}
			}
		}
		for(int j=0;j<columnNum;j++){
			sheet.setColumnWidth(j, map.get(j));
		}
	} 
	
	/**
	 * 设置列宽
	 * @param sheet
	 * @param width
	 */
	public static void setColumnWidth(Sheet sheet, int width){
		int columnNum = sheet.getRow(0).getPhysicalNumberOfCells();
		for(int j=0;j<columnNum;j++){
			sheet.setColumnWidth(j, width);
		}
	}
	
	
	
	/**
	 * 获得单元格内容并转为字符串格式
	 * @param cell
	 * @return
	 */
	public static String getStringValueFromCell(Cell cell){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//DecimalFormat df = new DecimalFormat("#.#");
		String cellValue = "";
		if(cell==null || cell.getCellType()==Cell.CELL_TYPE_BLANK){
			return cellValue;
		}else if(cell.getCellType()==Cell.CELL_TYPE_STRING){
			cellValue = cell.getStringCellValue();
		}else if(cell.getCellType()==SXSSFCell.CELL_TYPE_NUMERIC){
			if(HSSFDateUtil.isCellDateFormatted(cell)){
				double d = cell.getNumericCellValue();
				Date date = HSSFDateUtil.getJavaDate(d);
				cellValue = sdf.format(date);
			}else{
				cellValue = sdf.format(cell.getNumericCellValue());
			}
		}else if(cell.getCellType()==Cell.CELL_TYPE_BOOLEAN){
			cellValue = String.valueOf(cell.getBooleanCellValue());
		}else if(cell.getCellType()==Cell.CELL_TYPE_ERROR){
			cellValue = "";
		}else if(cell.getCellType()==Cell.CELL_TYPE_FORMULA){
			cellValue = cell.getCellFormula().toString();
		}
		return cellValue;
	}
	
	/**
	 * 生成标题行样式
	 * @param workbook
	 * @return
	 */
	public static CellStyle generateColumnTitleStyle(Workbook workbook){
		//生成一个样式
		CellStyle columnTitlestyle = workbook.createCellStyle();
		//设置样式
		columnTitlestyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);//下边框
		columnTitlestyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		columnTitlestyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
		columnTitlestyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
		columnTitlestyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		columnTitlestyle.setFillForegroundColor(HSSFColor.YELLOW.index);
		columnTitlestyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		columnTitlestyle.setFillBackgroundColor(HSSFColor.YELLOW.index);
		
		//生成一个字体
		Font font = workbook.createFont();
		font.setFontName("Arial");
		font.setFontHeightInPoints((short)11);
		//把字体应用到当前的样式
		columnTitlestyle.setFont(font);
		return columnTitlestyle;
	}
}
