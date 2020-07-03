package ${basePackage}.controller;

import com.xinan.distributeCore.result.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ${basePackage}.service.I${className}Service;
import ${basePackage}.entity.${className}Entity;

/**
 * <ol>
 * date:${date} editor:${editor}
 * <li>创建文档</li>
 * <li>${tabelComments}Controller</li>
 * </ol>
 *
 * @author <a href="${author}">${editor}</a>
 * @version 1.0
 * @since 1.0
 */
@RestController
@Api(value = "${tabelComments}")
@RequestMapping(value="/${namespace}")
@Slf4j
public class ${className}Controller{
 	@Autowired
	private I${className}Service ${humpName}Service;
	
	/**
	 * 增加${tabelComments}记录
	 * @param ${className}Entity ${tabelComments}实体对象
  	 */
  	@ApiOperation(value = "增加${tabelComments}记录", notes="根据${humpName}实体对象增加${tabelComments}")
	@RequestMapping(value={"/insert${className}"}, method={RequestMethod.POST})
	public BaseResult<Integer> insert${className}(@RequestBody ${className}Entity ${humpName}Entity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(${humpName}Service.insert${className}(${humpName}Entity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}
	
	/**
	 * 删除${tabelComments}记录
	 * @param ${className}Entity ${tabelComments}实体对象
  	 */
  	@ApiOperation(value = "删除${tabelComments}记录", notes="根据${humpName}实体对象删除${tabelComments}")
	@RequestMapping(value={"/delete${className}"}, method={RequestMethod.POST})
	public BaseResult<Integer> delete${className}(@RequestBody ${className}Entity ${humpName}Entity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(${humpName}Service.delete${className}(${humpName}Entity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
    		log.error(baseResult.msg);
		}
    	return baseResult;
	}
	
	/**
	 * 修改${tabelComments}记录
	 * @param ${className}Entity ${tabelComments}实体对象
  	 */
  	@ApiOperation(value = "修改${tabelComments}记录", notes="根据${humpName}实体对象修改${tabelComments}")
	@RequestMapping(value={"/update${className}"}, method={RequestMethod.POST})
	public BaseResult<Integer> update${className}(@RequestBody ${className}Entity ${humpName}Entity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(${humpName}Service.update${className}(${humpName}Entity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
    		log.error(baseResult.msg);
		}
    	return baseResult;
	}
	/**
	 * 查询${tabelComments}记录
	 * @param ${className}Entity ${tabelComments}实体对象
	 * @return List<${className}Entity>返回符合条件的${tabelComments}实体对象结果集
 	 */
 	@ApiOperation(value = "查询${tabelComments}记录", notes="根据${humpName}实体对象查询${tabelComments}")
	@RequestMapping(value={"/select${className}"}, method={RequestMethod.POST})
	public	BaseResult<List<${className}Entity>> select${className}(@RequestBody ${className}Entity ${humpName}Entity){
        BaseResult<List<${className}Entity>> baseResult = new BaseResult<List<${className}Entity>>();
        try{
            baseResult.setData(${humpName}Service.select${className}(${humpName}Entity));
 		} catch (Exception e) {
            baseResult.code=-1;
            baseResult.msg=e.getMessage();
            log.error(baseResult.msg);
		}
		return baseResult;
	}
	
	/**
	 * 查询${tabelComments}记录数
	 * @param ${className}Entity ${tabelComments}实体对象
	 * @return int返回符合条件的${tabelComments}实体对象个数
 	 */
 	@ApiOperation(value = "查询${tabelComments}记录个数", notes="根据${humpName}实体对象查询${tabelComments}记录个数")
	@RequestMapping(value={"/select${className}Count"}, method={RequestMethod.POST})
	public	BaseResult<Integer> select${className}Count(@RequestBody ${className}Entity ${humpName}Entity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
			baseResult.setData(${humpName}Service.select${className}Count(${humpName}Entity));
 		} catch (Exception e) {
			baseResult.code=-1;
			baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}
}