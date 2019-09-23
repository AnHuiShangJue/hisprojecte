package com.ahsj.userinfor.services.impl;

import com.ahsj.userinfor.common.CodeHelper;
import com.ahsj.userinfor.common.Constants;
import com.ahsj.userinfor.common.WebContextUtil;
import com.ahsj.userinfor.common.utils.JsonUtils;
import com.ahsj.userinfor.controller.BaseLoginUser;
import com.ahsj.userinfor.dao.SysCodeDetailMapper;
import com.ahsj.userinfor.dao.SysCodeMapper;
import com.ahsj.userinfor.entity.SysCode;
import com.ahsj.userinfor.entity.SysCodeDetail;
import com.ahsj.userinfor.entity.TranslateModel.SysCodeDetailTranslate;
import com.ahsj.userinfor.entity.TranslateModel.SysCodeTranslate;
import com.ahsj.userinfor.entity.TranslateModel.TranslateDelete;
import com.ahsj.userinfor.entity.TranslateModel.TranslateModels;
import com.ahsj.userinfor.entity.TreeEntity;
import com.ahsj.userinfor.feign.ITranslateService;
import com.ahsj.userinfor.services.CodeService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class CodeServiceImpl implements CodeService {

    private Logger log = LoggerFactory.getLogger(CodeServiceImpl.class.getSimpleName());


    @Autowired
    private SysCodeMapper sysCodeMapper;

    @Autowired
    private SysCodeDetailMapper sysCodeDetailMapper;

    @Autowired
    ITranslateService iTranslateService;

    @Autowired
    AmqpTemplate amqpTemplat;

    private static final String AHSJ_CODE = "123456";

    /**
     * @return
     * @Description 查询字典表树
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-13
     * @Time 22:48
     **/
    @Transactional(readOnly = true)
    public List<TreeEntity> selectTreeCode(Integer id) throws Exception {
        if (id == null) {
            return sysCodeMapper.selectTreeCode(id);
        } else {
            return sysCodeDetailMapper.selectTreeCode(id);
        }

    }


    /**
     * @return
     * @Description 查询list
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-13
     * @Time 22:48
     **/
    @Transactional(readOnly = true)
    public PageBean<SysCodeDetail> list(PageBean<SysCodeDetail> pageEntity) throws Exception {
        pageEntity.setData(CodeHelper.getInstance().setCodeValue(sysCodeDetailMapper.list(pageEntity)));
        return pageEntity;
    }


    /**
     * @return
     * @Description 更新初始化
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-13
     * @Time 22:48
     **/
    @Transactional(readOnly = true)
    public SysCode updateInit(Long id) throws Exception {
        return (SysCode) sysCodeMapper.selectByPrimaryKey(id);
    }


    /**
     * @return
     * @Description 新增或者更新
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-13
     * @Time 22:48
     **/
    @Transactional(readOnly = false)
    public Message saveOrUpdate(SysCode sysCode) {
        // 如果主健为空 则为新增
        if (EmptyUtil.Companion.isNullOrEmpty(sysCode.getId())) {
            // 根据种类查询
            SysCode checkId = sysCodeMapper.selectByType(sysCode.getType());
            if (!EmptyUtil.Companion.isNullOrEmpty(checkId) && !EmptyUtil.Companion.isNullOrEmpty(checkId.getId())) {
                return MessageUtil.createMessage(false, "此种类已存在！");
            }
            // 根据名称查询
            checkId = sysCodeMapper.selectByName(sysCode.getName());
            if (!EmptyUtil.Companion.isNullOrEmpty(checkId) && !EmptyUtil.Companion.isNullOrEmpty(checkId.getId())) {
                return MessageUtil.createMessage(false, "此名称已存在！");
            }
            sysCodeMapper.insert(sysCode);
            log.info("--------------------字典模块新增翻译发送开始--------------------------");
            BaseLoginUser loginUser = new BaseLoginUser();
            TranslateModels translateModels = new TranslateModels();
            SysCodeTranslate translate = new SysCodeTranslate();
            BeanUtils.copyProperties(sysCode, translate);
            translateModels.setUserId(loginUser.getId());
            translateModels.setSysCodeTranslate(translate);
            amqpTemplat.convertAndSend("com.ahsj.addSysCodeTranslate", JsonUtils.serialize(translateModels));
            log.info(JsonUtils.serialize(translateModels));
            log.info("--------------------字典模块新增翻译发送结束--------------------------");

            amqpTemplat.convertAndSend("com.ahsj.addCode", AHSJ_CODE);
            return MessageUtil.createMessage(true, "保存成功。");
        } else {
            // 如果主健不为空 则为更新
            sysCodeMapper.update(sysCode);
            log.info("--------------------字典模块修改翻译发送开始--------------------------");
            BaseLoginUser loginUser = new BaseLoginUser();
            TranslateModels translateModels = new TranslateModels();
            SysCodeTranslate translate = new SysCodeTranslate();
            BeanUtils.copyProperties(sysCode, translate);
            translateModels.setUserId(loginUser.getId());
            translateModels.setSysCodeTranslate(translate);
            amqpTemplat.convertAndSend("com.ahsj.updateSysCodeTranslate", JsonUtils.serialize(translateModels));
            log.info(JsonUtils.serialize(translateModels));
            log.info("--------------------字典模块修改翻译发送结束--------------------------");
            amqpTemplat.convertAndSend("com.ahsj.updateCode", AHSJ_CODE);
            return MessageUtil.createMessage(true, "更新成功。");
        }
    }


    /**
     * @return
     * @Description 删除
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-13
     * @Time 22:48
     **/
    @Transactional(readOnly = false)
    public Message delete(Long[] id) throws Exception {
        for (int i = 0; i < id.length; i++) {
            sysCodeMapper.deleteByPrimaryKey(id[i]);
            log.info("--------------------字典模块删除翻译发送开始--------------------------");
            TranslateDelete translateDelete = new TranslateDelete();
            translateDelete.setId(id[i]);
            translateDelete.setModel(Constants.TRANSLATE_SYS_CODE);
            amqpTemplat.convertAndSend(Constants.TRANSLATE_HIS_DELETE, JsonUtils.serialize(translateDelete));
            log.info(JsonUtils.serialize(translateDelete));
            log.info("--------------------字典模块删除翻译发送结束--------------------------");
        }
        amqpTemplat.convertAndSend("com.ahsj.deleteCode", AHSJ_CODE);

        return MessageUtil.createMessage(true, "删除成功。");
    }


    /**
     * @return
     * @Description 更新初始化
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-13
     * @Time 22:48
     **/
    public SysCodeDetail updateInitDetail(Long id) throws Exception {
        return (SysCodeDetail) sysCodeDetailMapper.selectByPrimaryKey(id);
    }


    /**
     * @return
     * @Description 新增或者更新
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-13
     * @Time 22:48
     **/
    public Message saveOrUpdateDetail(SysCodeDetail sysCodeDetail) throws Exception {
        // 如果主健为空 则为新增
        if (EmptyUtil.Companion.isNullOrEmpty(sysCodeDetail.getId())) {
            // 根据种类查询
            SysCodeDetail check = sysCodeDetailMapper.selectByCode(sysCodeDetail);
            if (!EmptyUtil.Companion.isNullOrEmpty(check) && !EmptyUtil.Companion.isNullOrEmpty(check.getId())) {
                return MessageUtil.createMessage(false, "此种类已存在！");
            }
            // 根据名称查询
            check = sysCodeDetailMapper.selectByValue(sysCodeDetail);
            if (!EmptyUtil.Companion.isNullOrEmpty(check) && !EmptyUtil.Companion.isNullOrEmpty(check.getId())) {
                return MessageUtil.createMessage(false, "此名称已存在！");
            }
            sysCodeDetailMapper.insert(sysCodeDetail);

            log.info("--------------------字典明细模块新增翻译发送信息开始--------------------------");
            BaseLoginUser loginUser = new BaseLoginUser();
            TranslateModels translateModels = new TranslateModels();
            SysCodeDetailTranslate translate = new SysCodeDetailTranslate();
            BeanUtils.copyProperties(sysCodeDetail, translate);
            translateModels.setUserId(loginUser.getId());
            translateModels.setSysCodeDetailTranslate(translate);
            amqpTemplat.convertAndSend("com.ahsj.addSysCodeDetailTranslate", JsonUtils.serialize(translateModels));
            log.info(JsonUtils.serialize(translateModels));
            log.info("--------------------字典明细模块新增翻译发送信息结束--------------------------");
            WebContextUtil.setCodeData();
            amqpTemplat.convertAndSend("com.ahsj.addCodeDetail", AHSJ_CODE);
            return MessageUtil.createMessage(true, "保存成功。");
        } else {
            // 如果主健不为空 则为更新
            sysCodeDetailMapper.update(sysCodeDetail);
            log.info("--------------------字典明细模块修改翻译发送信息开始--------------------------");
            BaseLoginUser loginUser = new BaseLoginUser();
            TranslateModels translateModels = new TranslateModels();
            SysCodeDetailTranslate translate = new SysCodeDetailTranslate();
            BeanUtils.copyProperties(sysCodeDetail, translate);
            translateModels.setUserId(loginUser.getId());
            translateModels.setSysCodeDetailTranslate(translate);
            amqpTemplat.convertAndSend("com.ahsj.updateSysCodeDetailTranslate", JsonUtils.serialize(translateModels));
            log.info(JsonUtils.serialize(translateModels));
            log.info("--------------------字典明细模块修改翻译发送信息结束--------------------------");
            WebContextUtil.setCodeData();
            amqpTemplat.convertAndSend("com.ahsj.updateCodeDetail", AHSJ_CODE);
            return MessageUtil.createMessage(true, "更新成功。");
        }
    }

    /**
     * @return
     * @Description 删除
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-13
     * @Time 22:48
     **/
    public Message deleteDetail(Long[] id) throws Exception {
        for (int i = 0; i < id.length; i++) {
            sysCodeDetailMapper.deleteByPrimaryKey(id[i]);
            log.info("--------------------字典明细模块删除翻译发送信息开始-------------------------");
            TranslateDelete translateDelete = new TranslateDelete();
            translateDelete.setId(id[i]);
            translateDelete.setModel(Constants.TRANSLATE_SYS_CODE_DETAIL);
            amqpTemplat.convertAndSend(Constants.TRANSLATE_HIS_DELETE, JsonUtils.serialize(translateDelete));
            log.info(JsonUtils.serialize(translateDelete));
            log.info("--------------------字典明细模块删除翻译发送信息结束--------------------------");
        }
        amqpTemplat.convertAndSend("com.ahsj.deleteCodeDetail", AHSJ_CODE);
        return MessageUtil.createMessage(true, "删除成功。");
    }


    /**
     * @return
     * @Description 取列表
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-13
     * @Time 22:48
     **/
    public List<SysCodeDetail> lstSysCodeDetail() throws Exception {
        return sysCodeDetailMapper.lstAllSysCodeDetail();
    }

    @Override
    @Transactional(readOnly = true)
    public SysCodeDetail SysCodeDetail(SysCodeDetail sysCodeDetail) {
        SysCodeDetail detail = sysCodeDetailMapper.SysCodeDetail(sysCodeDetail);
        if (EmptyUtil.Companion.isNullOrEmpty(sysCodeDetail)) {
            return null;
        } else {
            return detail;
        }
    }

    /**
     * @return java.util.List<com.ahsj.userinfor.entity.SysCodeDetail>
     * @功能说明
     * @Params [sysCodeDetail]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 11:18
     **/
    @Override
    @Transactional(readOnly = true)
    public List<SysCodeDetail> queryTranslateInfoByDates(SysCodeDetail sysCodeDetail) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(sysCodeDetail)) {
            log.info("查询数据不存在 ， 无对应数据");
            return new ArrayList<>();
        } else {
            List<SysCodeDetail> translateList = sysCodeDetailMapper.queryTranslateInfoByDate(sysCodeDetail);
            if (EmptyUtil.Companion.isNullOrEmpty(translateList)) {
                log.info("查询数据不存在 ， 无对应数据");
                return new ArrayList<>();
            } else {
                return translateList;
            }
        }
    }

    /**
     * @return java.util.List<com.ahsj.userinfor.entity.SysCode>
     * @功能说明
     * @Params [sysCode]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 11:18
     **/
    @Override
    @Transactional(readOnly = true)
    public List<SysCode> queryTranslateInfoByDate(SysCode sysCode) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(sysCode)) {
            log.info("查询数据不存在 ， 无对应数据");
            return new ArrayList<>();
        } else {
            List<SysCode> translateList = sysCodeMapper.queryTranslateInfoByDate(sysCode);
            if (EmptyUtil.Companion.isNullOrEmpty(translateList)) {
                log.info("查询数据不存在 ， 无对应数据");
                return new ArrayList<>();
            } else {
                return translateList;
            }
        }
    }

    /**
     * @return java.util.List<com.ahsj.userinfor.entity.SysCode>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 14:49
     **/
    @Override
    @Transactional(readOnly = true)
    public List<SysCode> getSysCodeAll() throws Exception {
        List<SysCode> sysCodeList = sysCodeMapper.getSysCodeAll();
        if (EmptyUtil.Companion.isNullOrEmpty(sysCodeList)) {
            log.info("查询失败");
            return new ArrayList<>();
        } else {
            return sysCodeList;
        }
    }

    /**
     * @return com.ahsj.userinfor.entity.SysCodeDetail
     * @功能说明
     * @Params [sysCodeDetail]
     * @Author XJP
     * @Date 2019/8/27
     * @Time 15:02
     **/
    @Override
    @Transactional(readOnly = true)
    public SysCodeDetail getSysCodeName(SysCodeDetail sysCodeDetail) {
        sysCodeDetail = sysCodeDetailMapper.getSysCodeName(sysCodeDetail);
        if (EmptyUtil.Companion.isNullOrEmpty(sysCodeDetail)) {
            log.info("查询失败");
            return new SysCodeDetail();
        } else {
            return sysCodeDetail;
        }
    }

    /**
     * @param sysCodeDetail
     * @Description  根据字段，值查询是否存在
     * @Author: dingli
     * @return: com.ahsj.userinfor.entity.SysCodeDetail
     * @Date 2019/8/30
     * @Time 16:14
     **/
    @Override
    @Transactional(readOnly = true)
    public SysCodeDetail selectSysCode(SysCodeDetail sysCodeDetail) {
        sysCodeDetail = sysCodeDetailMapper.selectSysCode(sysCodeDetail);
        if (EmptyUtil.Companion.isNullOrEmpty(sysCodeDetail)) {
            log.info("----字典没有该字段-----");
            return new SysCodeDetail();
        } else {
            return sysCodeDetail;
        }
    }

}



