package com.ahsj.hiscore.entity;

import utils.EmptyUtil;

import java.math.BigDecimal;

public class TreeHelper {
    private TreeHelper() {
    }

    public static TreeHelper getInstance() {
        return new TreeHelper();
    }
    /**
     *@Description  对树Id进行操作
     *@Params
     *@return
     *@Author chenzhicai
     *@Date 2019-04-11
     *@Time 18:16
    **/
    public String getTreeId(String treeId, String parentId) {
        //如果含有父类，但是自身id不存在，则本节点ID为父类Id+001
        if (EmptyUtil.Companion.isNullOrEmpty(treeId) && !EmptyUtil.Companion.isNullOrEmpty(parentId)) {
            return parentId + "001";
        }
        //如果父类和节点Id都存在，则本节点id为节点Id+1
        else if (!EmptyUtil.Companion.isNullOrEmpty(treeId)) {
            BigDecimal bigTreeId = new BigDecimal(treeId);
            bigTreeId = bigTreeId.add(BigDecimal.ONE);
            return bigTreeId.toPlainString();
        } else {
            throw new RuntimeException("计算不出唯一标识");
        }
    }
}
