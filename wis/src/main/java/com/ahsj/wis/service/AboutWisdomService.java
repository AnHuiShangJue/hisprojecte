package com.ahsj.wis.service;

import com.ahsj.wis.entity.AboutWisdom;

public interface AboutWisdomService {

    int insert(AboutWisdom record)throws Exception;

    AboutWisdom select()throws Exception;

}
