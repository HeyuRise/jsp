package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Heyu {

    private static boolean a;

    public static void main(String[] args) throws IOException {



        // String a = new String(fileToByte("D:/a.txt"));
        System.out.println(System.currentTimeMillis());
    }

    public static byte[] fileToByte(String filePath) throws IOException {
        byte[] bytes = null;
        FileInputStream fis = null;
        try{
            File file = new File(filePath);
            fis = new FileInputStream(file);
            bytes = new byte[(int) file.length()];
            fis.read(bytes);
        }catch(IOException e){
            e.printStackTrace();
            throw e;
        }finally{
            fis.close();
        }
        return bytes;
    }

}
