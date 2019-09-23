package com.ahsj.hiscore.controller.typography;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.common.dto.OrganizationUser;
import com.ahsj.hiscore.common.utils.JsonUtils;
import com.ahsj.hiscore.common.utils.Result;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.entity.dto.UserDept;
import com.ahsj.hiscore.entity.model.HisTypographyDateModel;
import com.ahsj.hiscore.entity.model.HisTypographyModel;
import com.ahsj.hiscore.entity.sys.Organization;
import com.ahsj.hiscore.feign.ICodeService;
import com.ahsj.hiscore.feign.IOrganizationService;
import com.ahsj.hiscore.feign.ITranslateService;
import com.ahsj.hiscore.services.HisConsumablesBuyplanDetailsService;
import com.ahsj.hiscore.services.HisMedicineInfoService;
import com.ahsj.hiscore.services.HisTypographyUserInfoService;
import com.ahsj.hiscore.services.TranslateInfoService;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * TODO
 * 描述 排班模块
 *
 * @InterfaceName: TypographyController
 * @author: XJP
 * @since: 2019/7/2 0002 下午 14:47
 */
@Controller
@RequestMapping("/api/typography")
public class TypographyController extends BaseController {

    @Autowired
    HisTypographyUserInfoService hisTypographyUserInfoService;

    @Autowired
    HisConsumablesBuyplanDetailsService hisConsumablesBuyplanDetailsService;

    @Autowired
    HisMedicineInfoService hisMedicineInfoService;

    @Autowired
    IOrganizationService iOrganizationService;


    @Autowired
    ITranslateService iTranslateService;

    @Autowired
    ICodeService iCodeService;

    @Autowired
    AmqpTemplate amqpTemplat;

    @Autowired
    TranslateInfoService translateInfoService;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 进入主页
     * @Params [token]
     * @Author XJP
     * @Date 2019/7/22
     * @Time 19:15
     **/
    @RequestMapping("/dept.ahsj")
    public ModelAndView getDept(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/typography/list");
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<HisTypographyUserInfo> userInfoList = hisTypographyUserInfoService.getHisTypographyUserInfoByNowDate(simpleFormatter.format(new Date()));
        modelAndView.addObject("typographyUserInfo", userInfoList);
        modelAndView.addObject("token", token);
        return modelAndView;
    }


    /**
     * @return java.lang.String
     * @Description 查询当前时刻以后的值班人员信息
     * @Params []
     * @Author XJP
     * @Date 2019/7/22
     * @Time 19:16
     **/
    @PostMapping("/typographyUserInfoList.ahsj")
    @ResponseBody
    public String getTypographyUserInfo() throws Exception {
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<HisTypographyUserInfo> userInfoList = hisTypographyUserInfoService.getHisTypographyUserInfoByNowDate(simpleFormatter.format(new Date()));
        Result result = new Result();
        result.setData(userInfoList);
        return JsonUtils.serialize(result);
    }


    /**
     * @return java.lang.String
     * @Description 查询历史时刻的值班人员信息
     * @Params []
     * @Author XJP
     * @Date 2019/7/22
     * @Time 19:16
     **/
    @PostMapping("/typographyUserInfoHistoryList.ahsj")
    @ResponseBody
    public String getHisTypographyUserInfoByHistoryDate() throws Exception {
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<HisTypographyUserInfo> userInfoList = hisTypographyUserInfoService.getHisTypographyUserInfoByHistoryDate(simpleFormatter.format(new Date()));
        Result result = new Result();
        result.setData(userInfoList);
        return JsonUtils.serialize(result);
    }


