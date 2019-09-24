package com.ahsj.hiscore.services;


import com.ahsj.hiscore.entity.HisDunningList;
import core.entity.PageBean;
import core.message.Message;

import java.math.BigDecimal;

public interface HisDunningListService {

    Message save(String MedicalRecordId, Double money,String number)throws Exception;

    PageBean<HisDunningList> list(PageBean<HisDunningList> pageBean) throws Exception;


}
