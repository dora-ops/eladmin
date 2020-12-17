package me.zhengjie.utils;

import org.openqa.selenium.Cookie;

import java.util.Set;

public class CookieUtil {

    public static String getHttpCoockieStr(Set<Cookie> cookies){
        if (cookies==null){
            return "";
        }
        StringBuilder builder=new StringBuilder();
        for (Cookie cookie:cookies) {
            builder.append(cookie.getName()).append("=").append(cookie.getValue()).append("; ");
        }
       return  builder.substring(0, builder.lastIndexOf("; "));
    }

    public static Cookie getHttpCoockieStr(Set<Cookie> cookies,String key){
        if (cookies==null){
            return null;
        }
        for (Cookie cookie:cookies) {
            if (cookie.getName().equals(key)){
                return cookie;
            }
        }
        return null;
    }

}
