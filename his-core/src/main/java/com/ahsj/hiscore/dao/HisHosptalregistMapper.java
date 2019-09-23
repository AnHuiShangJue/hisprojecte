package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisHosptalregist;

public interface HisHosptalregistMapper {
    /**  
         *@Description 
           * @param null
         *@Author: dingli
         *@return    
         *@Date 2019/7/13
         *@Time 15:38
        **/
    int deleteByPrimaryKey(Long id) throws Exception;
/**  
     *@Description 
       * @param null
     *@Author: dingli
     *@return    
     *@Date 2019/7/13
     *@Time 15:38
    **/
    int insert(HisHosptalregist record) throws Exception;
/**  
     *@Description 
       * @param null
     *@Author: dingli
     *@return    
     *@Date 2019/7/13
     *@Time 15:39
    **/
    int insertSelective(HisHosptalregist record) throws Exception;
/**  
     *@Description 
       * @param null
     *@Author: dingli
     *@return    
     *@Date 2019/7/13
     *@Time 15:39
    **/
    HisHosptalregist selectByPrimaryKey(Long id)throws Exception;
/**  
     *@Description 
       * @param null
     *@Author: dingli
     *@return    
     *@Date 2019/7/13
     *@Time 15:39
    **/
    int updateByPrimaryKeySelective(HisHosptalregist record)throws Exception;
/**  
     *@Description 
       * @param null
     *@Author: dingli
     *@return    
     *@Date 2019/7/13
     *@Time 15:39
    **/
    int updateByPrimaryKey(HisHosptalregist record)throws Exception;
/**  
     *@Description 
       * @param null
     *@Author: dingli
     *@return    
     *@Date 2019/7/13
     *@Time 15:39
    **/
    HisHosptalregist getHisHosptalregist(String  medicalNumber)throws Exception;
/**  
     *@Description 
       * @param null
     *@Author: dingli
     *@return    
     *@Date 2019/7/13
     *@Time 16:22
    **/
    int selectNumCount(String number) throws Exception;

    /**
     * @Description isfail
     * @Author   muxu
     * @Date 2019/7/17
     * @Time 17:35
     * @Return int
     * @Params [record]
    **/
    int updateIsFail(HisHosptalregist record)throws Exception;


    /**
     * @Description
     * @Author   muxu
     * @Date 2019/8/13
     * @Time 17:35
     * @Return int
     * @Params
     **/

    HisHosptalregist  selectByDepartmentId (Long id)throws Exception;
}