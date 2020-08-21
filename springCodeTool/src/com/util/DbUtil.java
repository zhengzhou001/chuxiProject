package com.util;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DbUtil {
	private Connection conn;
	Statement stmt;
	private Map mapProperties = new HashMap();

	DbUtil(Map map){
		this.mapProperties =map;
		this.ConnByJdbc();
	}
	 private static Pattern linePattern = Pattern.compile("_(\\w)");
     /**下划线转驼峰*/
     public static String lineToHump(String str){
         str = str.toLowerCase();
         Matcher matcher = linePattern.matcher(str);
         StringBuffer sb = new StringBuffer();
         while(matcher.find()){
             matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
         }
         matcher.appendTail(sb);
         return sb.toString();
     }
     public static void main(String[] args) {
		System.out.println(lineToHump("STUDENT_ABC"));
	}
	/**
	 * 获取表数据库相关信息
	 *
	 * @param tableName 表名
	 */
	public  void getTableAttr(String tableName) throws Exception {
		System.out.println("开始生成"+tableName+"相关代码、配置...");
		String driver =  MapUtils.getString(mapProperties, "driver", "");
		if (StringUtils.contains(driver,"mysql")){
			 getTableAttrMysql(tableName);
			return ;
		}


 		//表注释
		String sql = "select t.comments from user_tab_comments t where t.table_name='" + tableName + "' ";
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		String comm = "";
		try {
			rs.next();
			comm = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mapProperties.put("date", getCurDate());
		mapProperties.put("tabelName", tableName);
		mapProperties.put("humpName", lineToHump(tableName));//驼峰标示
		mapProperties.put("className", lineToHump(tableName).substring(0, 1).toUpperCase().concat(lineToHump(tableName).substring(1)));//驼峰标示 首字母大写
		mapProperties.put("tabelComments", comm != null ? comm : "");
		rs.close();
		
		sql = " select  a.COLUMN_NAME,a.DATA_LENGTH,a.DATA_PRECISION,a.DATA_SCALE,a.DATA_TYPE ,b.COMMENTS"+
	 " from all_tab_columns a ,user_col_comments b where a.table_name='" + tableName + "' "+
	 " and a.OWNER='"+MapUtils.getString(mapProperties, "user", "").toUpperCase()+"'"+
	 " and a.table_name=b.table_name"+
	 " and a.COLUMN_NAME=b.COLUMN_NAME ";
		rs = stmt.executeQuery(sql);
 		List colList = new ArrayList<>();
		while (rs.next()){
			Map tMap = new HashMap();
			tMap.put("COLUMN_NAME", rs.getString("COLUMN_NAME"));
			tMap.put("COLUMN_NAME_HUMP",lineToHump(rs.getString("COLUMN_NAME")) );//驼峰标示
			tMap.put("COLUMN_NAME_METHOD",lineToHump(rs.getString("COLUMN_NAME")).substring(0, 1).toUpperCase().concat(lineToHump(rs.getString("COLUMN_NAME")).substring(1)) );//首字母大写
			tMap.put("DATA_LENGTH", rs.getString("DATA_LENGTH"));
			tMap.put("DATA_PRECISION", rs.getString("DATA_PRECISION")==null?"":rs.getString("DATA_PRECISION"));
			tMap.put("DATA_SCALE", rs.getString("DATA_SCALE")==null?"":rs.getString("DATA_SCALE"));
			tMap.put("DATA_TYPE", rs.getString("DATA_TYPE"));
			tMap.put("COMMENTS", rs.getString("COMMENTS")==null?"":rs.getString("COMMENTS"));
			String type = String.format("{DATA_TYPE:%s,DATA_LENGTH:%s,DATA_PRECISION:%s,DATA_SCALE:%s}",
					MapUtils.getString(tMap,"DATA_TYPE",""),
					MapUtils.getString(tMap,"DATA_LENGTH",""),
					MapUtils.getString(tMap,"DATA_PRECISION",""),
					MapUtils.getString(tMap,"DATA_SCALE","")
			);
			tMap.put("COLUMN_TYPE",type);
			if (rs.getString("DATA_TYPE").equals("NUMBER")) {
				tMap.put("javaType", "String");
				tMap.put("sqlMapType", "NUMERIC");
 			}else if (rs.getString("DATA_TYPE").equals("DATE")) {
				tMap.put("javaType", "String");
				tMap.put("sqlMapType", "VARCHAR");
 			}else {
				tMap.put("javaType", "String");
				tMap.put("sqlMapType", "VARCHAR");
 			}
			colList.add(tMap);
		}
		//获取主键
		  sql = "select col.column_name from user_constraints con,  user_cons_columns col where con.constraint_name = col.constraint_name " +
				"and con.constraint_type='P' and col.table_name = '" + tableName + "'";
		  rs = stmt.executeQuery(sql);
		try {
			while (rs.next()) {

				for (int i = 0; i < colList.size(); i++) {
					Map tMap = (Map) colList.get(i);
					if (MapUtils.getString(tMap, "COLUMN_NAME").equals(rs.getString(1).toUpperCase())) {
						mapProperties.put("keyMap", tMap);
						break;
					}
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		rs.close();
		mapProperties.put("colList", colList);

	}

	private void getTableAttrMysql(String tableName)  throws  Exception{
		{
			System.out.println("开始生成"+tableName+"相关代码、配置...");
			//表注释
			String sql = "SHOW TABLE STATUS FROM `"+MapUtils.getString(mapProperties, "dbms_db")+"`";

			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);



			String comm = "";
			try {
				while (rs.next()) {
					if (rs.getString(1).toUpperCase().equals(tableName)) {
						comm = rs.getString(18) != null ? rs.getString(18) : "";
						comm=comm.substring(0,comm.indexOf(";"));
						break;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}



			mapProperties.put("date", getCurDate());
			mapProperties.put("tabelName", tableName);
			mapProperties.put("humpName", lineToHump(tableName));//驼峰标示
			mapProperties.put("className", lineToHump(tableName).substring(0, 1).toUpperCase().concat(lineToHump(tableName).substring(1)));//驼峰标示 首字母大写
			mapProperties.put("tabelComments", comm != null ? comm : "");


			mapProperties.put("tabelComments", comm != null ? comm : "");
			rs.close();

			sql = " SHOW FULL COLUMNS FROM  " +tableName;
			rs = stmt.executeQuery(sql);
			List colList = new ArrayList<>();
			while (rs.next()){
				Map tMap = new HashMap();
				tMap.put("COLUMN_NAME", rs.getString("Field").toUpperCase());
				tMap.put("COLUMN_NAME_HUMP",lineToHump(rs.getString("Field")) );//驼峰标示
				tMap.put("COLUMN_NAME_METHOD",lineToHump(rs.getString("Field")).substring(0, 1).toUpperCase().concat(lineToHump(rs.getString("Field")).substring(1)) );//首字母大写
				tMap.put("COMMENTS", rs.getString("Comment")==null?"":rs.getString("Comment"));


				String type = rs.getString("Type");
				tMap.put("COLUMN_TYPE", type);
				if(StringUtils.containsIgnoreCase(type, "int") ) {
					tMap.put("javaType", "Integer");
					tMap.put("sqlMapType", "NUMERIC");
					tMap.put("DATA_TYPE","NUMERIC");
				}else if(StringUtils.containsIgnoreCase(type, "float")
				||StringUtils.containsIgnoreCase(type, "double")){
					tMap.put("javaType", "Double");
					tMap.put("sqlMapType", "NUMERIC");
					tMap.put("DATA_TYPE","NUMERIC");
				}else if(StringUtils.containsIgnoreCase(type, "DATE")){
					tMap.put("javaType", "String");
					tMap.put("sqlMapType", "VARCHAR");
					tMap.put("DATA_TYPE","DATE");
				}else{
					tMap.put("javaType", "String");
					tMap.put("sqlMapType", "VARCHAR");
					tMap.put("DATA_TYPE","VARCHAR");
				}

				colList.add(tMap);
			}
			//获取主键
			sql = "SELECT column_name FROM INFORMATION_SCHEMA.`KEY_COLUMN_USAGE` WHERE table_name='" + tableName + "' AND constraint_name='PRIMARY'";
			rs = stmt.executeQuery(sql);
			try {
				while (rs.next()) {

					for (int i = 0; i < colList.size(); i++) {
						Map tMap = (Map) colList.get(i);
						if (MapUtils.getString(tMap, "COLUMN_NAME").equals(rs.getString(1).toUpperCase())) {
							mapProperties.put("keyMap", tMap);
							break;
						}
					}

				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			rs.close();
			mapProperties.put("colList", colList);

		}
	}


	//获取当前时间
	public static String getCurDate() {
		java.util.Calendar c = java.util.Calendar.getInstance();
		return (new java.text.SimpleDateFormat("yyyy-MM-dd")).format((java.util.Calendar.getInstance()).getTime());
	}
	/**
	 * 使用JDBC的URL方式连接数据库
	 */
	private void ConnByJdbc() {
		try {
			Class.forName(MapUtils.getString(mapProperties, "driver", ""));
			conn = DriverManager.getConnection(MapUtils.getString(mapProperties, "url", ""), MapUtils.getString(mapProperties, "user", ""), MapUtils.getString(mapProperties, "pass", ""));
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			closeDb();
		}
	}
	/**
	 * 关闭数据库连接
	 */
	public void closeDb() {

		try {
			if (!conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException err) {
			err.printStackTrace();
		}
	}
}
