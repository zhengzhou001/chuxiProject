package com.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;


public class PropertyUtil {

	static  Map getMap( InputStream inputStream){
		Map retMap = new HashMap();
		try {
			 
			Properties prop = new Properties();
			prop.load(inputStream);     ///加载属性列表
			Iterator<String> it=prop.stringPropertyNames().iterator();
			while(it.hasNext()){
				String key=it.next();
				retMap.put(key, prop.getProperty(key));
			}
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}

}
