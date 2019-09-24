package com.ahsj.hiscore.quartz;

import com.ahsj.hiscore.services.HisDailyRecordService;
import com.ahsj.hiscore.services.HisDrugLossReportingService;
import com.ahsj.hiscore.services.HisHospitalManageService;
import com.ahsj.hiscore.services.TranslateInfoService;
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
}

    