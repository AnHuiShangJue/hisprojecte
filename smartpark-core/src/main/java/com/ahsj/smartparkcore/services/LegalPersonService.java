package com.ahsj.smartparkcore.services;

import core.message.Message;
import com.ahsj.smartparkcore.entity.po.LegalPerson;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: LegalPersonService
 * <p>
 * Date:     2019/10/14 13:40
 *
 * @author XJP
 * @create 2019/10/14
 * @since 1.0.0
 */

public interface LegalPersonService {

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [legalPerson]
     * @Author XJP
     * @Date 2019/10/14
     * @Time 16:53
     **/
    Message insert(LegalPerson legalPerson) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [legalPerson]
     * @Author XJP
     * @Date 2019/10/14
     * @Time 16:54
     **/
    Message updateByPrimaryKeySelective(LegalPerson legalPerson) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [legalPerson]
     * @Author XJP
     * @Date 2019/10/14
     * @Time 17:25
     **/
    Message updateByCompanyId(LegalPerson legalPerson) throws Exception;
}
