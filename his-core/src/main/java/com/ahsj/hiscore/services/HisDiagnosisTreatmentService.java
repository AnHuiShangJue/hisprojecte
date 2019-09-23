package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisDiagnosisTreatment;
import core.message.Message;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisDiagnosisTreatmentService
 * <p>
 * Date:     2019/9/16 16:16
 *
 * @author XJP
 * @create 2019/9/16
 * @since 1.0.0
 */

public interface HisDiagnosisTreatmentService {

    /**
     * @return core.message.Message
     * @功能说明 增添和修改有创治疗信息
     * @Params [hisDiagnosisTreatment]
     * @Author XJP
     * @Date 2019/9/17
     * @Time 10:26
     **/
    Message addOrUpdateHisDiagnosisTreatment(HisDiagnosisTreatment hisDiagnosisTreatment) throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.HisDiagnosisTreatment
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/9/17
     * @Time 10:28
     **/
    HisDiagnosisTreatment selectByPrimaryKey(Long id) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明 医生有创治疗签字
     * @Params [hisDiagnosisTreatment, id]
     * @Author XJP
     * @Date 2019/9/17
     * @Time 11:15
     **/
    Message signature(HisDiagnosisTreatment hisDiagnosisTreatment, Long userId) throws Exception;
}
