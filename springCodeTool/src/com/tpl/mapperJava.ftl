package ${basePackage}.mapper;

import java.util.List;

import ${basePackage}.entity.${className}Entity;

/**
 * <ol>
 * date:${date} editor:${editor}
 * <li>创建文档</li>
 * <li>${tabelComments}Mapper接口</li>
 * </ol>
 *
 * @author <a href="${author}">${editor}</a>
 * @version 1.0
 * @since 1.0
 */
public interface ${className}Mapper {
	/**
	 * 增加${tabelComments}记录
	 * @param ${className}Entity ${tabelComments}实体对象
  	 */
	public int insert${className}(${className}Entity ${humpName}Entity);
	
	/**
	 * 删除${tabelComments}记录
	 * @param ${className}Entity ${tabelComments}实体对象
  	 */
	public int delete${className}(${className}Entity ${humpName}Entity);
	
	/**
	 * 修改${tabelComments}记录
	 * @param ${className}Entity ${tabelComments}实体对象
  	 */
	public int update${className}(${className}Entity ${humpName}Entity);
	/**
	 * 查询${tabelComments}记录
	 * @param ${className}Entity ${tabelComments}实体对象
	 * @return List<${className}Entity>返回符合条件的${tabelComments}实体对象结果集
 	 */
	public	List<${className}Entity> select${className}(${className}Entity ${humpName}Entity);
	
	/**
	 * 查询${tabelComments}记录个数
	 * @param ${className}Entity ${tabelComments}实体对象
	 * @return int返回符合条件的${tabelComments}实体对象个数
 	 */
	public	int select${className}Count(${className}Entity ${humpName}Entity);
}