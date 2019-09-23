package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.dao.HisMedirecordProjectMapper;
import com.ahsj.hiscore.entity.HisMedirecordProject;
import com.ahsj.hiscore.services.HisMedirecordProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author dingli
 * @Date 2019/7/16
 * @Time 14:07
 **/
@Service
public class HisMedirecordProjectServicImpl implements HisMedirecordProjectService {
 @Autowired
 HisMedirecordProjectMapper hisMedirecordProjectMapper;
    @Override
    public int updateHisMedirecordProject(HisMedirecordProject record) throws Exception {
        return hisMedirecordProjectMapper.updateByPrimaryKeySelective(record);
    }
/** className HisMedirecordProjectServicImpl
 *@author dingli
 *@date 2019/7/16 14:07
 */
}
