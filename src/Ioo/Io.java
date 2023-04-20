package Ioo;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Io {
    public static void main(String[] args) throws IOException {
        File inFile = new File("src/Ioo/hhh.txt");
        File outFile = new File("src/Ioo/out.txt");

        FileReader fr = new FileReader(inFile);
        FileWriter fw = new FileWriter(outFile);

        int c;
        while ((c=fr.read())!=-1) {
            fw.write(c);
        }
        fr.close();
        fw.close();
    }
}
