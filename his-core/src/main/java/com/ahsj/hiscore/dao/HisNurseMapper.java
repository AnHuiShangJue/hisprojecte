package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisDoctorInfo;
import com.ahsj.hiscore.entity.HisNurse;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HisNurseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HisNurse record);

    int insertSelective(HisNurse record);

    HisNurse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisNurse record);

    int updateByPrimaryKey(HisNurse record);

    List<? extends HisNurse> list(PageBean<HisNurse> pageBean);

    List<HisNurse> selectForInsert(HisNurse hisNurse);

    List<HisNurse> selectAll();

    List<HisNurse> getHisNurseByDepartmentId(Long departmentId );

    List<HisNurse> selectNurse();


    int selectNumberCount();

    HisDoctorInfo selectByNumber(String nurseNumber);
}