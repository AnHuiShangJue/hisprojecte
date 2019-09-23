package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisAdlBarthelMapper;
import com.ahsj.hiscore.entity.HisAdlBarthel;
import com.ahsj.hiscore.services.HisAdlBarthelService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.List;

@Service
public class HisAdlBarthelServiceImpl implements HisAdlBarthelService {

    @Autowired
    HisAdlBarthelMapper hisAdlBarthelMapper;

    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisAdlBarthel hisAdlBarthel) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisAdlBarthel)){
            return MessageUtil.createMessage(false,"保存失败");
        }else {
            hisAdlBarthelMapper.insert(hisAdlBarthel);
            return MessageUtil.createMessage(true,"保存成功");

        }

    }

    @Override
    @Transactional(readOnly = true)
    public PageBean<HisAdlBarthel> list(PageBean<HisAdlBarthel> pageBean) throws Exception {
            List<HisAdlBarthel> data = hisAdlBarthelMapper.list(pageBean);
            for (int i = 0;i<data.size();++i){
                int score = 0;

                if (data.get(i).getEat() !=null && data.get(i).getEat() != 404){
                    score = score+data.get(i).getEat();
                }
                if (data.get(i).getShower() !=null && data.get(i).getShower() != 404){
                    score = score+data.get(i).getShower();
                }
                if (data.get(i).getModification() !=null && data.get(i).getModification() != 404){
                    score = score+data.get(i).getModification();
                }
                if (data.get(i).getWear() !=null && data.get(i).getWear() != 404){
                    score = score+data.get(i).getWear();
                }
                if (data.get(i).getShit() !=null && data.get(i).getShit() != 404){
                    score = score+data.get(i).getShit();
                }
                if (data.get(i).getPee() !=null && data.get(i).getPee() != 404){
                    score = score+data.get(i).getPee();
                }
                if (data.get(i).getToilet() !=null && data.get(i).getToilet() != 404){
                    score = score+data.get(i).getToilet();
                }
                if (data.get(i).getBedsidechair() !=null && data.get(i).getBedsidechair() != 404){
                    score = score+data.get(i).getBedsidechair();
                }
                if (data.get(i).getWalk() !=null && data.get(i).getWalk() != 404){
                    score = score+data.get(i).getWalk();
                }
                if (data.get(i).getStairs() !=null && data.get(i).getStairs() != 404){
                    score = score+data.get(i).getStairs();
                }
                data.get(i).setScore(score);
            }
        pageBean.setData(CodeHelper.getInstance().setCodeValue(data));
        return pageBean;
    }

    @Override
    @Transactional(readOnly = true)
    public HisAdlBarthel selectById(Long id) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(id)){
            return new HisAdlBarthel();
        }else {
            return hisAdlBarthelMapper.selectByPrimaryKey(id);
        }
    }
}
