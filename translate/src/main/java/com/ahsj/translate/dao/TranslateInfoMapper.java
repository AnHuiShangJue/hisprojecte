package com.ahsj.translate.dao;

import com.ahsj.translate.entity.Translate;
import com.ahsj.translate.entity.TranslateInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TranslateInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TranslateInfo translateInfo);

    int insertSelective(TranslateInfo translateInfo);

    TranslateInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TranslateInfo translateInfo);

    int updateByPrimaryKey(TranslateInfo translateInfo);

    /**
     * @return int
     * @功能说明
     * @Params [translateList]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 16:24
     **/
    int addTranslateList(List<TranslateInfo> translateList);

    /**
     * @return java.util.List<com.ahsj.translate.entity.TranslateInfo>
     * @功能说明
     * @Params [translateInfo]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 16:24
     **/
    List<TranslateInfo> queryTranslateList(TranslateInfo translateInfo);

    /**
     * @return java.util.List<com.ahsj.translate.entity.TranslateInfo>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/8
     * @Time 16:24
     **/
    List<TranslateInfo> queryTranslateListAll();

    /**
     * @return com.ahsj.translate.entity.TranslateInfo
     * @功能说明
     * @Params [translateInfo]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 16:24
     **/
    TranslateInfo queryTranslate(TranslateInfo translateInfo);

    /**
     * @return int
     * @功能说明
     * @Params [translateInfo]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 16:24
     **/
    int deleteByTranslate(TranslateInfo translateInfo);

    /**
     * @return java.util.List<com.ahsj.translate.entity.TranslateInfo>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 13:28
     **/
    List<TranslateInfo> selectByTranslateType();

    /**
     * @return java.util.List<com.ahsj.translate.entity.TranslateInfo>
     * @功能说明
     * @Params [translateInfo]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 13:28
     **/
    List<TranslateInfo> selectByTranslateTypeAndTranslateId(TranslateInfo translateInfo);


}