package lzz.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * excel读写工具类
 * @author lzz
 * @date 2018/8/5
 */
public class POIUtil {

    private final static String xls = "xls";
    private final static String xlsx = "xlsx";


    /**
     * 读入excel文件,单行数据
     * @param file           excel文件
     * @param sheetName      sheet名称
     * @param firstRowNum    开始读取的行号
     * @param firstCellNum   开始读取的列号
     * @return
     * @throws IOException
     */
    public static String[] readSingleExcel(File file, String sheetName, int firstRowNum, int firstCellNum)
            throws IOException {
        //检查文件
        checkFile(file);

        //获得Workbook工作薄对象
        Workbook workbook = getWorkBook(file);
        if (workbook == null) {
            return null;
        }

        //建议用sheet名称来获取Sheet对象
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            return null;
        }

        Row row = sheet.getRow(firstRowNum - 1);
        if (row == null) {
            return null;
        }
        //获得当前行的列数
        int lastCellNum = row.getLastCellNum();

        String[] cells = new String[lastCellNum];
        int p = 0;
        //循环当前行
        for (int cellNum = firstCellNum - 1; cellNum < lastCellNum; cellNum++) {
            Cell cell = row.getCell(cellNum);
            cells[p++] = getCellValue(cell);
        }

        return cells;
    }


    public static void checkFile(File file) throws IOException {
        //判断文件是否存在
        if (null == file) {
            throw new FileNotFoundException("文件不存在！");
        }
        //获得文件名
        String fileName = file.getName();

        //判断文件是否是excel文件
        if (!fileName.endsWith(xls) && !fileName.endsWith(xlsx)) {
            throw new IOException(fileName + "不是excel文件");
        }
    }


    public static Workbook getWorkBook(File file) {
        //获得文件名
        String fileName = file.getName();
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            //获取excel文件的io流
            InputStream input = new FileInputStream(file);
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if (fileName.endsWith(xls)) {
                //2003
                workbook = new HSSFWorkbook(input);
            } else if (fileName.endsWith(xlsx)) {
                //2007
                workbook = new XSSFWorkbook(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }


    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        //判断数据的类型
        switch (cell.getCellType()) {
            //数字
            case Cell.CELL_TYPE_NUMERIC:
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            //字符串
            case Cell.CELL_TYPE_STRING:
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            //Boolean
            case Cell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            //公式
            case Cell.CELL_TYPE_FORMULA:
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            //空值
            case Cell.CELL_TYPE_BLANK:
                cellValue = "";
                break;
            //故障
            case Cell.CELL_TYPE_ERROR:
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }
}
