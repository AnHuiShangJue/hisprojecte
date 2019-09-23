package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.BoolMessage;
import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisNurseMapper;
import com.ahsj.hiscore.dao.HisUserNurseMapper;
import com.ahsj.hiscore.entity.HisDoctorInfo;
import com.ahsj.hiscore.entity.HisNurse;
import com.ahsj.hiscore.entity.HisUserNurse;
import com.ahsj.hiscore.entity.UserInfo;
import com.ahsj.hiscore.feign.IUserService;
import com.ahsj.hiscore.services.HisNurseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class HisNurseServiceImpl implements HisNurseService {



    @Autowired
    HisNurseMapper hisNurseMapper;
    @Autowired

    HisUserNurseMapper hisUserNurseMapper;

    @Autowired
    IUserService iUserService;

    /**
     *@Description  新增或更新
     *@Params
     *@return
     *@Author chenzhicai
     *@Date 2019-06-18
     *@Time 14:59
    **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisNurse hisNurse, HttpServletRequest request) throws Exception{
        if(!EmptyUtil.Companion.isNullOrEmpty(hisNurse.getId())){
            //check out id 是否存在
           HisNurse checkIfExit = hisNurseMapper.selectByPrimaryKey(hisNurse.getId());
            //check out 身份证和工号是否唯一
           List<HisNurse> checkForUpdate = hisNurseMapper.selectForInsert(hisNurse);
            if(EmptyUtil.Companion.isNullOrEmpty(checkIfExit)){
                return MessageUtil.createMessage(false,"更新失败!该信息不存在!");
            }
            // 如果返回俩条数据则意味着工号和身份证已经存在
            if(checkForUpdate.size() > 1){
                return MessageUtil.createMessage(false,"更新失败!所变更的身份证或工号已存在");
            }else if(checkForUpdate.size() == 1){
                //如果id相同则意味着身份证和工号唯一
                if(hisNurse.getId().equals(checkForUpdate.get(0).getId())){
                    hisNurseMapper.updateByPrimaryKey(hisNurse);
                    return MessageUtil.createMessage(true,"更新成功");
                } else{
                    return MessageUtil.createMessage(false,"更新失败!所变更的身份证或工号已存在");
                }
            }else{
                //如果唯一检查时空的则说明身份证和工号唯一
                hisNurseMapper.updateByPrimaryKey(hisNurse);
                return MessageUtil.createMessage(true,"更新成功");
            }
        }else{
            int nurseNum = hisNurseMapper.selectNumberCount()+1;
            boolean flag = false;
            String nurseNumber = "";
            while(true){
                nurseNumber = "N"+String.format("%08d", nurseNum);
                HisDoctorInfo checkNumber = hisNurseMapper.selectByNumber(nurseNumber);
                if(EmptyUtil.Companion.isNullOrEmpty(checkNumber))
                    break;
                else
                    nurseNum++;
            }
            hisNurse.setNumber(nurseNumber);
            List<HisNurse> check = hisNurseMapper.selectForInsert(hisNurse);
            if(check.size() >0){
                return MessageUtil.createMessage(false,"新增失败!身份证或工号已存在！");
            }else{
                UserInfo userInfo = new UserInfo();
                userInfo.setUserId(hisNurse.getNumber());
                userInfo.setPassword(hisNurse.getIdCard().substring(hisNurse.getIdCard().length()-6,hisNurse.getIdCard().length()));
                userInfo.setUserName(hisNurse.getName());
                userInfo.setSex(hisNurse.getSex().toString());
                userInfo.setDelFlag("1");
                userInfo.setIsInitData("1");
                userInfo.setTelPhone(hisNurse.getPhone());
                UserInfo userCheck = iUserService.selectByName(userInfo.getUserId());
                if(!EmptyUtil.Companion.isNullOrEmpty(userCheck.getId())){
                    //说明访问用户服务失败
                    if(userCheck.getId().equals(-1)){
                        return MessageUtil.createMessage(false,"新增失败!用户模块服务访问失败!请联系管理员!");
                    }
                    return MessageUtil.createMessage(false,"新增失败!"+userInfo.getUserId()+"该工号对应的账号已经存在了!请修改工号!");
                }
                BoolMessage message =  iUserService.saveOrUpdateUserInfo(userInfo);
                if(message.isSuccess()){
                    hisNurseMapper.insert(hisNurse);
                    UserInfo successUserInfo = iUserService.selectByName(userInfo.getUserId());
                    HisUserNurse hisUserNurse = new HisUserNurse();
                    hisUserNurse.setUserId(successUserInfo.getId());
                    hisUserNurse.setNurseId(hisNurse.getId());
                    hisUserNurseMapper.insert(hisUserNurse);
                    return MessageUtil.createMessage(true,"新增成功");
                }else{
                    return MessageUtil.createMessage(false,"新增失败!"+message.getMessage());
                }
            }
        }
    }


    /**
     *@Description  删除
     *@Params
     *@return
     *@Author chenzhicai
     *@Date 2019-06-18
     *@Time 14:59
     **/
    @Override
    @Transactional(readOnly = false)
    public Message delete(Long[] ids) throws Exception{
        for (Long id:ids) {
            hisNurseMapper.deleteByPrimaryKey(id);
        }
        return MessageUtil.createMessage(true,"删除成功!");
    }

    /**
     *@Description  list
     *@Params
     *@return
     *@Author chenzhicai
     *@Date 2019-06-18
     *@Time 14:59
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisNurse> list(PageBean<HisNurse> pageBean)throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisNurseMapper.list(pageBean)));
        return pageBean;
    }

    /**
     *@Description  初始化
     *@Params
     *@return
     *@Author chenzhicai
     *@Date 2019-06-18
     *@Time 14:59
     **/
    @Override
    @Transactional(readOnly = true)
    public HisNurse updateInit(Long id)throws Exception {
        return CodeHelper.getInstance().setCodeValue(hisNurseMapper.selectByPrimaryKey(id));
    }

    /**
     *@Description select
     *@Params [id]
     *@return com.ahsj.hiscore.entity.HisNurse
     *@Author chenzhicai
     *@Date 2019/6/21
     *@Time 4:08 PM
    **/
    @Override
    public HisNurse selectById(Long id) {
        return CodeHelper.getInstance().setCodeValue(hisNurseMapper.selectByPrimaryKey(id));
    }

    @Override
    public List<HisNurse> selectAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<HisNurse> hisNurses = hisNurseMapper.selectAll();
        PageInfo<HisNurse> info = new PageInfo<>(hisNurses);
        List<HisNurse> list = info.getList();
        if (!CollectionUtils.isEmpty(list)){
            return  list;
        }
        return null;
    }

    @Override
    public List<HisNurse> getHisNurseByDepartmentId(Long departmentId) {
        return hisNurseMapper.getHisNurseByDepartmentId(departmentId);
    }


   /**
    * @Description
    * @Author   muxu
    * @Date 2019/7/11
    * @Time 17:00
    * @Return
    * @Params
   **/
    @Override
    public List<HisNurse> selectNurse() {
        return hisNurseMapper.selectNurse();
    }
}
