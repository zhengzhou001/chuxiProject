package com.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.collections.MapUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class Build {
	private Map configMap ;
	private Configuration cfg;

	Build(){
		//获取properties文件内容
		//this.configMap = PropertyUtil.getMap( Build.class.getResourceAsStream("/config.properties"));
		this.configMap = PropertyUtil.getMap( Build.class.getResourceAsStream("/config.properties"));

		configMap.put("BUILD_TAB_MC", MapUtils.getString(this.configMap, "BUILD_TAB_MC").toUpperCase());
		cfg = new Configuration();
		//        cfg.setDirectoryForTemplateLoading(new File("bin/freemarker"));
		cfg.setDefaultEncoding("UTF-8");//解决模板中文输出乱码问题
		cfg.setClassForTemplateLoading(this.getClass(), "/com/tpl");
	}

	//创建实体类
	void createEntity(){
		Map  mapParam = new HashMap();
		String filePath  = MapUtils.getString(this.configMap,"basePackage").replace('.','\\')+"\\entity";
		mapParam.put("FTL_NAME", "entity.ftl");
		mapParam.put("FILE_PATH", filePath);
		mapParam.put("FILE_NAME", MapUtils.getString(this.configMap,"className")+"Entity");
		mapParam.put("FILE_EXT_NAME", ".java");
		createByTemplate(mapParam);
	}
	//创建mapper
	void createMapper(){
		Map  mapParam = new HashMap();
		String[] filePaths  = MapUtils.getString(this.configMap,"basePackage").split("\\.");
		String filePath  =filePaths[filePaths.length-1];
		if ( MapUtils.getString(this.configMap,"driver").contains("mysql")){
			mapParam.put("FTL_NAME", "mapper_mysql.ftl");
		}else{
			mapParam.put("FTL_NAME", "mapper.ftl");
	}
		mapParam.put("FILE_PATH", filePath);
		this.configMap.put("namespace", filePath);
		mapParam.put("FILE_NAME", MapUtils.getString(this.configMap,"className")+"Mapper");
		mapParam.put("FILE_EXT_NAME", ".xml");
		createByTemplate(mapParam);
	}
	//创建mapperJava
	void createMapperJava(){
		Map  mapParam = new HashMap();
		String filePath  = MapUtils.getString(this.configMap,"basePackage").replace('.','\\')+"\\mapper";
		mapParam.put("FTL_NAME", "mapperJava.ftl");
		mapParam.put("FILE_PATH", filePath);
 		mapParam.put("FILE_NAME", MapUtils.getString(this.configMap,"className")+"Mapper");
		mapParam.put("FILE_EXT_NAME", ".java");
		createByTemplate(mapParam);
	}
	//创建Service
	void createService(){
		Map  mapParam = new HashMap();
		String filePath  = MapUtils.getString(this.configMap,"basePackage").replace('.','\\')+"\\service";
		mapParam.put("FTL_NAME", "service.ftl");
		mapParam.put("FILE_PATH", filePath);
 		mapParam.put("FILE_NAME", "I"+MapUtils.getString(this.configMap,"className")+"Service");
		mapParam.put("FILE_EXT_NAME", ".java");
		createByTemplate(mapParam);
		mapParam.clear();
		filePath  = MapUtils.getString(this.configMap,"basePackage").replace('.','\\')+"\\service\\impl";
		mapParam.put("FTL_NAME", "serviceImpl.ftl");
		mapParam.put("FILE_PATH", filePath);
 		mapParam.put("FILE_NAME", MapUtils.getString(this.configMap,"className")+"ServiceImpl");
		mapParam.put("FILE_EXT_NAME", ".java");
		createByTemplate(mapParam);
	}
	//创建Controller
	void createController(){
		Map  mapParam = new HashMap();
		String filePath  = MapUtils.getString(this.configMap,"basePackage").replace('.','\\')+"\\controller";
		mapParam.put("FTL_NAME", "controller.ftl");
		mapParam.put("FILE_PATH", filePath);
 		mapParam.put("FILE_NAME", MapUtils.getString(this.configMap,"className")+"Controller");
		mapParam.put("FILE_EXT_NAME", ".java");
		createByTemplate(mapParam);
	}
	//根据模板生成文件
	private boolean createByTemplate(Map mapParam) {
		try {
			Template sqlMapTemplate = cfg.getTemplate(MapUtils.getString(mapParam, "FTL_NAME"));
			System.out.println("使用模板文件->"+MapUtils.getString(mapParam, "FTL_NAME"));
			String strFilePath="";
			if (MapUtils.getString(mapParam, "FTL_NAME").equals("mapper.ftl")||
					MapUtils.getString(mapParam, "FTL_NAME").equals("mapper_mysql.ftl")) {
				strFilePath =MapUtils.getString(this.configMap, "code_dir")   + File.separator +"resources"+ File.separator +"mapper"+ File.separator+ MapUtils.getString(mapParam, "FILE_PATH");
			}else if (MapUtils.getString(mapParam, "FTL_NAME").startsWith("web")) {
				strFilePath =MapUtils.getString(this.configMap, "code_dir")   + File.separator +"webapp"+ File.separator + MapUtils.getString(mapParam, "FILE_PATH");
			}else {
				strFilePath = MapUtils.getString(this.configMap, "code_dir")   + File.separator +"java"+ File.separator + MapUtils.getString(mapParam, "FILE_PATH");
			}
			// strFilePath = DbTools.getPropertiesVal("code_dir") + File.separator + dbTools.getMapByKey(mapParam, "FILE_PATH");
			File filePath = new File(strFilePath);
			if (!filePath.exists()) {
				if (filePath.mkdirs())
					System.out.println("生成目录:" + strFilePath);
				else {
					System.out.println("不能够生成目录:" + strFilePath);
					return false;
				}
			}
			String fileName = strFilePath + File.separator
					+MapUtils.getString(mapParam, "FILE_NAME")
					+MapUtils.getString(mapParam, "FILE_EXT_NAME") ;
			OutputStreamWriter out = new OutputStreamWriter(
					new FileOutputStream(fileName), "UTF-8");

			sqlMapTemplate.process(this.configMap, out);
			out.flush();
			out.close();
			System.out.println("-->生成文件:" + fileName+"\n");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Build  build  = new Build();
		DbUtil dbUtil = new DbUtil(build.configMap);
		String[] BUILD_TAB_MC = MapUtils.getString(build.configMap,	"BUILD_TAB_MC").split(",");
		for (int i = 0; i < BUILD_TAB_MC.length; i++) {
			dbUtil.getTableAttr(BUILD_TAB_MC[i]);
			//System.out.println(build.configMap);
			build.createEntity();
			build.createMapper();
			build.createMapperJava();
			build.createService();
			build.createController();
		}
		System.out.println("生成完毕");
		
	}

}
