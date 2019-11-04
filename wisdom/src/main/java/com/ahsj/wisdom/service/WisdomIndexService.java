package com.ahsj.wisdom.service;

import com.ahsj.wisdom.entity.WisdomIndex;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author dingli
 * @Date 2019/11/4
 * @Time 16:08
 **/
public interface WisdomIndexService {
    /**
     * className WisdomIndexService
     *
     * @author dingli
     * @date 2019/11/4 16:08
     */
    List<WisdomIndex> selectAll() throws Exception;

    /**
     * @Description 根据name修改value
     * @Params: [record]
     * @Author: dingli
     * @Return: int
     * @Date 2019/11/4
     * @Time 16:43
     **/
    int updateByName(WisdomIndex record) throws Exception;
}
