package com.ahsj.smartparkcore.services;

import com.ahsj.smartparkcore.entity.dto.LeaseDTO;
import com.ahsj.smartparkcore.entity.po.EnterpriseInfo;
import com.ahsj.smartparkcore.entity.po.Lease;
import com.ahsj.smartparkcore.entity.vo.LeaseVO;
import core.entity.PageBean;
import sun.plugin2.message.Message;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: LeaseService
 * <p>
 * Date:     2019/10/19 15:06
 *
 * @author XJP
 * @create 2019/10/19
 * @since 1.0.0
 */

public interface LeaseService {

    /**
     * @return core.entity.PageBean<com.ahsj.smartparkcore.entity.vo.LeaseVO>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/10/19
     * @Time 17:39
     **/
    PageBean<LeaseDTO> queryList(PageBean<LeaseDTO> pageBean) throws Exception;

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.vo.LeaseVO>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/10/19
     * @Time 17:39
     **/
    List<LeaseVO> List() throws Exception;

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.vo.LeaseVO>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/10/19
     * @Time 17:39
     **/
    List<LeaseVO> isLeaseList() throws Exception;

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.vo.LeaseVO>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/10/19
     * @Time 17:39
     **/
    List<LeaseVO> noLeaseList() throws Exception;

    /**
     * @Description 根据 targetType  targetId 查信息
     * @Params: [lease]
     * @Author: dingli
     * @Return: int
     * @Date 2019/10/21
     * @Time 14:52
     **/
    LeaseDTO selectLease(LeaseVO leaseVO) throws Exception;
}
