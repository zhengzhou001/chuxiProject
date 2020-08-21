package com.xinan.testservice.test.service;

import java.util.List;
import com.xinan.distributeCore.service.IBaseService;
import com.xinan.testservice.test.entity.TestDemoEntity;

/**
 * <ol>
 * date:2020-08-21 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>示例表Service接口</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface ITestDemoService extends IBaseService{
	/**
	 * 增加示例表记录
	 * @param TestDemoEntity 示例表实体对象
  	 */
	public int insertTestDemo(TestDemoEntity testDemoEntity) throws Exception;
	
	/**
	 * 删除示例表记录
	 * @param TestDemoEntity 示例表实体对象
  	 */
	public int deleteTestDemo(TestDemoEntity testDemoEntity) throws Exception;
	
	/**
	 * 修改示例表记录
	 * @param TestDemoEntity 示例表实体对象
  	 */
	public int updateTestDemo(TestDemoEntity testDemoEntity) throws Exception;
	/**
	 * 查询示例表记录
	 * @param TestDemoEntity 示例表实体对象
	 * @return List<TestDemoEntity>返回符合条件的示例表实体对象结果集
 	 */
	public	List<TestDemoEntity> selectTestDemo(TestDemoEntity testDemoEntity) throws Exception;
	
	/**
	 * 查询示例表记录数
	 * @param TestDemoEntity 示例表实体对象
	 * @return int返回符合条件的示例表实体对象个数
 	 */
	public	int selectTestDemoCount(TestDemoEntity testDemoEntity);
}