package com.xinan.testservice.test.service.impl;

import org.apache.commons.lang3.StringUtils;
import com.xinan.distributeCore.tools.BaseTools;
import com.xinan.distributeCore.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import com.xinan.testservice.test.service.ITestDemoService;
import com.xinan.testservice.test.mapper.TestDemoMapper;
import com.xinan.testservice.test.entity.TestDemoEntity;

/**
 * <ol>
 * date:2020-08-21 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>示例表Service实现类</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@Service
@Slf4j
public class TestDemoServiceImpl extends BaseServiceImpl implements ITestDemoService {
	@Autowired
	private TestDemoMapper testDemoMapper;
	/**
	 * 增加示例表记录
	 * @param TestDemoEntity 示例表实体对象
  	 */
	public int insertTestDemo(TestDemoEntity testDemoEntity) throws Exception{
		return testDemoMapper.insertTestDemo(testDemoEntity);
	}
	
	/**
	 * 删除示例表记录
	 * @param TestDemoEntity 示例表实体对象
  	 */
	public int deleteTestDemo(TestDemoEntity testDemoEntity) throws Exception{
    	return testDemoMapper.deleteTestDemo(testDemoEntity);
	}
	
	/**
	 * 修改示例表记录
	 * @param TestDemoEntity 示例表实体对象
  	 */
	public int updateTestDemo(TestDemoEntity testDemoEntity) throws Exception{
    	return testDemoMapper.updateTestDemo(testDemoEntity);
	}
	/**
	 * 查询示例表记录
	 * @param TestDemoEntity 示例表实体对象
	 * @return List<TestDemoEntity>返回符合条件的示例表实体对象结果集
 	 */
	public	List<TestDemoEntity> selectTestDemo(TestDemoEntity testDemoEntity) throws Exception{
		return testDemoMapper.selectTestDemo(testDemoEntity);
	}
	
	/**
	 * 查询示例表记录数
	 * @param TestDemoEntity 示例表实体对象
	 * @return int返回符合条件的示例表实体对象个数
 	 */
	public	int selectTestDemoCount(TestDemoEntity testDemoEntity){
		return testDemoMapper.selectTestDemoCount(testDemoEntity);
	}
}