package com.ahsj.hiscore.quartz;

import com.ahsj.hiscore.entity.HisMedicalOrderDetail;
import com.ahsj.hiscore.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: hisprojecte
 * @description:
 * @author: chenzhicai
 * @create: 2019-07-22-15-31
 **/

@RestController
@RequestMapping("/job")
public class QuartzJob {

    Logger logger = LoggerFactory.getLogger(QuartzJob.class.getSimpleName());


    @Autowired
    HisDailyRecordService hisDailyRecordService;

    @Autowired
    TranslateInfoService translateInfoService;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    @Autowired
    HisDrugLossReportingService hisDrugLossReportingService;

    @Autowired
    HisMedicalOrderDetailService hisMedicalOrderDetailService;

    /**
     * @return void
     * @Description 开始执行定时任务   cro表达式为Seconds Minutes Hours DayofMonth Month DayofWeek Year               https://sunnyzh66.iteye.com/blog/2294605
     * @Params []
     * @Author chenzhicai
     * @Date 2019-07-20
     * @Time 16:04
     **/
    @Scheduled(cron = "0 59 23 * * ?")
    public void djob() {
        logger.info("-------------------开始日结-----------------------");
        hisDailyRecordService.startCalculate();
        logger.info("-------------------结束日结-----------------------");
    }

//    @Scheduled(cron = "0/5 * * * * ?")
    @Scheduled(cron = "0 59 23 * * ?")
    public void job() {
        logger.info("-------------------开始住院数据操作-----------------------");
        hisHospitalManageService.startAddDate();
        logger.info("-------------------结束住院数据操作-----------------------");
    }

    /**
     *@Description 药品报损定时任务
     *@Params []
     *@return void
     *@Author zhushixiang
     *@Date 2019-08-02
     *@Time 10:46
    **/
//        @Scheduled(cron = "0/5 * * * * ?")
    @Scheduled(cron = "0 59 23 * * ?")
    public void druglossjob()throws Exception {
        logger.info("-------------------扫描药品信息-----------------------");
        hisDrugLossReportingService.startScanDrug();
        logger.info("-------------------扫描结束-----------------------");
    }

    /**
     * @Description 护理级别定时任务
     * @Author  muxu
     * @Date  2019/9/24
     * @Time 18:29
     * @Return
     * @Params
    **/
    @Scheduled(cron = "0 59 23 * * ?")
    public void careLeveladdjob()throws Exception {
        logger.info("-------------------开始日结-----------------------");
        hisHospitalManageService.startAddcareLevel();
        logger.info("-------------------结束日结-----------------------");
    }


    /**
     *@功能说明  翻译
     *@Params []
     *@return void
     *@Author XJP
     *@Date 2019/8/8
     *@Time 16:44
    **/
    @Scheduled(cron = "0 59 23 * * ?")
    public void TranslateInfojob() throws Exception {
        logger.info("-------------------翻译信息开始-----------------------");
        translateInfoService.TranslateInfojob();
        logger.info("-------------------翻译信息结束-----------------------");
    }

    /**
     *@Description 根据医嘱当时生成输液单(定时任务)
     *@Params []
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-25
     *@Time 21:33
    **/
    @Scheduled(cron = "0 0 0 * * ?")
//    @Scheduled(cron = "0/30 * * * * ?")
    public void createInfusionByMedicalOrder()throws Exception {
        logger.info("-------------------扫描医嘱详细信息，生成输液单-----------------------");
        hisMedicalOrderDetailService.createInfusionByMedicalOrder();
        logger.info("-------------------扫描结束-----------------------");
    }

    /**
     *@Description 为所有正在住院的病人根据他们的长期医嘱每天定时生成对应的药品与项目收费明细（长期医嘱医生只需开一天的量即可）
     *@Params 
     *@return 
     *@Author zhushixiang
     *@Date 2019-10-04
     *@Time 23:42
    **/
    @Scheduled(cron = "0 0 0 * * ?")
//    @Scheduled(cron = "0/30 * * * * ?")
    public void createChargeDetailsForMedicalOrder()throws Exception {
        logger.info("-------------------扫描在住院病人长期医嘱详细药品与项目信息，生成收费明细-----------------------");
        hisMedicalOrderDetailService.createChargeDetailsForMedicalOrder();
        logger.info("-------------------扫描结束-----------------------");
    }
}

    