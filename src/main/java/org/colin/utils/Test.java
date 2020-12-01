package org.colin.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
/**
 * @Description:
 * @ClassName: Test
 * @Author: wujiangbo
 * @Date: 2020/6/15 0015 20:38
 * @Version: 1.1.0
 */
public class Test {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Map<Integer, List<Object>> map = initType("D:/work/123.xls" , 0,0,0);
        long endTime = System.currentTimeMillis();
        System.out.println("耗时="+(endTime-startTime));
        System.out.println("map="+map);
    }

    public static Map<Integer, List<Object>> initType(String path, int sheetNum, int startRow, int endRow) {
            Map<Integer, List<Object>> returnMap = new HashMap<>();
        try {
            //1 获取excel文件流   excel  xls 文件   暂不支持xlsx
            if (path.contains("xlsx") || path.contains("XLSX")) {
                System.err.println("请使用xls格式文件");
                return returnMap;
            }
            InputStream inputStream = new FileInputStream(path);
            POIFSFileSystem fileSystem = new POIFSFileSystem(inputStream);
            HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
            //获取指定sheet页
            HSSFSheet sheet = workbook.getSheetAt(sheetNum);
            for(int i=startRow; i<=endRow; i++){
                List<Object> headerColumns = new ArrayList<>();
                //读取第i行
                HSSFRow headerRow = sheet.getRow(i);
                for (int j = 0; j < headerRow.getPhysicalNumberOfCells(); j++) {
                    HSSFCell cell = headerRow.getCell(j);
                    String cellValue = "";
                    switch(cell.getCellType()){
                        case STRING:
                            cellValue = cell.getStringCellValue();//字符串
                            break;
                        case NUMERIC:
                            cellValue = cell.getNumericCellValue() + "";//数字
                            break;
                        case BOOLEAN:
                            cellValue = cell.getBooleanCellValue() + "";//Boolean
                            break;
                        case FORMULA:
                            cellValue = cell.getCellFormula() + "";//公式
                            break;
                        case BLANK:
                            cellValue = "";//空值
                            break;
                        case ERROR:
                            cellValue = "非法字符";//非法字符
                            break;
                        default:
                            cellValue = "未知类型";//未知类型
                            break;
                    }
                    headerColumns.add(cellValue);
                }
                returnMap.put(i, headerColumns);
            }
//            //获取sheet    所有行数
//            int rows = sheet.getPhysicalNumberOfRows();
//            List<String> headerColumns = new ArrayList<>();
//            for (int j = 0; j < headerRow.getPhysicalNumberOfCells(); j++) {
//                HSSFCell cell = headerRow.getCell(j);
//                cell.getStringCellValue();
//                headerColumns.add(cell.getStringCellValue());
//            }
//            //获取类型
//            HSSFRow secondRow = sheet.getRow(1);
//            int cells = secondRow.getPhysicalNumberOfCells();
//            //对第二行的数据进行操作
//            for (int j = 1; j < rows; j++) {
//                HSSFRow row = sheet.getRow(j);
//                List<String> cellsValue = new ArrayList<>();
//                //获取字段属性
//                for (int k = 2; k < cells; k++) {
//                    cellsValue.add(row.getCell(k).getStringCellValue());
//                }
//            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnMap;
    }

    public static String DateToStr(Date date) {
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String str = format.format(date);
        return str;
    }
}