    /**
     * @return java.lang.String
     * @Description 查看一定时间范围I值班人员
     * @Params [startDate, endDate]
     * @Author XJP
     * @Date 2019/7/22
     * @Time 19:17
     **/
    @PostMapping("/startdate/endDate.ahsj")
    @ResponseBody
    public String getHisTypographyUserInfoStartDateAndEndDate(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        HisTypographyUserInfo hisTypographyUserInfo = new HisTypographyUserInfo();
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<HisTypographyUserInfo> list = null;
        try {
            Date startTime = simpleFormatter.parse(startDate);
            Date endTime = simpleFormatter.parse(endDate);
            hisTypographyUserInfo.setStartDate(startTime);
            hisTypographyUserInfo.setEndDate(endTime);
            list = hisTypographyUserInfoService.getHisTypographyUserInfoByStartDateAndEndDate(hisTypographyUserInfo);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Result result = new Result();
        if (list == null) {
            result.setData(list);
            result.setStatus(204);
            result.setMsg("失败");
            return JsonUtils.serialize(result);
        } else {
            result.setData(list);
            result.setStatus(200);
            result.setMsg("成功");
            return JsonUtils.serialize(result);
        }
    }


    /**
     * @return java.lang.String
     * @功能说明 查看某部门下的所有人员
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:40
     **/
    @PostMapping("/select/user/typography.ahsj")
    @ResponseBody
    public String getOrganizationById(@RequestParam("id") Long id) throws Exception {
        ArrayList<OrganizationUser> list = new ArrayList<>();
        Organization organization = iOrganizationService.selectUserByOrgId(id);
        if (organization != null) {
            List<UserInfo> userInfoList = organization.getUserInfoList();
            for (UserInfo userInfo : userInfoList) {
                OrganizationUser organizationUser = new OrganizationUser();
                organizationUser.setId(organization.getId());
                organizationUser.setOrganizationName(organization.getName());
                organizationUser.setUserName(userInfo.getUserName());
                list.add(organizationUser);
            }
            Result result = new Result();
            result.setData(list);
            result.setStatus(200);
            return JsonUtils.serialize(result);
        }
        return null;
    }


    /**
     * @return java.lang.String
     * @功能说明 查看某部门下的所有人员
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:40
     **/
    @PostMapping("/select/organization/typography.ahsj")
    @ResponseBody
    public String getOrganizationById(@RequestParam("id") String id) throws Exception {
        ArrayList<UserDept> userDepts = iOrganizationService.selectUserByOrgId(id);
        if (EmptyUtil.Companion.isNullOrEmpty(userDepts)){
            return null;
        }
        for (UserDept userDept : userDepts) {
            if (EmptyUtil.Companion.isNullOrEmpty(userDept.getUserName())){
                return null;
            }
        }
        UserDept userDept = userDepts.get(0);
        Result result = new Result();
        result.setMsg(userDept.getDeptName());
        result.setStatus(200);
        result.setData(userDepts);
        return JsonUtils.serialize(result);
    }


    /**
     * @return java.lang.String
     * @Description 批量删除
     * @Params [ids]
     * @Author XJP
     * @Date 2019/7/22
     * @Time 19:17
     **/
    @PostMapping("/delete/typography/id.ahsj")
    @ResponseBody
    public String deletebatchHisTypographyUserInfo(@RequestParam(value = "ids[]") Integer[] ids) throws Exception {
        Result result = new Result();
        if (ids != null) {
            hisTypographyUserInfoService.deletebatchHisTypographyUserInfo(ids);
            result.setMsg("成功");
            result.setStatus(200);
            return JsonUtils.serialize(result);
        } else {
            result.setMsg("失败");
            result.setStatus(204);
            return JsonUtils.serialize(result);
        }
    }


    /**
     * @return core.message.Message
     * @Description 新增 值班 信息
     * @Params [hisTypographyModel]
     * @Author XJP
     * @Date 2019/7/22
     * @Time 19:18
     **/
    @PostMapping("/save/Typography.ahsj")
    @ResponseBody
    public Message saveHisTypographyUserInfo(@RequestBody HisTypographyModel hisTypographyModel) throws Exception {
        return hisTypographyUserInfoService.saveHisTypographyUserInfo(hisTypographyModel.getHisTypographyUserInfoRep(), hisTypographyModel.getDeptId());
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.sys.Organization>
     * @功能说明
     * @Params [organization]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:40
     **/
    @PostMapping("/select/query/organization/Typography.ahsj")
    @ResponseBody
    public PageBean<Organization> saveHisTypographyUserInfo(Organization organization) throws Exception {
        PageBean<Organization> organizationPageBean = iOrganizationService.queryList(organization);
        return organizationPageBean;
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.sys.Organization>
     * @功能说明
     * @Params [organization]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:40
     **/
    @PostMapping("/select/query/organization/queryOrganizationList.ahsj")
    @ResponseBody
    public PageBean<Organization> queryOrganizationList(Organization organization) throws Exception {
        PageBean<Organization> pageBean = new PageBean<>();
        List<Organization> list = iOrganizationService.queryOrganizationList(organization.getId());
        pageBean.setData(list);
        return pageBean;
    }


    /**
     * @return java.lang.String
     * @Description 查看某部门 时间范围的值班数据
     * @Params [hisTypographyDateModel]
     * @Author XJP
     * @Date 2019/7/22
     * @Time 19:18
     **/
    @PostMapping("/query/queryHisTypographyUserInfoList.ahsj")
    @ResponseBody
    public String queryHisTypographyUserInfoList(@RequestBody HisTypographyDateModel hisTypographyDateModel) throws Exception {
        String[] typographyUserInfoListdate = hisTypographyDateModel.getTypographyUserInfoListdate();
        List<HisTypographyUserInfo> typographyUserInfoList = hisTypographyUserInfoService.getTypographyUserInfoListdate(typographyUserInfoListdate, hisTypographyDateModel.getDeptId());
        // List<HisTypographyUserInfo> typographyUserInfoList = hisTypographyUserInfoService.queryHisTypographyUserInfoList(hisTypographyDateModel.getTypographyUserInfoList(), hisTypographyDateModel.getDeptId());
        Result result = new Result();
        if (CollectionUtils.isEmpty(typographyUserInfoList)) {
            result.setMsg("查询失败");
            result.setStatus(204);
            return JsonUtils.serialize(result);
        } else {
            result.setMsg("查询成功");
            result.setStatus(200);
            result.setData(typographyUserInfoList);
            return JsonUtils.serialize(result);
        }
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisMedicineInfo>
     * @功能说明
     * @Params [hisMedicineInfo]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:40
     **/
    @PostMapping("/apii.ahsj")
    @ResponseBody
    public List<HisMedicineInfo> get(HisMedicineInfo hisMedicineInfo) throws Exception {
        List<HisMedicineInfo> hisMedicineInfos = hisMedicineInfoService.queryTranslateInfoByDate(hisMedicineInfo);
        return hisMedicineInfos;
    }

    /**
     * @return void
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:41
     **/
    @PostMapping("/api.ahsj")
    @ResponseBody
    public void get() throws Exception {
        Translate translate = new Translate();
        // translate.setTranslateId(480L);
        translate.setTranslateType(Constants.TRANSLATE_HIS_HISCONSUMABLES_LIST);
        //  translate.setTranslateColumn("value");
        List<Translate> translates = iTranslateService.queryTranslate(translate);
        System.out.println("--------------->>>>>>>>>>>>>>------------" + translates.size());
    }

    /**
     * @return void
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:41
     **/
    @PostMapping("/dsrw.ahsj")
    @ResponseBody
    public void gettranslateInfoService() throws Exception {
        translateInfoService.TranslateInfojob();
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisConsumablesBuyplanDetails>
     * @功能说明
     * @Params [hisConsumablesBuyplanDetails]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:41
     **/
    @PostMapping("/dsrwi.ahsj")
    @ResponseBody
    List<HisConsumablesBuyplanDetails> queryTranslateInfoByDate(HisConsumablesBuyplanDetails hisConsumablesBuyplanDetails) throws Exception {
        List<HisConsumablesBuyplanDetails> hisConsumablesBuyplanDetails1 = hisConsumablesBuyplanDetailsService.queryTranslateInfoByDate(hisConsumablesBuyplanDetails);
        return hisConsumablesBuyplanDetails1;
    }


}
