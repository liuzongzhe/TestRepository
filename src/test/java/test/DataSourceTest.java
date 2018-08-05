package test;

import lzz.App;
import lzz.controller.User;
import lzz.util.FileUtil;
import lzz.util.POIUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Author lzz
 * Date   2018/5/24
 */
@SpringBootTest(classes = App.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class DataSourceTest {

    @Test
    public void testExcel() {

        try {

            File file = new File("E:/E/test.xlsx");

//            String sheetName = "用户表0";
//            int firstRowNum = 3;
//            int firstCellNum = 1;

//            String sheetName = "用户表1";
//            int firstRowNum = 6;
//            int firstCellNum = 2;

            String sheetName = "用户表2";
            int firstRowNum = 4;
            int firstCellNum = 1;

//            String sheetName = "用户表3";
//            int firstRowNum = 3;
//            int firstCellNum = 2;

            String[] excelUser = POIUtil.readSingleExcel(file, sheetName, firstRowNum, firstCellNum);
            User user = new User();
            user.setId(excelUser[0]);
            user.setUsername(excelUser[1]);
            user.setPassword(excelUser[2]);
            user.setAge(Integer.parseInt(excelUser[3]));
            user.setAddress(excelUser[4]);
            System.out.println(user);

            FileUtil.writeStringToFile(user.toString(), "E:/E/temp.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFileUtil() {
        String content = "insert into user(id, username, password) values('001', '张三', '456')";
        File file = new File("E:/E/temp.txt");
        FileUtil.writeStringToFile(content, file);
    }
}
