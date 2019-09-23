package com.ahsj.hiscore.fallback;

import com.ahsj.hiscore.entity.sys.SysCode;
import com.ahsj.hiscore.entity.sys.SysCodeDetail;
import com.ahsj.hiscore.feign.ICodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class ICodeServiceFallback implements ICodeService {

    Logger logger = LoggerFactory.getLogger(ICodeServiceFallback.class.getSimpleName());

    @Override
    public List<SysCodeDetail> lstSysCodeDetail() throws Exception {
        logger.error("服务获取异常,不能够访问字典服务");
        return new ArrayList<SysCodeDetail>();
    }

    /**
     * @return com.ahsj.hiscore.entity.sys.SysCodeDetail
     * @功能说明
     * @Params [sysCodeDetail]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 14:54
     **/
    @Override
    public SysCodeDetail SysCodeDetail(SysCodeDetail sysCodeDetail) throws Exception {
        logger.error("服务获取异常,不能够访问字典服务");
        return new SysCodeDetail();
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.sys.SysCodeDetail>
     * @功能说明
     * @Params [sysCodeDetail]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 14:54
     **/
    @Override
    public List<SysCodeDetail> queryTranslateInfoByDate(SysCodeDetail sysCodeDetail) throws Exception {
        logger.error("服务获取异常,不能够访问字典服务");
        return new ArrayList<>();
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.sys.SysCode>
     * @功能说明
     * @Params [sysCode]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 14:54
     **/
    @Override
    public List<SysCode> queryTranslateInfoByDate(SysCode sysCode) throws Exception {
        logger.error("服务获取异常,不能够访问字典服务");
        return new ArrayList<>();
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.sys.SysCode>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 14:53
     **/
    @Override
    public List<SysCode> getSysCodeAll() throws Exception {
        logger.error("服务获取异常,不能够访问字典服务");
        return new ArrayList<>();
    }

    @Override
    public SysCodeDetail getSysCodeName(SysCodeDetail sysCodeDetail) throws Exception {
        logger.error("服务获取异常,不能够访问字典服务");
        return new SysCodeDetail();
    }

    /**
     * @param sysCodeDetail
     * @Description 根据字段，值查询字典是否存在
     * @Author: dingli
     * @return: com.ahsj.hiscore.entity.sys.SysCodeDetail
     * @Date 2019/8/30
     * @Time 16:18
     **/
    @Override
    public SysCodeDetail selectSysCode(SysCodeDetail sysCodeDetail) throws Exception {
        logger.error("服务获取异常,不能够访问字典服务");
        return new SysCodeDetail();
    }
}
