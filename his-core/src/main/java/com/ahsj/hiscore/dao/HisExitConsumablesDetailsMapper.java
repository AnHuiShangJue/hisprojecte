package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisExitConsumablesDetails;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HisExitConsumablesDetailsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HisExitConsumablesDetails record);

    int insertSelective(HisExitConsumablesDetails record);

    HisExitConsumablesDetails selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisExitConsumablesDetails record);

    int updateByPrimaryKey(HisExitConsumablesDetails record);

    List<? extends HisExitConsumablesDetails> list(PageBean<HisExitConsumablesDetails> pageBean);

    List<HisExitConsumablesDetails> listByNumber(PageBean<HisExitConsumablesDetails> pageBean);

//    List<? extends HisExitConsumablesDetails> getExitConsumabes(String exitNumber);

    /**
     * @Description 耗材盘点
     * @Params: [pageBean]
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisTollRecord>
     * @Date 2019/9/19
     * @Time 17:10
     **/
    List<HisExitConsumablesDetails> consumableInventory(PageBean<HisExitConsumablesDetails> pageBean) throws Exception;


}