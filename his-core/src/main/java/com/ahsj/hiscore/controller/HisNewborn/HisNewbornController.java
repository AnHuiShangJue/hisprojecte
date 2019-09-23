package com.ahsj.hiscore.controller.HisNewborn;

import com.ahsj.hiscore.entity.HisHospitalManage;
import com.ahsj.hiscore.entity.HisNewborn;
import com.ahsj.hiscore.services.HisHospitalManageService;
import com.ahsj.hiscore.services.HisNewbornService;
import core.entity.PageBean;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisNewbornController
 * <p>
 * Date:     2019/9/10 13:15
 *
 * @author XJP
 * @create 2019/9/10
 * @since 1.0.0
 */

@RestController
@RequestMapping("/api/hisNewborn")
public class HisNewbornController {

    @Autowired
    HisNewbornService hisNewbornService;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisNewborn>
     * @功能说明 分页查询新生儿信息
     * @Params [hisNewborn]
     * @Author XJP
     * @Date 2019/9/10
     * @Time 13:31
     **/
    @PostMapping("/hisNewborn/list.ahsj")
    public PageBean<HisNewborn> queryList(HisNewborn hisNewborn) throws Exception {
        PageBean<HisNewborn> pageBean = new PageBean<>();
        pageBean.setParameter(hisNewborn);
        PageBean<HisNewborn> hisNewbornPageBean = hisNewbornService.queryList(pageBean);
        return hisNewbornPageBean;
    }

    /**
     * @return core.message.Message
     * @功能说明 新生儿信息新增
     * @Params [hisNewborn]
     * @Author XJP
     * @Date 2019/9/10
     * @Time 13:45
     **/
    @PostMapping("/add/hisNewborn.ahsj")
    public Message addHisNewborn(HisNewborn hisNewborn) throws Exception {
        return hisNewbornService.insertSelective(hisNewborn);
    }

    /**
     * @return com.ahsj.hiscore.entity.HisHospitalManage
     * @功能说明 查询新生儿的病床号
     * @Params []
     * @Author XJP
     * @Date 2019/9/11
     * @Time 14:38
     **/
   @PostMapping("/add/getHisHospitalManageBed.ahsj")
    public List<HisHospitalManage> getHisHospitalManageBed() throws Exception {
        return hisHospitalManageService.getHisHospitalManageBed();
    }

    /**
     * @return core.message.Message
     * @功能说明 新生儿信息修改
     * @Params [hisNewborn]
     * @Author XJP
     * @Date 2019/9/10
     * @Time 13:46
     **/
    @PostMapping("/update/hisNewborn.ahsj")
    public Message updateHisNewborn(HisNewborn hisNewborn) throws Exception {
        return hisNewbornService.updateByPrimaryKeySelective(hisNewborn);
    }

    /**
     * @return core.message.Message
     * @功能说明 通过病床编号修改婴儿的信息停用状态
     * @Params [hisNewborn]
     * @Author XJP
     * @Date 2019/9/10
     * @Time 15:18
     **/
    @PostMapping("/update/updateIsEnable.ahsj")
    public Message updateIsEnable(HisNewborn hisNewborn) throws Exception {
        return hisNewbornService.updateIsEnable(hisNewborn);
    }

    /**
     * @return com.ahsj.hiscore.entity.HisNewborn
     * @功能说明 查询打印数据
     * @Params [hisNewborn]
     * @Author XJP
     * @Date 2019/9/11
     * @Time 14:44
     **/
    @PostMapping("/select/print.ahsj")
    public HisNewborn print(HisNewborn hisNewborn) throws Exception {
        return hisNewbornService.selectByPrimaryKey(hisNewborn.getId());
    }

}
