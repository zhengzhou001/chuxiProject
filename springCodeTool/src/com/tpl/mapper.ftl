<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${basePackage}.mapper.${className}Mapper" >
	<resultMap id="BaseResultMap" type="${basePackage}.entity.${className}Entity" >
		<result column="TOTAL" property="total"  />
		<result column="ROWNO" property="rowno"  />
		<#list colList  as col>
		<result column="${col.COLUMN_NAME}" property="${col.COLUMN_NAME_HUMP}" jdbcType="${col.sqlMapType}" />
		</#list>
	</resultMap>
	<!--${tabelComments}通用查询条件 -->
	<sql id="${tabelName}_WHERE">
		<where>
			<#list colList  as col>
			<#assign DATA_TYPE='${col.DATA_TYPE}'>
			<#if DATA_TYPE=="DATE">
			<if test="${col.COLUMN_NAME_HUMP} != null and ${col.COLUMN_NAME_HUMP} != ''">
				AND T.${col.COLUMN_NAME} = TO_DATE(${'#'}{${col.COLUMN_NAME_HUMP},'YYYY-MM-DD hh24:mi:ss')
			</if>
			<if test="${col.COLUMN_NAME_HUMP}_MIN != null and ${col.COLUMN_NAME_HUMP}_MIN != ''">
				AND T.${col.COLUMN_NAME} &gt;= TO_DATE(${'#'}{${col.COLUMN_NAME_HUMP}_MIN},'YYYY-MM-DD hh24:mi:ss')
			</if>
			<if test="${col.COLUMN_NAME_HUMP}_MAX != null and ${col.COLUMN_NAME_HUMP}_MAX != ''">
				AND T.${col.COLUMN_NAME} &lt;= TO_DATE(${'#'}{${col.COLUMN_NAME_HUMP}_MAX},'YYYY-MM-DD hh24:mi:ss')
			</if>
			<#else>
			<if test="${col.COLUMN_NAME_HUMP} != null and ${col.COLUMN_NAME_HUMP} != ''">
				AND T.${col.COLUMN_NAME} = ${'#'}{${col.COLUMN_NAME_HUMP}}
			</if>
			<if test="${col.COLUMN_NAME_HUMP}_LIKE != null and ${col.COLUMN_NAME_HUMP}_LIKE != ''">
				AND T.${col.COLUMN_NAME} LIKE concat(concat('%',${'#'}{${col.COLUMN_NAME_HUMP}_LIKE}),'%')
			</if>
			<if test="${col.COLUMN_NAME_HUMP}_LLIKE != null and ${col.COLUMN_NAME_HUMP}_LLIKE != ''">
				AND T.${col.COLUMN_NAME} LIKE concat('%',${'#'}{${col.COLUMN_NAME_HUMP}_LLIKE})
			</if>
			<if test="${col.COLUMN_NAME_HUMP}_RLIKE != null and ${col.COLUMN_NAME_HUMP}_RLIKE != ''">
				AND T.${col.COLUMN_NAME} LIKE concat(${'#'}{${col.COLUMN_NAME_HUMP}_RLIKE},'%')
			</if>
			</#if>
				
			</#list>
		</where>
	</sql>
	<!-- ${tabelComments}通用查询 -->
    <select id="select${className}" parameterType="${basePackage}.entity.${className}Entity" resultMap="BaseResultMap" >
     	<if test="curPage != null and curPage != ''">
            <if test="pageSize != null and pageSize != ''">
                SELECT F.* FROM (
            </if>
        </if>
         SELECT E.*,ROWNUM ROWNO FROM (
            SELECT <#list colList as col><#assign fieldSqlType='${col.DATA_TYPE}'><#if fieldSqlType=="DATE">
            TO_CHAR(T.${col.COLUMN_NAME},'YYYY-MM-DD hh24:mi:ss') ${col.COLUMN_NAME}<#if col_index+1 != colList?size>,</#if><#else>T.${col.COLUMN_NAME}<#if col_index+1 != colList?size>,</#if></#if><#if (col_index+1)%5==0>${'\r\n\t\t\t'}</#if></#list>
             ,FIRST_VALUE(ROWNUM) OVER(ORDER BY ROWNUM DESC) AS TOTAL
             FROM ${tabelName} T
            <include refid="${tabelName}_WHERE"/>
            <if test="sortName != null and sortName != ''">
            	 ORDER BY  ${r'${sortName}'}
            	 <if test="sortOrder != null and sortOrder != ''">
            	 	 ${r'${sortOrder}'}
            	 </if>
            </if>
            )E
      <if test="curPage != null and curPage != ''">
            <if test="pageSize != null and pageSize != ''">
                <![CDATA[ WHERE ROWNUM <=${'#'}{curPage} * ${'#'}{pageSize} ) F WHERE F.ROWNO >=(${'#'}{curPage} - 1) * ${'#'}{pageSize} + 1]]>
            </if>
        </if>
    </select>
    
    <!-- ${tabelComments}通用查询个数 -->
    <select id="select${className}Count" parameterType="${basePackage}.entity.${className}Entity" resultType="java.lang.Integer" >
     	 SELECT COUNT(1) FROM ${tabelName} T <include refid="${tabelName}_WHERE"/>
    </select>
    
    <!-- ${tabelComments}保存 -->
    <insert id="insert${className}" parameterType="${basePackage}.entity.${className}Entity" >
    	insert into ${tabelName} 
    	<trim prefix="(" suffix=")" suffixOverrides="," >
    	<#list colList  as col>
			<#assign DATA_TYPE='${col.DATA_TYPE}'>
			<#if DATA_TYPE=="DATE">
			 ${col.COLUMN_NAME} ,
			<#else>
			<if test="${col.COLUMN_NAME_HUMP} != null and ${col.COLUMN_NAME_HUMP} != ''">
				  ${col.COLUMN_NAME} ,
			</if>
			</#if>
		</#list>
		</trim>
		<trim prefix="values (" suffix=")" suffixOverrides="," >
		<#list colList  as col>
			<#assign DATA_TYPE='${col.DATA_TYPE}'>
			<#if DATA_TYPE=="DATE">
				SYSDATE,
			<#else>
			<if test="${col.COLUMN_NAME_HUMP} != null and ${col.COLUMN_NAME_HUMP} != ''">
				 ${'#'}{${col.COLUMN_NAME_HUMP}},  
			</if>
			</#if>
		</#list>
		</trim>
    </insert>
    
    <!-- ${tabelComments}修改-->
    <update id="update${className}" parameterType="${basePackage}.entity.${className}Entity" >
    	update   ${tabelName} 
    	<set>
    	<#list colList  as col>
			<#assign DATA_TYPE='${col.DATA_TYPE}'>
			<#if DATA_TYPE=="DATE">
			<if test="${col.COLUMN_NAME_HUMP} != null and ${col.COLUMN_NAME_HUMP} != ''">
				  ${col.COLUMN_NAME}=  TO_DATE(${'#'}{${col.COLUMN_NAME_HUMP}},'YYYY-MM-DD hh24:mi:ss'),
			</if>
			 
			<#else>
			<if test="${col.COLUMN_NAME_HUMP} != null and ${col.COLUMN_NAME_HUMP} != ''">
				  ${col.COLUMN_NAME}=  ${'#'}{${col.COLUMN_NAME_HUMP}},
			</if>
			</#if>
		</#list>
		</set>
		 where ${keyMap.COLUMN_NAME}=${'#'}{${keyMap.COLUMN_NAME_HUMP}}
    </update>
    
    <!-- ${tabelComments}删除-->
    <delete id="delete${className}" parameterType="${basePackage}.entity.${className}Entity" >
    	delete  from ${tabelName}   where ${keyMap.COLUMN_NAME}=${'#'}{${keyMap.COLUMN_NAME_HUMP}}
    </delete>
    
</mapper>