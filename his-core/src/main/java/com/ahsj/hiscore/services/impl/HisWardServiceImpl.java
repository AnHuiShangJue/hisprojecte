package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.common.excel.JxlsUtil;
import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisWardMapper;
import com.ahsj.hiscore.entity.HisWard;
import com.ahsj.hiscore.entity.Translate;
import com.ahsj.hiscore.entity.sys.SysCodeDetail;
import com.ahsj.hiscore.feign.ICodeService;
import com.ahsj.hiscore.feign.ITranslateService;
import com.ahsj.hiscore.services.HisBedService;
import com.ahsj.hiscore.services.HisImportInfoService;
import com.ahsj.hiscore.services.HisWradService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HisWardServiceImpl implements HisWradService {

    @Autowired
    HisWardMapper hisWardMapper;

    @Autowired
    HisBedService hisBedService;

    @Autowired
    ICodeService iCodeService;

    @Autowired
    AmqpTemplate amqpTemplat;

    @Autowired
    ITranslateService iTranslateService;

    @Autowired
    HisImportInfoService hisImportInfoService;

    private Logger logger = LoggerFactory.getLogger(HisWardServiceImpl.class.getSimpleName());

    /**
     * @param pageBean
     * @return
     * @Description
     * @Author: dingli
     * @Date 2019/7/13
     * @Time 15:29
     **/
    @Transactional(readOnly = true)
    @Override
    public PageBean<HisWard> getHisWardAll(PageBean<HisWard> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisWardMapper.getHisWardAll(pageBean)));
        return pageBean;
    }

    /**
     * @return core.message.Message
     * @Description 新增或更新
     * @Params [hisWard]
     * @Author dingli
     * @Date 2019-07-04
     * @Time 11;20
     **/
    @Transactional(readOnly = false)
    @Override
    public Message saveOrUpdate(HisWard hisWard) throws Exception {
        // HisWard hisWard1 = hisWardMapper.gethisWardByNumber(hisWard.getNumber());
        HisWard hisWard1 = hisWardMapper.selectByPrimaryKey(hisWard.getId());
        if (EmptyUtil.Companion.isNullOrEmpty(hisWard.getId())) {
            // 如果主健为空 则为新增
            if (EmptyUtil.Companion.isNullOrEmpty(hisWard1)) {
                hisWardMapper.insert(hisWard);//增加对象
                return MessageUtil.createMessage(true, "添加病房成功!");
            } else {
                return MessageUtil.createMessage(false, "新增失败," + hisWard.getNumber() + "号病房已经存在！");
            }
        } else {//主键不为空  修改
            if (!EmptyUtil.Companion.isNullOrEmpty(hisWard1) && hisWard1.getId() != hisWard.getId()) {//该病房号存在并id不一致
                //主键不存在
                return MessageUtil.createMessage(false, "更新失败," + hisWard.getNumber() + "号病房已经存在！");
            } else {
                if (hisWard.getCount() != hisWard1.getCount()) {//修改了总床数
                    int i = hisBedService.countBedByWardId(hisWard.getId());//当前病床总数
                    int count = hisWard.getCount();//修改的总病床数
                    if (count < i) {
                        return MessageUtil.createMessage(false, "更新失败,病床总数不能小于当前病床数" + i);
                    } else {
                        hisWard.setCurrentCount(count - i);
                    }
                }
                hisWard.setUpdateDate(new Date());
                hisWardMapper.updateByPrimaryKeySelective(hisWard);
                return MessageUtil.createMessage(true, "更新成功!");
            }

        }

    }

    /**
     * @return core.message.Message
     * @Description 删除病房
     * @Params [id]
     * @Author dingli
     * @Date 2019-07-04
     * @Time 17;08
     **/
    @Override
    @Transactional(readOnly = false)
    public Message deleteByPrimaryKey(Long[] ids) throws Exception {
        for (Long id : ids) {
            hisWardMapper.deleteByPrimaryKey(id);
            if (!EmptyUtil.Companion.isNullOrEmpty(hisBedService.getHisBed(id))) {//如果病房下面有病床就删除  id 是wardId;
                hisBedService.deleteByWardId(id);//根据wardId 删除该wardId下的所有的病床
            }
        }
        return MessageUtil.createMessage(true, "删除成功!");
    }

    @Override
    @Transactional(readOnly = true)
    /**
     *@Description
     * @param id
     *@Author: dingli
     *@return
     *@Date 2019/7/13
     *@Time 15:31
     **/
    public HisWard selectByPrimaryKey(Long id) throws Exception {
        // CodeHelper.getInstance().setCodeValue  字典反射框架
        return CodeHelper.getInstance().setCodeValue(hisWardMapper.selectByPrimaryKey(id));
    }

    /**
     * @return core.message.Message
     * @Description 设置病床启用状态
     * @Params [ids]
     * @Author dingli
     * @Date 2019-07-05
     * @Time 14:35
     **/
    @Override
    @Transactional(readOnly = false)
    public Message setEnable(Long[] ids) throws Exception {
        for (Long id : ids) {
            HisWard hisWard = hisWardMapper.selectByPrimaryKey(id);
            if (hisWard.getIsEnable() == 1)
                hisWard.setIsEnable(2);
            else {
                hisWard.setIsEnable(1);
            }
            hisWardMapper.updateByPrimaryKeySelective(hisWard);
        }
        return MessageUtil.createMessage(true, "是否启用状态修改成功!");
    }

    @Transactional(readOnly = true)
    @Override
    /**
     *@Description
     * @param number
     *@Author: dingli
     *@return
     *@Date 2019/7/13
     *@Time 15:33
     **/
    public HisWard gethisWardByNumber(Integer number) throws Exception {

        return CodeHelper.getInstance().setCodeValue(hisWardMapper.gethisWardByNumber(number));
    }

    @Transactional(readOnly = true)
    @Override
    /**
     *@Description
     * @param id
     *@Author: dingli
     *@return
     *@Date 2019/7/13
     *@Time 15:33
     **/
    public String getUserName(Long id) throws Exception {
        return hisWardMapper.getUserName(id);
    }

    @Transactional(readOnly = true)
    @Override
    /**
     *@Description
     * @param
     *@Author: dingli
     *@return
     *@Date 2019/7/13
     *@Time 15:34
     **/
    public List<HisWard> selectHisWard() throws Exception {
        return hisWardMapper.selectHisWard();
    }

    @Transactional(readOnly = false)
    @Override
    /**
     *@Description
     * @param record
     *@Author: dingli
     *@return
     *@Date 2019/7/13
     *@Time 15:34
     **/
    public int updateByPrimaryKey(HisWard record) throws Exception {
        return hisWardMapper.updateByPrimaryKey(record);
    }

    @Transactional(readOnly = false)
    @Override
    /**
     *@Description
     * @param record
     *@Author: dingli
     *@return
     *@Date 2019/7/13
     *@Time 15:38
     **/
    public int updateByPrimaryKeySelective(HisWard record) throws Exception {
        return hisWardMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * @param hisWard
     * @param request
     * @param response
     * @param session
     * @Description 导出Excel
     * @Author: dingli
     * @return: void
     * @Date 2019/8/28
     * @Time 18:53
     **/
    @Override
    @Transactional(readOnly = true)
    public void exportExcels(Long[] ids, HisWard hisWard, String param, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        HashMap<String, Object> beans = new HashMap<String, Object>();
        String psth = null;
        List<HisWard> hisWardAll = null;
        if (EmptyUtil.Companion.isNullOrEmpty(ids)) {
            hisWardAll = CodeHelper.getInstance().setCodeValue(hisWardMapper.getHisWardList(hisWard)); //优先根据id导出
            logger.info("------------根据条件导出病房表------------");
        } else {
            hisWardAll = CodeHelper.getInstance().setCodeValue(hisWardMapper.selectByIds(ids));
            logger.info("------------根据id导出病房表------------");
        }
        if (StringUtils.equals(param, Constants.HIS_CH)) {//中文导出
         //   psth = this.getClass().getClassLoader().getResource("templates/excel/export/HisWard_CH.xlsx").getPath();  //目标路径
            psth = Constants.HIS_SYS_EXCEL_HISWARD_CH_FILE_URL;
            logger.info("------------导出病房表中文版------------");
        }
        if(StringUtils.equals(param, Constants.HIS_EN)){
            logger.info("------------导出病房表英文版------------");
        }
        if (StringUtils.equals(param, Constants.HIS_KM)) {//高棉语导出
          //  psth = this.getClass().getClassLoader().getResource("templates/excel/export/HisWard_KM.xlsx").getPath();  //目标路径
            psth = Constants.HIS_SYS_EXCEL_HISWARD_KM_FILE_URL;
            for (HisWard ward : hisWardAll) {
                Translate translate = new Translate();
                translate.setTranslateId(ward.getTypeId());
                translate.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                List<Translate> translates = iTranslateService.queryTranslate(translate);
                translates.stream().filter(f -> f.getTranslateChina().equals(ward.getRoomtype())).forEach(e -> ward.setRoomtype(e.getTranslateKhmer()));
                Translate translate1 = new Translate();
                translate1.setTranslateId(ward.getEnableId());
                translate1.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                List<Translate> translates1 = iTranslateService.queryTranslate(translate1);
                translates1.stream().filter(f -> f.getTranslateChina().equals(ward.getEnable())).forEach(e -> ward.setEnable(e.getTranslateKhmer()));
            }
            logger.info("------------导出病房表高棉语版------------");
        }
        beans.put("hisWardAll", hisWardAll);
        if (StringUtils.equals(param, Constants.HIS_CH)) {
            JxlsUtil.export(request, response, psth, "病房信息表", beans);
        }
        if (StringUtils.equals(param, Constants.HIS_KM)) {//高棉语导出
            JxlsUtil.export(request, response, psth, "សំណុំបែបបទព័ត៌មានវួដ។", beans);
        }
        return;
    }

    /**
     * @param successList
     * @Description 导入excel
     * @Author: dingli
     * @return: java.util.Map
     * @Date 2019/8/29
     * @Time 13:29
     **/
    @Override
    public Map importExcel(List<HisWard> successList, String filename) throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        if (!EmptyUtil.Companion.isNullOrEmpty(successList) && successList.size() > 0) {
            for (int i = 0; i < successList.size(); i++) {
                logger.info("---------正在导入的病房编号"+successList.get(i).getNumber()+"------------");
                if (successList.get(i).getCount() < successList.get(i).getCurrentCount()) {
                    successList.get(i).setCurrentCount(successList.get(i).getCount());//如果总床数小于当前床数，令当前床数等于总床数
                }
                SysCodeDetail detail = new SysCodeDetail();
                detail.setValue(successList.get(i).getRoomtype());
                String code = iCodeService.getSysCodeName(detail).getCode();
                //对应的字典字段
                successList.get(i).setType(Integer.parseInt(code));
                SysCodeDetail details = new SysCodeDetail();
                details.setValue(successList.get(i).getEnable());
                String codes = iCodeService.getSysCodeName(details).getCode();
                successList.get(i).setIsEnable(Integer.parseInt(codes));
            }
            hisWardMapper.importHisWard(successList);//批量插入
        }
        map.put("success",successList.size());  //成功的条数
        return map;
    }


}
