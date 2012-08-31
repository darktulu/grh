package com.bull.grh.service.admin;

import java.util.List;

import com.bull.grh.view.admin.vo.TypeVO;
import com.bull.grh.view.admin.vo.ValueVO;

public interface AdminValueService {

	public void save(TypeVO typeVO);

	public void updateType(TypeVO typeVO);

	public void deleteType(TypeVO typeVO);

	public void addValue(ValueVO valueVo);

	public void updateValue(ValueVO valueVO);

	public void deleteValue(ValueVO valueVo);

	public List<ValueVO> loadValuesByDefName(String name);

	public List<ValueVO> loadValuesByType(String typeName);

	public List<ValueVO> loadValuesRoot();

	public List<ValueVO> loadValuesChildren(String parentName);
	
	public boolean valueExists(ValueVO valueVO);

	public List<TypeVO> loadTypeChildren(String parentNom);

	public List<TypeVO> loadTypesRoot();

	public List<TypeVO> loadTypes();
	
	public boolean typeExists(TypeVO typeVO);

}
