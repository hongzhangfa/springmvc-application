package com.hzfmvc.forthexam.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecUtils {

    public static String getCurrentUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return userDetails.getUsername();
    }

    public static String getCurrentAuthorities() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return userDetails.getAuthorities().toString();
    }


    public static String getAuthority(String str, String delimeter) {
       // String str1 = "www.runoob.com";
        String[] temp;
        String x = " ";
       // String delimeter1 = "\\.";  // 指定分割字符， . 号需要转义
        temp = str.split(delimeter); // 分割字符串
        for(int i =0; i < temp.length ; i++){
            System.out.println(temp[i]);
            x += temp[i];
        }
        return x;
    }

}
