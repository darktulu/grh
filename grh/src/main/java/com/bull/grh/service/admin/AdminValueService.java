package com.bull.grh.service.admin;

import com.bull.grh.view.admin.vo.TypeVO;
import com.bull.grh.view.admin.vo.ValueVO;

import java.util.List;

public interface AdminValueService {
    void save(TypeVO typeVO);

    void updateType(TypeVO typeVO);

    void deleteType(TypeVO typeVO);

    void addValue(ValueVO valueVo);

    void updateValue(ValueVO valueVO);

    void deleteValue(ValueVO valueVo);

    List<ValueVO> loadValuesByDefName(String name);

    List<ValueVO> loadValuesByType(String typeName);

    List<ValueVO> loadValuesRoot();

    List<ValueVO> loadValuesChildren(String parentName);

    boolean valueExists(ValueVO valueVO);

    List<TypeVO> loadTypeChildren(String parentNom);

    List<TypeVO> loadTypesRoot();

    List<TypeVO> loadTypes();

    boolean typeExists(TypeVO typeVO);
}
