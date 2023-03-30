package org.mnsg.resource.domain;

import com.alibaba.fastjson.JSONObject;
import org.mnsg.resource.utils.Request;

public class Constant {
    // 资源文件版本
    public static String RESOURCE_VERSION;
    public final static String VERSION_URL_JP = "https://minasigo-no-shigoto-web-r-server.orphans-order.com/mnsg/user/getVersion";
    public final static String VERSION_URL_TW = "https://web-server-tw.orphans-order.johren.games/mnsg/user/getVersion";
    public final static String RESOURCE_URL_JP = "https://minasigo-no-shigoto-pd-c-res.orphans-order.com/" + getVersion(true) + "/resource.json?" + System.currentTimeMillis();
    public final static String RESOURCE_URL_TW = "https://resource-tw.orphans-order.johren.games/" + getVersion(false) + "/resource.json?" + System.currentTimeMillis();
    public static String getVersion(boolean isJP) {
        String versionUrl = isJP ? VERSION_URL_JP : VERSION_URL_TW;
        JSONObject jsonObject = JSONObject.parseObject(Request.get(versionUrl, true));
//        System.out.println(jsonObject);
        return jsonObject.getJSONObject("version").getString("resourceVersion");
    }
}
