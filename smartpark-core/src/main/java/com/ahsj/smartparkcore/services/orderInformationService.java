package com.ahsj.smartparkcore.services;

import com.ahsj.smartparkcore.entity.dto.SiteDTO;
import com.ahsj.smartparkcore.entity.po.Site;

/**
 * @Description
 * @Author dingli
 * @Date 2019/10/15
 * @Time 14:14
 **/
public interface orderInformationService {

    public String getAliPayOrderStr(Site Site) throws Exception;
}
