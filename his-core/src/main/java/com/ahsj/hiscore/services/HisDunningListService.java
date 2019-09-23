package com.ahsj.hiscore.services;


import core.message.Message;

import java.math.BigDecimal;

public interface HisDunningListService {

    Message save(String MedicalRecordId, Double money,String number)throws Exception;

}
