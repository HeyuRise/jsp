package com.pcbwx.ebes.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.ebes.model.ExpressCompany;

public interface ExpressCompanyMapper extends BaseMapper<ExpressCompany>{
	
	List<ExpressCompany> load(@Param("enable") Integer enable);
	
	List<ExpressCompany> selectByCompanyName(@Param("companyName") String companyName);
}