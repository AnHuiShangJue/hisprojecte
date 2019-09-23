package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisTransferRecord;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface HisTransferRecordMapper extends BaseMapper<HisTransferRecord>{
    int deleteByPrimaryKey(Long id);

    int insert(HisTransferRecord record);

    int insertSelective(HisTransferRecord record);

    HisTransferRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisTransferRecord record);

    int updateByPrimaryKey(HisTransferRecord record);

    
    /**
     * @Description list
     * @Author  muxu
     * @Date  2019/9/15
     * @Time 16:56
     * @Return java.util.List<? extends com.ahsj.hiscore.entity.HisTransferRecord>
     * @Params [pageBean]
    **/
    List<? extends HisTransferRecord> list(PageBean<HisTransferRecord> pageBean);

}