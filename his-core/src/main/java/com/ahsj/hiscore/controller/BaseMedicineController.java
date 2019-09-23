package com.ahsj.hiscore.controller;

import com.ahsj.hiscore.core.WebContextUtil;
import com.ahsj.hiscore.entity.sys.Organization;
import core.code.Constants;
import core.controller.BaseController;

import java.util.List;

/**
 * @program: his
 * @description:
 * @author: chenzhicai
 * @create: 2019-07-05-17-10
 **/
public class BaseMedicineController extends BaseController {

    protected boolean isSameDepart(String departName) {
        List<Organization> enumData = (List<Organization>) WebContextUtil.getServletContext().getAttribute(Constants.GLOBAL_DATA_ORANGIATION);
        for (Organization o : enumData) {
            if (o.getName().equals(departName)) {
                return true;
            }
        }
        return false;
    }

    protected boolean isSameUser(Long targetId) {
        if (getId().equals( targetId)) return true;
        else return false;
    }
}

    