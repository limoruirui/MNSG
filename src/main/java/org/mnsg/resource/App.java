package org.mnsg.resource;

import com.alibaba.fastjson.JSONObject;
import org.mnsg.resource.domain.Constant;
import org.mnsg.resource.domain.GetUrl;
import org.mnsg.resource.domain.ResourceDecrypt;
import org.mnsg.resource.utils.ReadJsonUtil;
import org.mnsg.resource.utils.Request;
import org.mnsg.resource.utils.WriteResourceFile;

import java.io.InputStream;

public class App {
    public static void main(String[] args) {
        // 下载资源文件, 并aes解密成明文, 若无法通过远程下载, 则读取本地文件
        JSONObject jsonObject = ResourceDecrypt._decrypt();
        if (jsonObject == null) {
            //读取整个资源文件
            System.out.println("远程下载资源链接失败, 通过读取本地资源文件进行下载(本地资源文件可能并非最新)");
            jsonObject = ReadJsonUtil.readJson("resourceJP.json");
        }
        Constant.RESOURCE_VERSION = jsonObject.getString("version");
        JSONObject resourceJsonObject = jsonObject.getJSONObject("assets");
        //遍历通过遍历json所有的key 获取json的value 并对value进行处理获取资源文件的真实链接 并下载保存到 src/main/result 文件夹中
        System.out.println("默认只保存png格式文件");
        for (Object o : resourceJsonObject.keySet().toArray()) {
            //遍历获取key
            String filePath = String.valueOf(o);
            if (!filePath.contains("/stand")) {
                continue;
            }
            try {
                // 通过key 获取 value
                JSONObject fileInfo = resourceJsonObject.getJSONObject(filePath);
                //处理value 得到真实下载链接
                String url = new GetUrl(fileInfo, filePath).getResult();
                //若资源文件后缀不为png 则不返回链接,而是跳过此文件
                if (url == null) {
//                    System.out.println("当前文件格式不为png, 跳过 若需要下载非png格式资源 自行修改");
                    continue;
                }
                //从远端服务器下载资源文件
                InputStream inputStream = Request.get(url);
                //写入本地文件
                String fileName = filePath.substring(filePath.lastIndexOf("/"));
                if (WriteResourceFile.Write(inputStream, fileName)) {
                    System.out.println("保存图片---" + fileName + "成功");
                }
            } catch (Exception e) {
                System.out.println(filePath);
            }

        }


    }
}
