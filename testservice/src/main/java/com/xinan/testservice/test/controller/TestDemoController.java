package com.xinan.testservice.test.controller;

import com.xinan.distributeCore.entity.PageEntity;
import com.xinan.distributeCore.result.BaseResult;
import com.xinan.testservice.test.entity.TestDemoEntity;
import com.xinan.testservice.test.service.ITestDemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <ol>
 * date:2020-08-21 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>示例表Controller</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@RestController
@Api(value = "示例表")
@RequestMapping(value="/test")
@Slf4j
public class TestDemoController{
 	@Autowired
	private ITestDemoService testDemoService;
	
	/**
	 * 增加示例表记录
	 * @param TestDemoEntity 示例表实体对象
  	 */
  	@ApiOperation(value = "增加示例表记录", notes="根据testDemo实体对象增加示例表",hidden = true)
	@RequestMapping(value={"/insertTestDemo"}, method={RequestMethod.POST})
	public BaseResult<Integer> insertTestDemo(@RequestBody TestDemoEntity testDemoEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(testDemoService.insertTestDemo(testDemoEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}
	
	/**
	 * 删除示例表记录
	 * @param TestDemoEntity 示例表实体对象
  	 */
  	@ApiOperation(value = "删除示例表记录", notes="根据testDemo实体对象删除示例表",hidden = true)
	@RequestMapping(value={"/deleteTestDemo"}, method={RequestMethod.POST})
	public BaseResult<Integer> deleteTestDemo(@RequestBody TestDemoEntity testDemoEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(testDemoService.deleteTestDemo(testDemoEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
    		log.error(baseResult.msg);
		}
    	return baseResult;
	}
	
	/**
	 * 修改示例表记录
	 * @param TestDemoEntity 示例表实体对象
  	 */
  	@ApiOperation(value = "修改示例表记录", notes="根据testDemo实体对象修改示例表",hidden = true)
	@RequestMapping(value={"/updateTestDemo"}, method={RequestMethod.POST})
	public BaseResult<Integer> updateTestDemo(@RequestBody TestDemoEntity testDemoEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(testDemoService.updateTestDemo(testDemoEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
    		log.error(baseResult.msg);
		}
    	return baseResult;
	}
	/**
	 * 查询示例表记录
	 * @param TestDemoEntity 示例表实体对象
	 * @return List<TestDemoEntity>返回符合条件的示例表分页对象
 	 */
 	@ApiOperation(value = "查询示例表记录", notes="根据testDemo实体对象查询示例表")
	@RequestMapping(value={"/selectTestDemo"}, method={RequestMethod.POST})
	public	BaseResult<PageEntity<TestDemoEntity>> selectTestDemo(@RequestBody TestDemoEntity testDemoEntity){
        BaseResult<PageEntity<TestDemoEntity>> baseResult = new BaseResult<PageEntity<TestDemoEntity>>();
        try{
			PageEntity<TestDemoEntity> pageEntity = new PageEntity<TestDemoEntity>();
			List<TestDemoEntity> rows = testDemoService.selectTestDemo(testDemoEntity);
			pageEntity.setRows(rows);
			if(rows != null && rows.size() > 0){
				TestDemoEntity entity = rows.get(0);
				pageEntity.setTotal(entity.getTotal());
			}else{
				pageEntity.setTotal(0);
			}
            baseResult.setData(pageEntity);
 		} catch (Exception e) {
            baseResult.code=-1;
            baseResult.msg=e.getMessage();
            log.error(baseResult.msg);
		}
		return baseResult;
	}
	
	/**
	 * 查询示例表记录数
	 * @param TestDemoEntity 示例表实体对象
	 * @return int返回符合条件的示例表实体对象个数
 	 */
 	@ApiOperation(value = "查询示例表记录个数", notes="根据testDemo实体对象查询示例表记录个数",hidden = true)
	@RequestMapping(value={"/selectTestDemoCount"}, method={RequestMethod.POST})
	public	BaseResult<Integer> selectTestDemoCount(@RequestBody TestDemoEntity testDemoEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
			baseResult.setData(testDemoService.selectTestDemoCount(testDemoEntity));
 		} catch (Exception e) {
			baseResult.code=-1;
			baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}
}