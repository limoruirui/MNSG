package org.mnsg.resource.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.Objects;

public class ReadJsonUtil {
    public static JSONObject readJson(String fileName) {
        StringBuffer sb = new StringBuffer();
        try {
            File file = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(fileName)).getFile());
            FileReader fileReader = new FileReader(file);
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "utf-8");
            int ch = 0;
            while ((ch = inputStreamReader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            inputStreamReader.close();

        } catch (
                IOException ex) {
            throw new RuntimeException(ex);
        }
        return JSONObject.parseObject(sb.toString());
    }

}
