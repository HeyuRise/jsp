package com.pcbwx.ebes.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.pcbwx.ebes.model.ExpressProduct;

public interface ExpressProductMapper extends BaseMapper<ExpressProduct> {
	
	@Select("select GREATEST(COALESCE(max(create_time)),COALESCE(max(update_time),0)) from express_product")
	Date selectLastRecordTime();

	/**
	 * 按产品名称查找
	 * @param productName
	 * @return
	 */
	List<ExpressProduct> selectByProductName(
			@Param("productName") String productName);

	/**
	 * 按快递公司Id和产品Code查找
	 * @param expressCompanyId
	 * @param productCode
	 * @return
	 */
	List<ExpressProduct> selectByCompanyIdAndProductCode(
			@Param("expressCompanyId") Integer expressCompanyId,
			@Param("productCode") String productCode);
	
	List<ExpressProduct> selectEnableByCompanyIdAndProductCode(
			@Param("expressCompanyId") Integer expressCompanyId,
			@Param("productCode") String productCode);
	
	/**
	 * 按快递公司Id和产品Code删除
	 * @param expressCompanyId
	 * @param productCode
	 * @return
	 */
	Integer deleteByCompanyIdAndProductCode(
			@Param("expressCompanyId") Integer expressCompanyId,
			@Param("productCode") String productCode);
	
	/**
	 * 查找可用的产品
	 * @return
	 */
	List<ExpressProduct> selectByStatus();
}