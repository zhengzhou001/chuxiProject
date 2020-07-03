package com.xinan.distributeCore.tools;


import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 */
public class BaseTools {
	private static int seq = 0;
	private static final int MAX_PER_SECOND = 1000;

	/**
	 * 生成15位的数字流水号(16位以后会出现科学计数影响业务操作)
	 * <p>
	 * <I>生成规则为:</I><b>yyMMddHHmmss+1位顺序号</b>
	 * </p>
	 *
	 * @return 15位流水号
	 */
	public static synchronized String getNextSeq() {
		seq++;
		return (new SimpleDateFormat("yyMMddHHmmss").format(new Date()))
				+ String.format("%03d", seq %= MAX_PER_SECOND);
	}
	//获取IP
	public static String getIPAddress() {
		HttpServletRequest request=null;
		try {
			RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
			 request = ((ServletRequestAttributes) requestAttributes).getRequest();
		}catch (Exception e){
			return "";
		}
		return getIPAddress(request);
	}

	//获取IP
	public static String getIPAddress(HttpServletRequest request) {
		String ip = null;

		//X-Forwarded-For：Squid 服务代理
		String ipAddresses = request.getHeader("X-Forwarded-For");

		if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			//Proxy-Client-IP：apache 服务代理
			ipAddresses = request.getHeader("Proxy-Client-IP");
		}

		if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			//WL-Proxy-Client-IP：weblogic 服务代理
			ipAddresses = request.getHeader("WL-Proxy-Client-IP");
		}

		if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			//HTTP_CLIENT_IP：有些代理服务器
			ipAddresses = request.getHeader("HTTP_CLIENT_IP");
		}

		if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			//X-Real-IP：nginx服务代理
			ipAddresses = request.getHeader("X-Real-IP");
		}

		//有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
		if (ipAddresses != null && ipAddresses.length() != 0) {
			ip = ipAddresses.split(",")[0];
		}

		//还是不能获取到，最后再通过request.getRemoteAddr();获取
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}


	/**
	 * 获取count个随机数子
	 * @param count 随机数个数
	 * @return count随机数字
	 */
	public static  String getRandomNumber(int count){
		StringBuffer sb = new StringBuffer();
		String str = "0123456789";
		Random r = new Random();
		for(int i=0;i<count;i++){
			int num = r.nextInt(str.length());
			sb.append(str.charAt(num));
			str = str.replace((str.charAt(num)+""), "");
		}
		return sb.toString();
	}
	/**
	 * 将时间字符串转换为指定格式字符串
	 * @param sourceDateStr 2018-01-01 12:00:00
	 * @param sourceDateFormat yyyy-MM-dd HH:mm:ss
	 * @param targetDateFormat yyyyMMdd
	 * @return 20180101
	 */
	public static  String getFormatDateStr(String sourceDateStr,String sourceDateFormat,String targetDateFormat){
		try {
			SimpleDateFormat sourceSimpleDateFormat = new SimpleDateFormat(sourceDateFormat);
			SimpleDateFormat targetSimpleDateFormat = new SimpleDateFormat(targetDateFormat);
			Date date = sourceSimpleDateFormat.parse(sourceDateStr);
			return targetSimpleDateFormat.format(date);
		} catch (ParseException e) {
			return "";
		}
	}

	/**
	 * 获取时间间隔
	 * @param beginDateStr 2018-01-01 12:00:00
	 * @param endDateStr 2018-01-01 12:00:01
	 * @return 1
	 */
	public static  long getBetweenTime(String beginDateStr,String endDateStr){
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date beginDate = simpleDateFormat.parse(beginDateStr);
			Date endDate = simpleDateFormat.parse(endDateStr);
			return endDate.getTime()-beginDate.getTime();
		} catch (ParseException e) {
			return -123;
		}
	}

	/**
	 * 时间字符串转时间对象
	 * @param sourceDateStr
	 * @param sourceDateFormat
	 * @return Date
	 */
	public static Date getDateByStr(String sourceDateStr,String sourceDateFormat){
		try {
			SimpleDateFormat sourceSimpleDateFormat = new SimpleDateFormat(sourceDateFormat);
			Date date = sourceSimpleDateFormat.parse(sourceDateStr);
			return date;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得当前日期
	 *
	 * @param type 格式类型 0:yyyy-MM-dd 1:yyyy-MM-dd HH:mm:ss 2:yyyyMMdd 3:HHmmss 4:yyyyMMddHHmmss
	 * @return String 返回当前年月日
	 */
	public static String getCurStrDate(int type) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		switch (type) {
			case 0:
				pattern = "yyyy-MM-dd";
				break;
			case 1:
				pattern = "yyyy-MM-dd HH:mm:ss";
				break;
			case 2:
				pattern = "yyyyMMdd";
				break;
			case 3:
				pattern = "HHmmss";
				break;
			case 4:
				pattern = "yyyyMMddHHmmss";
				break;
			case 5:
				pattern = "yyyyMM";
				break;
		}
		return (new SimpleDateFormat(pattern)).format(new Date());
	}

	//获取cookie
	public static String getCookie(HttpServletRequest request, String name) {
		if (request==null)
			return "";
		if (StringUtils.isEmpty(name))
			return "";
		Cookie[] cookies = request.getCookies();
		if (cookies==null)
			return "";
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie1 = cookies[i];
			if (cookie1.getName().equals(name)) {
				return cookie1.getValue();
			}
		}
		return "";
	}
	public static void main(String[] args) {
		System.out.println((int) getBetweenTime("2020-04-15 17:11:46","2020-04-15 17:11:46"));
	}
}
