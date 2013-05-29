package com.bull.grh.service.admin.impl;

import com.bull.grh.domaine.TypeValue;
import com.bull.grh.domaine.ValueList;
import com.bull.grh.repos.admin.TypeValueDao;
import com.bull.grh.repos.admin.ValueListDao;
import com.bull.grh.service.admin.AdminValueService;
import com.bull.grh.view.admin.vo.TypeVO;
import com.bull.grh.view.admin.vo.ValueVO;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service("adminValueService")
@Transactional
public class AdminValueServiceImpl implements AdminValueService {
    @Inject
    private DozerBeanMapper mapper;
    @Inject
    private TypeValueDao typeValueDao;
    @Inject
    private ValueListDao valueListDao;

    protected static Logger logger = Logger.getLogger("AdminValueService");

    @Override
    public void save(TypeVO typeVO) {
        logger.debug("saving typeVO");

        if (typeVO == null) {
            logger.error("typeVO is Null");
        } else {

            TypeValue typeValue = new TypeValue();
            typeValue.setNom(typeVO.getNom());

            if (typeVO.getParent() != null){
                typeValue.setParent(typeValueDao.findOne(typeVO.getParent().getId()));
            }
            typeValueDao.save(typeValue);
        }
    }

    @Override
    public void updateType(TypeVO typeVO) {
        logger.debug("updateType typevalue");

        if (typeVO == null) {
            logger.error("typeValue is Null");
        } else {
            TypeValue typeValue = typeValueDao.findOne(typeVO.getId());
            typeValue.setNom(typeVO.getNom());
            typeValueDao.save(typeValue);
        }
    }

    @Override
    public void deleteType(TypeVO typeVO) {
        logger.debug("delete Type");

        if (typeVO == null || "".equals(typeVO.getNom())) {
            logger.error("typeVO is Null or name doesn't exist");
        } else {
            TypeValue typeValue = typeValueDao.findOne(typeVO.getId());
            typeValueDao.delete(typeValue);
        }
    }

    @Override
    public void addValue(ValueVO valueVo) {
        logger.debug("addValue");

        if (valueVo == null) {
            logger.error("valueVo is Null");
        } else {

            ValueList valueList = new ValueList();
            ValueList parent = null;

            if (valueVo.getParent() != null) {
                parent = valueListDao.findOne(valueVo.getParent().getId());
            }

            valueList.setParent(parent);
            valueList.setValue(valueVo.getValue());

            valueListDao.save(valueList);
        }
    }

    @Override
    public void updateValue(ValueVO valueVO) {
        logger.debug("updateValue valueList");

        if (valueVO == null) {
            logger.error("valueList is Null");
        } else {
            ValueList valueList = valueListDao.findOne(valueVO.getId());
            valueList.setValue(valueVO.getValue());
            valueListDao.save(valueList);
        }
    }

    @Override
    public void deleteValue(ValueVO valueVo) {
        logger.debug("deleteValue");

        if (valueVo == null) {
            logger.error("valueVO is Null");
        } else {
            ValueList valueList = valueListDao.findOne(valueVo.getId());
            valueListDao.delete(valueList);
        }
    }

    @Override
    public List<ValueVO> loadValuesRoot() {
        List<ValueVO> values = new ArrayList<ValueVO>();
        for (ValueList value : valueListDao.findByParentIsNull()) {
            values.add(mapper.map(value, ValueVO.class));
        }
        return values;
    }

    @Override
    public List<ValueVO> loadValuesByDefName(String name) {
        List<ValueVO> values = new ArrayList<ValueVO>();
        for (ValueList value : valueListDao.findByTypeValueNom(name)) {
            values.add(mapper.map(value, ValueVO.class));
        }
        return values;
    }

    @Override
    public List<ValueVO> loadValuesChildren(String parentName) {
        List<ValueVO> values = new ArrayList<ValueVO>();
        for (ValueList value : valueListDao.findByParentValue(parentName)) {
            values.add(mapper.map(value, ValueVO.class));
        }
        return values;
    }

    @Override
    public boolean valueExists(ValueVO valueVO) {
        return valueListDao.findByValue(valueVO.getValue()) != null;
    }

    @Override
    public boolean typeExists(TypeVO typeVO) {
        return typeValueDao.findByNom(typeVO.getNom()) != null;
    }

    @Override
    public List<ValueVO> loadValuesByType(String typeName) {
        List<ValueVO> values = new ArrayList<ValueVO>();
        for (ValueList value : valueListDao.findByTypeValueNom(typeName)) {
            values.add(mapper.map(value, ValueVO.class));
        }
        return values;
    }

    @Override
    public List<TypeVO> loadTypes() {
        List<TypeVO> types = new ArrayList<TypeVO>();
        for (TypeValue type : typeValueDao.findAll()) {
            types.add(mapper.map(type, TypeVO.class));
        }
        return types;
    }

    @Override
    public List<TypeVO> loadTypeChildren(String parentNom) {
        List<TypeVO> types = new ArrayList<TypeVO>();
        for (TypeValue type : typeValueDao.findByParentNom(parentNom)) {
            types.add(mapper.map(type, TypeVO.class));
        }
        return types;
    }

    @Override
    public List<TypeVO> loadTypesRoot() {
        List<TypeVO> types = new ArrayList<TypeVO>();
        for (TypeValue type : typeValueDao.findByParentIsNull()) {
            types.add(mapper.map(type, TypeVO.class));
        }
        return types;
    }
}
