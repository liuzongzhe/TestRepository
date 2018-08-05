package lzz.util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lzz
 * @date 2018/8/5
 */
public class FileUtil {

    public static void writeStringToFile(String content, File file) {
        writeStringToFile(content, file.getAbsolutePath());
    }


    public static void writeStringToFile(String content, String filePath) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(new Date());
        Writer writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filePath, true));
            writer.write("\r\n");
            writer.write("=========" + date + "=========");
            writer.write("\r\n");
            writer.write(content);
            writer.write("\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
