package org.mnsg.resource.domain;

import com.alibaba.fastjson.JSONObject;
import org.mnsg.resource.App;
import org.mnsg.resource.utils.Request;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;

public class ResourceDecrypt {

    public static JSONObject _decrypt() {

        URL url = App.class.getClassLoader().getResource("resourceDecrypt.js");
        FileReader fr = null;
        try {
            fr = new FileReader(url.getPath());
            String data = getResource();
            return JSONObject.parseObject(runJs(fr, "decrypt", data));
        } catch (NullPointerException | FileNotFoundException e) {
            System.out.println("远程下载文件失败, 原因是 resourceDecrypt.js 文件无法读取到");
            return null;
        } catch (Exception e) {
            System.out.println("远程下载文件失败, 原因未知, 可能网络问题, 也可能资源解密密钥变更了");
            return null;
        }

    }
    private static String getResource() {
        String data = Request.get(Constant.RESOURCE_URL_JP, true);
        return data;
    }
    private static String runJs(FileReader fr,String func,String...parameter){
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");  //创建引擎实例
        Object result = "";
        try {
            engine.eval(fr); //编译
            if (engine instanceof Invocable) {
                result = ((Invocable) engine).invokeFunction(func, parameter); // 执行方法
                return String.valueOf(result);
            }
        } catch (Exception e) {
            return "表达式runtime错误:" + e.getMessage();
        }
        return "";
    }

}
