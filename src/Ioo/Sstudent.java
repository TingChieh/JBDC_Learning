package Ioo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Sstudent {
    public static void main(String[] args) {

        // 保存姓名、性别、年龄、成绩
        String outName = "小张", inName;
        char outSex = 'f', inSex;
        int outAge = 20, inAge;
        double outScore = 90, inScore;

        try {
            File file = new File("src/Ioo/Student.txt");
            FileOutputStream fos = new FileOutputStream(file);
            // 创建数据输出流对象
            DataOutputStream dos = new DataOutputStream(fos);
            // 开始写文件
            dos.writeUTF(outName);
            dos.writeChar(outSex);
            dos.writeInt(outAge);
            dos.writeDouble(outScore);
            dos.close();
            fos.close();
            // 结束
            FileInputStream fis = new FileInputStream(file);
            // 创建数据输入流对象
            DataInputStream dis = new DataInputStream(fis);
            // 开始读文件
            inName = dis.readUTF();
            inSex = dis.readChar();
            inAge = dis.readInt();
            inScore = dis.readDouble();
            System.out.println("姓名是" + inName + "性别是" +
                    inSex + "年龄是" + inAge + "成绩是" + inScore);
                    dis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
