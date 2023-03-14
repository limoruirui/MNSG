package org.mnsg.resource.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class Request {
    private static final Logger LOGGER = LoggerFactory.getLogger(org.mnsg.resource.utils.Request.class);

    /**
     * 获取Cookie对象
     */
    private Request() {
    }

    ;

    /**
     * 发送get请求
     *
     * @param url 请求的地址，包括参数
     * @return String
     */
    public static InputStream get(String url) {
        RequestConfig defaultConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).setSocketTimeout(5000).build();
        HttpClient client = HttpClients.custom().setDefaultRequestConfig(defaultConfig).build();

        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("connection", "keep-alive");
        httpGet.addHeader("charset", "UTF-8");
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36");
        HttpResponse resp = null;
        String respContent = null;
        InputStream content = null;
        try {
            resp = client.execute(httpGet);
            HttpEntity entity = null;
            if (resp.getStatusLine().getStatusCode() < 400) {
                entity = resp.getEntity();
            } else {
                entity = resp.getEntity();
            }
            content = entity.getContent();
//            respContent = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            LOGGER.info("get请求错误 -- " + e);
        } finally {
            return content;
//            return JSONObject.parseObject(respContent);
        }
    }


}
