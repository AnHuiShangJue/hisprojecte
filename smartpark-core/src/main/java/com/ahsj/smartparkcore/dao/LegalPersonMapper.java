package com.ahsj.smartparkcore.dao;

import com.ahsj.smartparkcore.entity.po.LegalPerson;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LegalPersonMapper {

    int deleteByPrimaryKey(Long id);

    int insert(LegalPerson legalPerson);

    int insertSelective(LegalPerson legalPerson);

    LegalPerson selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LegalPerson legalPerson);

    int updateByPrimaryKey(LegalPerson legalPerson);

    /**
     * @return int
     * @功能说明
     * @Params [legalPerson]
     * @Author XJP
     * @Date 2019/10/14
     * @Time 17:26
     **/
    int updateByCompanyId(LegalPerson legalPerson);
}