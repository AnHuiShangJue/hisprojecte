package com.ahsj.baseuserinfo.controller;

import com.ahsj.baseuserinfo.entity.SysCode;
import com.ahsj.baseuserinfo.entity.SysCodeDetail;
import com.ahsj.baseuserinfo.entity.TreeEntity;
import com.ahsj.baseuserinfo.services.CodeService;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import java.util.List;

@RestController
@RequestMapping("/api/syscode/")
public class SysCodeController extends BaseController {
    @Autowired
    private CodeService codeService;


    /**
     * @return
     * @Description 返回树状list
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-13
     * @Time 22:53
     **/
    @RequestMapping(value = "treeCode.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public List<TreeEntity> treeCode(Integer lv, Integer id) throws Exception {
        if (lv == null) {
            return codeService.selectTreeCode(null);
        }
        return codeService.selectTreeCode(id);
    }


    /**
     * @return
     * @Description 查询list
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-13
     * @Time 22:53
     **/
    @RequestMapping(value = "list.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public PageBean<SysCodeDetail> list(SysCodeDetail sysCodeDetail) throws Exception {
        PageBean<SysCodeDetail> pageEntity = new PageBean<SysCodeDetail>();
        pageEntity.setParameter(sysCodeDetail);
        return codeService.list(pageEntity);
    }

    /**
     * @return
     * @Description 更新初始化
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-13
     * @Time 22:53
     **/
    @RequestMapping(value = "update/index.ahsj", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView updateInit(Long id, String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/sys/code/update");
        if (!EmptyUtil.Companion.isNullOrEmpty(id)) {
            modelAndView.addObject("code", codeService.updateInit(id));
        } else {
            modelAndView.addObject("code", new SysCode());
        }
        modelAndView.addObject("token", token);
        return modelAndView;
    }


    /**
     * @return
     * @Description 新增或者更新
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-13
     * @Time 22:53
     **/
    @RequestMapping(value = "saveOrUpdate.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message saveOrUpdate(SysCode sysCode) throws Exception {
        Message message = codeService.saveOrUpdate(sysCode);
      /*  List<Translate> list = new ArrayList<>();
        TranslateModel model = new TranslateModel();
        Translate translate = new Translate();
        Long userId = getId();
        Long aLong = Long.valueOf(userId);
        translate.setTranslateChina(sysCode.getName());
        translate.setTranslateId(sysCode.getId());
        translate.setTranslateType(Constants.TRANSLATE_SYS_CODE);
        translate.setTranslateColumn("name");
        list.add(translate);
        model.setUserId(aLong);
        model.setTranslateList(list);
        iTranslateService.addTranslate(model);*/
        return message;
    }


    /**
     * @return
     * @Description 删除
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-13
     * @Time 22:53
     **/
    @RequestMapping(value = "delete.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message delete(Long[] id) throws Exception {
        return codeService.delete(id);
    }


    /**
     * @return
     * @Description 更新初始化
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-13
     * @Time 22:53
     **/
    @RequestMapping(value = "updateInitDetail/index.ahsj", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView updateInitDetail(Long id, Integer typeId, String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        if (!EmptyUtil.Companion.isNullOrEmpty(id)) {
            modelAndView.addObject("sysCodeDetail", codeService.updateInitDetail(id));
        } else {
            SysCodeDetail sysCodeDetail = new SysCodeDetail();
            sysCodeDetail.setTypeId(typeId);
            modelAndView.addObject("sysCodeDetail", sysCodeDetail);
        }
        modelAndView.addObject("token", token);
        modelAndView.setViewName("backend/sys/code/code_detail_update");
        return modelAndView;
    }


    /**
     * @return
     * @Description 新增或者更新
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-13
     * @Time 22:53
     **/
    @RequestMapping(value = "saveOrUpdateDetail.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message saveOrUpdateDetail(SysCodeDetail sysCodeDetail) throws Exception {
        return codeService.saveOrUpdateDetail(sysCodeDetail);
    }


    /**
     * @return
     * @Description 删除
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-13
     * @Time 22:53
     **/
    @RequestMapping(value = "deleteDetail.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message deleteDetail(Long[] id) throws Exception {
        return codeService.deleteDetail(id);
    }


    @RequestMapping("/index/index.ahsj")
    ModelAndView codeIndex(String token) {
        ModelAndView modelAndView = new ModelAndView("backend/sys/code/list");
        modelAndView.addObject("userName", getUserName());
        modelAndView.addObject("title", "医疗信息系统");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    @RequestMapping("/inner/lstSysCodeDetail.ahsj")
    List<SysCodeDetail> lstSysCodeDetail() throws Exception {
        return codeService.lstSysCodeDetail();
    }

    @PostMapping("/inner/lstSysCodeDetail/type.ahsj")
    public SysCodeDetail SysCodeDetail(@RequestBody SysCodeDetail sysCodeDetail) {
        SysCodeDetail detail = codeService.SysCodeDetail(sysCodeDetail);
        return detail;
    }


    /**
     * @return java.util.List<HisMedicineInfo>
     * @功能说明
     * @Params [hisMedicineInfo]
     * @Author XJP
     * @Date 2019/8/9
     * @Time 12:35
     **/
    @RequestMapping(value = "queryTranslateInfoByDate/sysCodeDetail.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public List<SysCodeDetail> queryTranslateInfoByDates(@RequestBody SysCodeDetail sysCodeDetail) throws Exception {
        return codeService.queryTranslateInfoByDates(sysCodeDetail);
    }

    /**
     * @return java.util.List<com.ahsj.userinfor.entity.SysCode>
     * @功能说明
     * @Params [sysCode]
     * @Author XJP
     * @Date 2019/8/9
     * @Time 12:50
     **/
    @RequestMapping(value = "queryTranslateInfoByDate/sysCode.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public List<SysCode> queryTranslateInfoByDate(@RequestBody SysCode sysCode) throws Exception {
        return codeService.queryTranslateInfoByDate(sysCode);
    }

    /**
     * @return java.util.List<com.ahsj.userinfor.entity.SysCode>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 14:48
     **/
    @RequestMapping(value = "queryTranslateInfoByDate/getSysCodeAll.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public List<SysCode> getSysCodeAll() throws Exception {
        List<SysCode> sysCodeList = codeService.getSysCodeAll();
        return sysCodeList;
    }

    /**
     * @return java.util.List<com.ahsj.userinfor.entity.SysCode>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/27
     * @Time 15:00
     **/
    @RequestMapping(value = "queryTranslateInfoByDate/getSysCodeName.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public SysCodeDetail getSysCodeName(@RequestBody SysCodeDetail sysCodeDetail) throws Exception {
        sysCodeDetail = codeService.getSysCodeName(sysCodeDetail);
        return sysCodeDetail;
    }

    /**
     * @param sysCodeDetail
     * @Description 根据字段，值查询是否存在
     * @Author: dingli
     * @return: com.ahsj.userinfor.entity.SysCodeDetail
     * @Date 2019/8/30
     * @Time 16:15
     **/
    @RequestMapping(value = "queryTranslateInfoByDate/selectSysCode.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public SysCodeDetail selectSysCode(@RequestBody SysCodeDetail sysCodeDetail) throws Exception {
        sysCodeDetail = codeService.selectSysCode(sysCodeDetail);
        return sysCodeDetail;
    }


}
