package org.mnsg.resource.domain;

import org.mnsg.resource.utils.MD5Util;

import java.util.ArrayList;

public class _Cipher {
    private String filePath;

    public _Cipher(String filePath) {
        this.filePath = filePath;
    }

    public String doCipher() {
        String path = this.filePath.substring(0, this.filePath.lastIndexOf("."));
        String pathMd5 = MD5Util.md5(path);
//        System.out.println(pathMd5);
        char c = pathMd5.charAt(0);
//        System.out.println(c);
        String result = "hello";
        switch (c) {
            case '0':
            case '1':
            case '2':
            case '3':
                result = String.join("/", first(pathMd5));
                break;
            case '4':
            case '5':
            case '6':
            case '7':
                result = String.join("/", second(pathMd5));
                break;
            case '8':
            case '9':
            case 'a':
            case 'b':
                result = String.join("/", third(pathMd5));
                break;
            case 'c':
            case 'd':
            case 'e':
            case 'f': ;
                result = String.join("/", fourth(pathMd5));
                break;

        }
        return result;
    }

    public ArrayList<String> first(String pathMD5) {
        return new ArrayList<String>() {
            {
                this.add(pathMD5.substring(0, 2));
                this.add(pathMD5.substring(4, 6));
            }
        };
    }
    public ArrayList<String> second(String pathMD5) {
        return new ArrayList<String>() {
            {
                this.add(pathMD5.substring(2, 4));
                this.add(pathMD5.substring(6, 8));
                this.add(pathMD5.substring(0, 2));
            }
        };
    }
    public ArrayList<String> third(String pathMD5) {
        return new ArrayList<String>() {
            {
                this.add(pathMD5.substring(4, 6));
                this.add(pathMD5.substring(0, 2));
                this.add(pathMD5.substring(6, 8));
                this.add(pathMD5.substring(2, 4));
            }
        };
    }
    public ArrayList<String> fourth(String pathMD5) {
        return new ArrayList<String>() {
            {
                this.add(pathMD5.substring(6, 8));
                this.add(pathMD5.substring(2, 4));
                this.add(pathMD5.substring(4, 6));
                this.add(pathMD5.substring(0, 2));
            }
        };
    }
}
