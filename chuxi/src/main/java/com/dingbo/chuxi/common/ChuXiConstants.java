package com.dingbo.chuxi.common;

import lombok.Data;

@Data
public  class ChuXiConstants {
    public static final String TITLE = "除夕网络";
    public static final String FOOT = "© 2020 除夕网络by丁波";
    public static final String PRE_PATH =System.getProperty("os.name").toLowerCase().contains("linux")?"/file/chuxi/":"E:/file/chuxi/";

}
