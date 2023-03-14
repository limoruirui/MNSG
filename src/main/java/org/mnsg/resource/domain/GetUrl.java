package org.mnsg.resource.domain;

import com.alibaba.fastjson.JSONObject;
import org.mnsg.resource.utils.MD5Util;

public class GetUrl {
    private JSONObject fileInfo;
    private String filePath;
    //日服资源cdn
    private String url = "https://minasigo-no-shigoto-pd-c-res.orphans-order.com/" + Constant.RESOURCE_VERSION + "/";
    //台服资源cdn
//    private String url = "https://resource-tw.orphans-order.johren.games/" + Constant.RESOURCE_VERSION + "/";

    public GetUrl(JSONObject fileInfo, String filePath) {
        this.fileInfo = fileInfo;
        this.filePath = filePath;
    }

    public String getResult() {
        if (!fileType().equals("png")) {
            return null;
        }
        this.url += filePathMd5() + "/" + new _Cipher(this.filePath).doCipher() + "/" + getFileMd5() + "." + fileType();
        return this.url;
    }
    private String filePathMd5() {
        return MD5Util.md5(this.filePath);
    }
    private String getFileMd5 () {
            Object[] keys = fileInfo.keySet().toArray();
            String key = String.valueOf(keys[keys.length - 1]);
        return fileInfo.getJSONObject(key).getString("md5");
        }
    private String fileType() throws RuntimeException {
        return this.filePath.substring(this.filePath.lastIndexOf(".") + 1);
    }
}
