package com.ahsj.smartparkcore.services;

import com.ahsj.smartparkcore.entity.dto.StationInfoDTO;
import com.ahsj.smartparkcore.entity.po.EnterpriseInfo;
import com.ahsj.smartparkcore.entity.po.StationInfo;
import com.ahsj.smartparkcore.entity.vo.StationInfoVO;
import core.entity.PageBean;
import core.message.Message;
import org.springframework.web.multipart.MultipartFile;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: StationInfoService
 * <p>
 * Date:     2019/10/19 17:57
 *
 * @author XJP
 * @create 2019/10/19
 * @since 1.0.0
 */

public interface StationInfoService {
    /**
     * @return core.entity.PageBean<com.ahsj.smartparkcore.entity.po.StationInfo>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/10/21
     * @Time 15:18
     **/
    PageBean<StationInfoDTO> queryList(PageBean<StationInfoDTO> pageBean) throws Exception;

    /**
     * @return com.ahsj.smartparkcore.entity.vo.StationInfoVO
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/10/21
     * @Time 15:18
     **/
    StationInfoVO selectById(Long id) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [infoDTO, file, relateKet, relatePage]
     * @Author XJP
     * @Date 2019/10/21
     * @Time 15:18
     **/
    Message addStationinfo(StationInfoDTO infoDTO, MultipartFile[] file, String relateKet, String relatePage) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [stationInfoDTO, file, relateKet, relatePage]
     * @Author XJP
     * @Date 2019/10/21
     * @Time 15:18
     **/
    Message updateStationinfo(StationInfoDTO stationInfoDTO, MultipartFile[] file, String relateKet, String relatePage) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [ids]
     * @Author XJP
     * @Date 2019/10/21
     * @Time 15:18
     **/
    Message updateSetDisable(Long[] ids) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/10/23
     * @Time 10:07
     **/
    Message reviewFail(Long id) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/10/23
     * @Time 10:07
     **/
    Message reviewSuccess(Long id) throws Exception;
}
