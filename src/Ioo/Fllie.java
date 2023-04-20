package Ioo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Fllie {
    public static void main(String[] args) {
		try{
            byte[] data=new byte[1024];            
            File srcFile=new File("src/Ioo/FileReader.txt");
            File desFile=new File("src/Ioo/Filewriter.txt");            
            BufferedInputStream bufIS =new BufferedInputStream(new FileInputStream(srcFile));
            BufferedOutputStream bufOS =new BufferedOutputStream(new FileOutputStream(desFile)); 
            System.out.println("复制文件: "+srcFile.length()+"字节");            
            while(bufIS .read(data)!=-1){bufOS.write(data);}            
            bufOS.flush(); //将缓冲区中的数据全部写出     
            bufIS .close();
            bufOS.close();             
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("using: java BufferedStreamDemo src des");
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
	}
}
