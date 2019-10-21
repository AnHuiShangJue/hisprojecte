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

    PageBean<StationInfo>  queryList(PageBean<StationInfo> pageBean) throws Exception;

    StationInfoVO selectById(Long id) throws Exception;

    Message addStationinfo(StationInfoDTO infoDTO, MultipartFile[] file, String relateKet, String relatePage)throws Exception;

    Message updateStationinfo(StationInfoDTO stationInfoDTO, MultipartFile[] file, String relateKet, String relatePage)throws Exception;
}
