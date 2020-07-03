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
				AND T.${col.COLUMN_NAME} = STR_TO_DATE(${'#'}{${col.COLUMN_NAME_HUMP},'%Y-%m-%d %H:%i:%s')
			</if>
			<if test="${col.COLUMN_NAME_HUMP}_MIN != null and ${col.COLUMN_NAME_HUMP}_MIN != ''">
				AND T.${col.COLUMN_NAME} &gt;= STR_TO_DATE(${'#'}{${col.COLUMN_NAME_HUMP}_MIN},'%Y-%m-%d %H:%i:%s')
			</if>
			<if test="${col.COLUMN_NAME_HUMP}_MAX != null and ${col.COLUMN_NAME_HUMP}_MAX != ''">
				AND T.${col.COLUMN_NAME} &lt;= STR_TO_DATE(${'#'}{${col.COLUMN_NAME_HUMP}_MAX},'%Y-%m-%d %H:%i:%s')
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
		<bind name="startNumber" value="(curPage-1)*pageSize"></bind>
		SELECT (@rowNum:=@rowNum+1) AS ROWNO,
		(SELECT COUNT(1) FROM ${tabelName} T <include refid="${tabelName}_WHERE"/>) AS TOTAL,
		E.* FROM (
		SELECT <#list colList as col><#assign fieldSqlType='${col.DATA_TYPE}'><#if fieldSqlType=="DATE">
			DATE_FORMAT(T.${col.COLUMN_NAME},'%Y-%m-%d %H:%i:%s') ${col.COLUMN_NAME}<#if col_index+1 != colList?size>,</#if><#else>T.${col.COLUMN_NAME}<#if col_index+1 != colList?size>,</#if></#if><#if (col_index+1)%5==0>${'\r\n\t\t\t'}</#if></#list>
		FROM ${tabelName} T
		<include refid="${tabelName}_WHERE"/>
		<if test="sortName != null and sortName != ''">
			ORDER BY  ${r'${sortName}'}
 				${r'${sortOrder}'}
 		</if>
			limit ${r'${startNumber}'},${r'${pageSize}'}
		)E,${tabelName} T
		<if test="pageSize != null and pageSize != ''">
			,(SELECT(@rowNum :=${r'${startNumber}'}))TEM_TB
		</if>
		WHERE E.${keyMap.COLUMN_NAME}=T.${keyMap.COLUMN_NAME}

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
			<if test="${col.COLUMN_NAME_HUMP} != null and ${col.COLUMN_NAME_HUMP} != ''">
				  ${col.COLUMN_NAME} ,
			</if>
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
			<if test="${col.COLUMN_NAME_HUMP} != null and ${col.COLUMN_NAME_HUMP} != ''">
				 STR_TO_DATE(${'#'}{${col.COLUMN_NAME_HUMP}},'%Y-%m-%d %H:%i:%s'),
			</if>
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
    	update   ${tabelName} T
    	<set>
    	<#list colList  as col>
			<#assign DATA_TYPE='${col.DATA_TYPE}'>
			<#if DATA_TYPE=="DATE">
			<if test="${col.COLUMN_NAME_HUMP}_NEW != null and ${col.COLUMN_NAME_HUMP}_NEW != ''">
				  ${col.COLUMN_NAME}=  STR_TO_DATE(${'#'}{${col.COLUMN_NAME_HUMP}_NEW},'%Y-%m-%d %H:%i:%s'),
			</if>

			<#else>
			<if test="${col.COLUMN_NAME_HUMP}_NEW != null and ${col.COLUMN_NAME_HUMP}_NEW != ''">
				  ${col.COLUMN_NAME}=  ${'#'}{${col.COLUMN_NAME_HUMP}_NEW},
			</if>
			</#if>
		</#list>
		</set>
		<include refid="${tabelName}_WHERE"/>
    </update>

    <!-- ${tabelComments}删除-->
    <delete id="delete${className}" parameterType="${basePackage}.entity.${className}Entity" >
    	delete  T  from ${tabelName}  T  <include refid="${tabelName}_WHERE"/>
    </delete>

</mapper>