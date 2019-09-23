package com.ahsj.translate.dao;

import com.ahsj.translate.entity.Translate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TranslateMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Translate translate);

    int insertSelective(Translate translate);

    Translate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Translate translate);

    int updateByPrimaryKey(Translate translate);

    int addTranslateList(List<Translate> translateList);

    List<Translate> queryTranslateList(Translate translate);

    List<Translate> queryTranslateListAll();

    Translate queryTranslate(Translate translate);

    int deleteByTranslate(Translate translate);
}