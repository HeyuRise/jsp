package com.heyu.jsp.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 * @author 孙贺宇
 * @date 2013-2-22
 */
public class FileUtil {
	public String dir = "";
	public String temp = "";

	/**
	 * 从硬盘文件中读取数据
	 * @param filename 文件名(包含路径)
	 * @return 文件内容(二进制)
	 */
	public static byte[] readFile(String filename) {
    	File file = new File(filename);

        if(!file.exists()||file.isDirectory()) {
            return null;
        }

        FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			return null;
		}
		
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		
		try {
			do {
				byte[] data = new byte[1024];
				int size = fis.read(data);
				if (-1 == size) {
					break;
				}
				bout.write(data, 0, size); 		
			} while (true);
		} catch (IOException e) {
			//e.printStackTrace();
			bout = null;
		}
		
		try {
			fis.close();
		} catch (IOException e) {
//			e.printStackTrace();
		}
		
		if (bout == null) {
			return null;			
		}
		return bout.toByteArray();
	}

	public static String readFileString(String filename) {
		return readFileString(filename, "UTF-8");
	}
	public static String readFileString(String filename, String charset) {
		byte[] data = FileUtil.readFile(filename);
		String dataStr = null;
		try {
			dataStr = new String(data, charset);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataStr;
	}
	/**
	 * 写入硬盘文件信息
	 * @param filename 文件名(包含路径)
	 * @param data 文件的二进制数据
	 * @return
	 */
	public static int writeFile(String filename, byte[] data) {		
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(filename);
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			return 1;
		}

		int writeBytes = data.length;
        PrintStream p = new PrintStream(out);
        try {
			p.write(data);
		} catch (IOException e) {
			//e.printStackTrace();
			writeBytes = 0;
		}
        
        try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return writeBytes;
	}
	/**
	 * 搜索目录下的所有文件(包括子目录)
	 * @param dir 目录路径
	 * @return 文件名列表
	 */
	public String[] serachFiles(String dir) {
        File root = new File(dir);

        File[] filesOrDirs = root.listFiles();

        String[] result = new String[10];

        for (int i = 0; i < filesOrDirs.length; i++) {
            if (filesOrDirs[i].isDirectory()) {
                serachFiles(filesOrDirs[i].getAbsolutePath());
            } else {
            	try {
            		result[i] = filesOrDirs[i].getName();
            	} catch (Exception e) {
        			continue;
        		}
                temp += filesOrDirs[i].getName() + ",";
            }
        }
        
        return temp.split(",");
    }

	public static boolean exist(String filename) {
		File targetFile = new File(filename); 
		return targetFile.exists();
	}
	public static boolean delete(String filename) {
		File targetFile = new File(filename); 
		return targetFile.delete();	
	}
	
	public static void main(String[] args) {
//		byte[] data = GFile.readFile("d:/test.jpg");
//		GFile.writeFile("d:/test_out.jpg", data);
		
		byte[] data = FileUtil.readFile("d:/new.txt");
		String dataStr = new String(data);
		System.out.println(dataStr);
		
//		FileUtil file = new FileUtil();
//        String[] files = file.serachFiles("e:/script");//("F:/movies");
//        for (int i = 0; i < files.length; i++) {
//            System.out.println("files[" + i + "]:" + files[i]);
//        }
	}
}
