package org.mnsg.resource.utils;


import java.io.*;

public class WriteResourceFile {
    /**
     * 将二进制数据保存到本地文件中
     * @param input 远端服务器返回的二进制数据
     * @param fileName 保存到的本地名字
     * @return 保存完成返回true 否则 false
     */
    public static boolean Write(InputStream input, String fileName) {
//        File dest = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath() + "result" + fileName);
//        String folder = fileName.substring(fileName.lastIndexOf(".") + 1).equals("png") ? "png_jp" : "other";
//        File dest = new File( "src/main/result/" + folder + fileName);
        File dest = new File( "src/main/result" + fileName);
        if (!dest.exists()) {
            try {
                dest.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        OutputStream out;
        try {
            out = new FileOutputStream(dest);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        byte[] bytes = new byte[1024];
        int length = 0;
        try {
            while ((length = input.read(bytes)) != -1) {
                out.write(bytes, 0, length);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            out.close();
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
