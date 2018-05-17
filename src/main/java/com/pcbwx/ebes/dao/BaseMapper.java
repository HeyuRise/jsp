package com.pcbwx.ebes.dao;

import java.io.Serializable;

/**
 * 基本持久化接口，供具体的持久化接口来继承
 * 
 * @author 王海龙
 *
 * @param <T>
 *            T为具体的Model类
 */
public interface BaseMapper<T> {
	/**
	 * 通过主键删除对象
	 * 
	 * @param id
	 *            模型id
	 * @return 影响的行数
	 */
	int deleteByPrimaryKey(Serializable id);

	/**
	 * 新增模型
	 * 
	 * @param record
	 *            模型对象
	 * @return 影响的行数
	 */
	int insert(T record);

	/**
	 * 新增模型(部分字段)
	 * 
	 * @param record
	 *            模型对象(部分字段有值)
	 * @return 影响的行数
	 */
	int insertSelective(T record);

	/**
	 * 通过主键获取模型对象
	 * 
	 * @param id
	 *            模型id
	 * @return 获取的模型对象
	 */
	T selectByPrimaryKey(Serializable id);

	/**
	 * 更新模型对象(部分字段)
	 * 
	 * @param record
	 *            模型对象
	 * @return 影响的行数
	 */
	int updateByPrimaryKeySelective(T record);

	/**
	 * 更新模型对象
	 * 
	 * @param record
	 *            模型对象
	 * @return 影响的行数
	 */
	int updateByPrimaryKey(T record);
}