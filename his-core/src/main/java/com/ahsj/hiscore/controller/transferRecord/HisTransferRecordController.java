package com.ahsj.hiscore.controller.transferRecord;

import com.ahsj.hiscore.dao.HisHospitalManageMapper;
import com.ahsj.hiscore.dao.HisTransferRecordMapper;
import com.ahsj.hiscore.entity.HisTransferRecord;
import com.ahsj.hiscore.services.HisHospitalManageService;
import com.ahsj.hiscore.services.HisTransferRecordService;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


    /**
     * @Description 转科记录
     * @Author  muxu
     * @Date  2019/9/15
     * @Time 16:33
     * @Return
     * @Params
    **/
@Controller
@RequestMapping("/api/transferRecord/")
public class HisTransferRecordController extends BaseController{

        @Autowired
        HisTransferRecordService hisTransferRecordService;

        @Autowired
        HisHospitalManageService hisHospitalManageService;

        @Autowired
        HisHospitalManageMapper hisHospitalManageMapper;

        @Autowired
        HisTransferRecordMapper hisTransferRecordMapper;



        /**
         * @Description
         * @Author  muxu
         * @Date  2019/9/16
         * @Time 16:03
         * @Return core.message.Message
         * @Params [model, hisTransferRecord]
        **/
        @RequestMapping(value = "saveOrUpdate.ahsj")
        @ResponseBody
        public Message saveOrUpdate (Map<String, Object> model, HisTransferRecord hisTransferRecord) throws Exception {
            return  hisTransferRecordService.saveOrUpdate(hisTransferRecord);
        }


        /**
         * @Description
         * @Author  muxu
         * @Date  2019/9/16
         * @Time 16:03
         * @Return core.message.Message
         * @Params [model, hisTransferRecord]
        **/
        @RequestMapping(value = "InSaveOrUpdate.ahsj")
        @ResponseBody
        public Message InSaveOrUpdate (Map<String, Object> model, HisTransferRecord hisTransferRecord) throws Exception {
            return  hisTransferRecordService.InSaveOrUpdate(hisTransferRecord);
        }


        /**
         * @Description
         * @Author  muxu
         * @Date  2019/9/16
         * @Time 16:03
         * @Return core.message.Message
         * @Params [model, hisTransferRecord, userId]
        **/
        @RequestMapping(value = "sign.ahsj")
        @ResponseBody
        public Message sign (Map<String, Object> model, HttpServletRequest request
                , @RequestParam(value="userId", required=true) String userId
                , @RequestParam(value="id", required=true) Long id
                , @RequestParam(value="doctorId", required=true) Long doctorId) throws Exception {
            if (!EmptyUtil.Companion.isNullOrEmpty(id)||!EmptyUtil.Companion.isNullOrEmpty(userId)||!EmptyUtil.Companion.isNullOrEmpty(doctorId)) {
                    HisTransferRecord hisTransferRecord = hisTransferRecordService.selectById(id);
                    hisTransferRecord.setDoctorId(doctorId);
                    hisTransferRecord.setDoctorSignature(userId);
                    return hisTransferRecordService.sign(hisTransferRecord);
            }else {
                return MessageUtil.createMessage(false,"数据异常，请联系管理员！");
            }
        }


        /**
         * @Description
         * @Author  muxu
         * @Date  2019/9/16
         * @Time 16:03
         * @Return core.message.Message
         * @Params [model, request, ids]
        **/
        @RequestMapping(value = "delete.ahsj")
        @ResponseBody
        public Message delete (Map<String, Object> model, HttpServletRequest request
                , @RequestParam(value="ids", required=true) Long[] ids
        ) throws Exception {
            if(null != request.getParameter("ids")){
                return  hisTransferRecordService.delete(ids);
            }else{
                    return MessageUtil.createMessage(false,"参数异常");
            }
        }


        /**
         * @Description
         * @Author  muxu
         * @Date  2019/9/16
         * @Time 16:03
         * @Return core.entity.PageBean<com.ahsj.hiscore.entity.HisTransferRecord>
         * @Params [model, request, hisTransferRecord]
        **/
        @RequestMapping(value = "list.ahsj")
        @ResponseBody
        public PageBean<HisTransferRecord> list (Map<String, Object> model, HttpServletRequest request, HisTransferRecord hisTransferRecord) throws Exception{
            PageBean<HisTransferRecord> pageBean = new PageBean<HisTransferRecord>();
            pageBean.setParameter(hisTransferRecord);
            return hisTransferRecordService.list(pageBean);
        }

        /**
         * @Description
         * @Author  muxu
         * @Date  2019/9/16
         * @Time 16:04
         * @Return org.springframework.web.servlet.ModelAndView
         * @Params [hisTransferRecord, hosptalManageId, token]
        **/
        @RequestMapping("update/index.ahsj")
        ModelAndView UpdateIndex(HisTransferRecord hisTransferRecord,Long hosptalManageId, String token){
            ModelAndView modelAndView = new ModelAndView("backend/hiscore/transferRecord/update");
            modelAndView.addObject("title","人员信息添加");
            modelAndView.addObject("token", token);
            modelAndView.addObject("hosptalManageId",hosptalManageId);
            if (EmptyUtil.Companion.isNullOrEmpty(hisTransferRecord.getId())){
                modelAndView.addObject("hisTransferRecord",hisTransferRecord);
            }else {
                modelAndView.addObject("hisTransferRecord",hisTransferRecordMapper.selectByPrimaryKey(hisTransferRecord.getId()));
            }
            modelAndView.addObject("hospitalManage",hisHospitalManageService.selectById(hosptalManageId));
            modelAndView.addObject("doctorId",getId());
            modelAndView.addObject("departmentId",getDeptId());
            return modelAndView;
        }


        /**
         * @Description
         * @Author  muxu
         * @Date  2019/9/16
         * @Time 16:04
         * @Return org.springframework.web.servlet.ModelAndView
         * @Params [token, id]
        **/
        @RequestMapping("list/index.ahsj")
        ModelAndView listIndex(String token,Long id){
            ModelAndView modelAndView = new ModelAndView("backend/hiscore/transferRecord/list");
            modelAndView.addObject("title","医疗信息系统");
            modelAndView.addObject("token",token);
            modelAndView.addObject("hosptalManageId",id);
            return modelAndView;
        }


        /**
         * @Description
         * @Author  muxu
         * @Date  2019/9/16
         * @Time 16:04
         * @Return org.springframework.web.servlet.ModelAndView
         * @Params [token, hosptalManageId, hisTransferRecord]
        **/
        @RequestMapping("updateOut/index.ahsj")
        ModelAndView updateOut(String token,Long hosptalManageId,HisTransferRecord hisTransferRecord){
            ModelAndView modelAndView = new ModelAndView("backend/hiscore/transferRecord/update_out");
            modelAndView.addObject("title","医疗信息系统");
            modelAndView.addObject("token",token);
            modelAndView.addObject("hosptalManageId",hosptalManageId);
            if (EmptyUtil.Companion.isNullOrEmpty(hisTransferRecord.getId())){
            modelAndView.addObject("hisTransferRecord",hisTransferRecord);
            }else {
                modelAndView.addObject("hisTransferRecord",hisTransferRecordMapper.selectByPrimaryKey(hisTransferRecord.getId()));
            }
            modelAndView.addObject("hospitalManage",hisHospitalManageService.selectById(hosptalManageId));
            modelAndView.addObject("doctorId",getId());
            modelAndView.addObject("departmentId",getDeptId());
            return modelAndView;
        }

    }

