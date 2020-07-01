package ${basePackage}.service.impl;

import org.apache.commons.lang3.StringUtils;
import com.xinan.distributeCore.tools.BaseTools;
import com.xinan.distributeCore.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import ${basePackage}.service.I${className}Service;
import ${basePackage}.mapper.${className}Mapper;
import ${basePackage}.entity.${className}Entity;

/**
 * <ol>
 * date:${date} editor:${editor}
 * <li>创建文档</li>
 * <li>${tabelComments}Service实现类</li>
 * </ol>
 *
 * @author <a href="${author}">${editor}</a>
 * @version 1.0
 * @since 1.0
 */
@Service
@Slf4j
public class ${className}ServiceImpl extends BaseServiceImpl implements I${className}Service {
	@Autowired
	private ${className}Mapper ${humpName}Mapper;
	/**
	 * 增加${tabelComments}记录
	 * @param ${className}Entity ${tabelComments}实体对象
  	 */
	public int insert${className}(${className}Entity ${humpName}Entity) throws Exception{
		return ${humpName}Mapper.insert${className}(${humpName}Entity);
	}
	
	/**
	 * 删除${tabelComments}记录
	 * @param ${className}Entity ${tabelComments}实体对象
  	 */
	public int delete${className}(${className}Entity ${humpName}Entity) throws Exception{
    	return ${humpName}Mapper.delete${className}(${humpName}Entity);
	}
	
	/**
	 * 修改${tabelComments}记录
	 * @param ${className}Entity ${tabelComments}实体对象
  	 */
	public int update${className}(${className}Entity ${humpName}Entity) throws Exception{
    	return ${humpName}Mapper.update${className}(${humpName}Entity);
	}
	/**
	 * 查询${tabelComments}记录
	 * @param ${className}Entity ${tabelComments}实体对象
	 * @return List<${className}Entity>返回符合条件的${tabelComments}实体对象结果集
 	 */
	public	List<${className}Entity> select${className}(${className}Entity ${humpName}Entity) throws Exception{
		return ${humpName}Mapper.select${className}(${humpName}Entity);
	}
	
	/**
	 * 查询${tabelComments}记录数
	 * @param ${className}Entity ${tabelComments}实体对象
	 * @return int返回符合条件的${tabelComments}实体对象个数
 	 */
	public	int select${className}Count(${className}Entity ${humpName}Entity){
		return ${humpName}Mapper.select${className}Count(${humpName}Entity);
	}
}