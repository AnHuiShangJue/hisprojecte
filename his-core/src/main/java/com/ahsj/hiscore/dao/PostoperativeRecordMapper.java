package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.PostoperativeRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostoperativeRecordMapper {

    int deleteByPrimaryKey(Long id);

    int insert(PostoperativeRecord postoperativeRecord);

    int insertSelective(PostoperativeRecord postoperativeRecord);

    PostoperativeRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PostoperativeRecord postoperativeRecord);

    int updateByPrimaryKey(PostoperativeRecord postoperativeRecord);
}