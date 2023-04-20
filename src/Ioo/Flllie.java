package Ioo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class Flllie {
    public static void main(String[] args) {
        try {
            // 指定字符编码方式为“UTF-8”，跟.txt文件的字符编码相同
            FileInputStream fi = new FileInputStream("src/Ioo/FileReader.txt");
            FileOutputStream fo = new FileOutputStream("src/Ioo/Filewriter.txt");
            InputStreamReader inStream = new InputStreamReader(fi, "UTF-8");
            OutputStreamWriter writerStream = new OutputStreamWriter(fo, "UTF-8");
            BufferedReader br = new BufferedReader(inStream);
            BufferedWriter bw = new BufferedWriter(writerStream);
            String s = "";
            while ((s = br.readLine()) != null) {
                bw.write(s);
                bw.newLine();
            }
            bw.flush(); // 将缓冲区中的数据全部写出
            br.close();
            bw.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
