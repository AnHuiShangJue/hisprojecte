package com.ahsj.hiscore.services;


import com.ahsj.hiscore.entity.HisAdlBarthel;
import core.entity.PageBean;
import core.message.Message;

public interface HisAdlBarthelService {


    /**
     *@Description 保存更新
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/18
     *@Time 17:35
    */
    Message saveOrUpdate(HisAdlBarthel hisAdlBarthel) throws Exception;

    /**
     *@Description list
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/19
     *@Time 17:51
    */
    PageBean<HisAdlBarthel> list(PageBean<HisAdlBarthel> pageBean) throws Exception;

    /**
     *@Description 根据id查找
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/20
     *@Time 10:34
    */
    HisAdlBarthel selectById(Long id)throws Exception;






}
