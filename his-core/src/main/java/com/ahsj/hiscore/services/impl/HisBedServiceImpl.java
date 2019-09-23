package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisBedMapper;
import com.ahsj.hiscore.dao.HisWardMapper;
import com.ahsj.hiscore.entity.HisBed;
import com.ahsj.hiscore.entity.HisWard;
import com.ahsj.hiscore.services.HisBedService;
import com.ahsj.hiscore.services.HisWradService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;
import java.util.Date;
import java.util.List;

@Service
public class HisBedServiceImpl implements HisBedService {

    @Autowired
    HisBedMapper hisBedMapper;

    @Autowired
    HisWardMapper hisWardMapper;

    @Autowired
    HisWradService hisWradService;

    /**
     * @param hisBed
     * @return
     * @Description 新增或修改病床
     * @Author: dingli
     * @Date 2019/7/13
     * @Time 14:59
     **/
    @Transactional(readOnly = false)
    @Override
    public Message saveOrUpdate(HisBed hisBed) throws Exception {
        HisBed bed = hisBedMapper.getBed(hisBed);
        if (EmptyUtil.Companion.isNullOrEmpty(hisBed.getId())) {
            // 如果主健为空 则为新增
            if (EmptyUtil.Companion.isNullOrEmpty(bed)) {//当前病床下是否有该病床号
                int i = hisBedMapper.countBedByWardId(hisBed.getWardId());//当前病床总数
                HisWard hisWard = hisWardMapper.selectByPrimaryKey(hisBed.getWardId());//总病床数
                int count = hisWard.getCount();
                if (count > i) {
                    hisBedMapper.insert(hisBed);//增加对象
                    hisWard.setCurrentCount(count - i - 1);
                    hisWradService.updateByPrimaryKeySelective(hisWard);//更新当前剩余量
                    return MessageUtil.createMessage(true, "添加病床成功!");
                } else {
                    return MessageUtil.createMessage(false, "新增失败,病床已满！");
                }
            } else {
                return MessageUtil.createMessage(false, "新增失败," + hisBed.getNumber() + "号病床已经存在！");
            }
        }
        else {//主键不为空  修改
            if (!EmptyUtil.Companion.isNullOrEmpty(bed) && bed.getId() != hisBed.getId()) {//当前病床下是否有该病床号
                return MessageUtil.createMessage(false, "更新失败," + hisBed.getNumber() + "号病床已经存在！");
            }
            else {
                hisBed.setUpdateDate(new Date());
                hisBedMapper.updateByPrimaryKeySelective(hisBed);
                return MessageUtil.createMessage(true, "更新成功!");
            }
        }
    }

    @Transactional(readOnly = true)
    @Override
    /**
     *@Description 分页
     * @param pageBean
     *@Author: dingli
     *@return
     *@Date 2019/7/13
     *@Time 15:11
     **/
    public PageBean<HisBed> getHisBedAll(PageBean<HisBed> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisBedMapper.getHisBedAll(pageBean)));
        return pageBean;
    }


    /**
     * @param wardId
     * @return
     * @Description 根据病房id获取所有的病床
     * @Author: dingli
     * @Date 2019/7/13
     * @Time 15:12
     **/
    @Transactional(readOnly = true)
    @Override
    public List<HisBed> getHisBed(Long wardId) throws Exception {
        return hisBedMapper.getHisBed(wardId);
    }

    /**
     * @param id
     * @return
     * @Description 根据id查询所有的病床
     * @Author: dingli
     * @Date 2019/7/13
     * @Time 15:12
     **/
    @Transactional(readOnly = true)
    @Override
    public HisBed selectByPrimaryKey(Long id) throws Exception {
        return CodeHelper.getInstance().setCodeValue(hisBedMapper.selectByPrimaryKey(id));
    }

    /**
     * @return core.message.Message
     * @Description 删除病床
     * @Params [id]
     * @Author dingli
     * @Date 2019-07-06
     * @Time 9;25
     **/
    @Override
    @Transactional(readOnly = false)
    public Message deleteByPrimaryKey(Long[] ids) throws Exception {
        int a = 0;//删除初始化;
        HisBed hisBed = hisBedMapper.selectByPrimaryKey(ids[0]);
        if (!EmptyUtil.Companion.isNullOrEmpty(hisBed)) {
            HisWard hisWard = hisWardMapper.selectByPrimaryKey(hisBed.getWardId());//当前病床
            int CurrentCount = hisWard.getCurrentCount();//剩余病床数
            for (Long id : ids) {
                hisBedMapper.deleteByPrimaryKey(id);
                a = a + 1;//删除总数
            }
            hisWard.setCurrentCount(CurrentCount + a);//删除一个增加一个
            hisWardMapper.updateByPrimaryKey(hisWard);//更新当前剩余量
            return MessageUtil.createMessage(true, "删除成功!");
        }
        return MessageUtil.createMessage(false, "删除失败!");
    }

    /**
     * @param id
     * @return
     * @Description 根据id删除病床
     * @Author: dingli
     * @Date 2019/7/13
     * @Time 15:13
     **/

    @Transactional(readOnly = false)
    @Override
    public int deleteByPrimaryKey(Long id) throws Exception {
        return hisBedMapper.deleteByPrimaryKey(id);
    }


    /**
     * @param wardId
     * @return
     * @Description 根据病房id删除病床
     * @Author: dingli
     * @Date 2019/7/13
     * @Time 15:14
     **/
    @Transactional(readOnly = false)
    @Override
    public int deleteByWardId(Long wardId) throws Exception {
        return hisBedMapper.deleteByWardId(wardId);
    }

    /**
     * @param wardId
     * @Description
     * @Author: dingli
     * @return: int
     * @Date 2019/8/13
     * @Time 18:02
     **/
    @Override
    @Transactional(readOnly = true)
    public int countBedByWardId(Long wardId) throws Exception {
        return hisBedMapper.countBedByWardId(wardId);
    }

    /**
     * @param
     * @Description 获取所有病床数
     * @Author: dingli
     * @return: int
     * @Date 2019/8/13
     * @Time 18:01
     **/
    @Override
    @Transactional(readOnly = true)
    public int countBed() throws Exception {
        return hisBedMapper.countBed();
    }

    /**
     * @param
     * @Description 查询所有的病床
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisBed>
     * @Date 2019/8/17
     * @Time 16:35
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisBed> selectHisBedAll() throws Exception {
        return CodeHelper.getInstance().setCodeValue(hisBedMapper.selectHisBedAll());
    }

    /**
     * @return core.message.Message
     * @Description 修改病床启用状态
     * @Params [id]
     * @Author dingli
     * @Date 2019-07-06
     * @Time 15;25
     **/
    @Override
    @Transactional(readOnly = false)
    public Message setEnable(Long[] ids) throws Exception {
        if (!EmptyUtil.Companion.isNullOrEmpty(ids)) {
            for (Long id : ids) {
                HisBed hisBed = hisBedMapper.selectByPrimaryKey(id);
                if (!EmptyUtil.Companion.isNullOrEmpty(hisBed)) {
                    if (hisBed.getIsEnable() == 1) {
                        hisBed.setIsEnable(2);
                    } else {
                        hisBed.setIsEnable(1);
                    }
                    hisBedMapper.updateByPrimaryKeySelective(hisBed);
                }
            }
            return MessageUtil.createMessage(true, "是否启用状态修改成功!");
        } else {
            return MessageUtil.createMessage(false, "是否启用状态修改失败!");
        }
    }


}
