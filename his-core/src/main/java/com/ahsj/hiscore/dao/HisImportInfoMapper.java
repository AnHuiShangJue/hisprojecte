package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisImportInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HisImportInfoMapper extends BaseMapper<HisImportInfo> {

    int deleteByPrimaryKey(Long id);

    int insert(HisImportInfo hisImportInfo);

    int insertSelective(HisImportInfo hisImportInfo);

    HisImportInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisImportInfo hisImportInfo);

    int updateByPrimaryKey(HisImportInfo hisImportInfo);

    /**
     * @return void
     * @功能说明
     * @Params [importInfoList]
     * @Author XJP
     * @Date 2019/8/30
     * @Time 13:43
     **/
    void inserHisImportInfo(List<HisImportInfo> importInfoList);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisImportInfo>
     * @功能说明
     * @Params [hisImportInfo]
     * @Author XJP
     * @Date 2019/9/3
     * @Time 15:26
     **/
    List<HisImportInfo> queryHisImportInfo(HisImportInfo hisImportInfo);
}