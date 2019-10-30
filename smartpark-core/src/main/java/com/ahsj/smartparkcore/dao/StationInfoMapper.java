package com.ahsj.smartparkcore.dao;

import com.ahsj.smartparkcore.entity.dto.StationInfoDTO;
import com.ahsj.smartparkcore.entity.po.StationInfo;
import com.ahsj.smartparkcore.entity.vo.StationInfoVO;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.security.cert.X509Certificate;
import java.util.List;


@Mapper
public interface StationInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StationInfo record);

    int insertSelective(StationInfo record);

    StationInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StationInfo record);

    int updateByPrimaryKey(StationInfo record);

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.po.StationInfo>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/10/19
     * @Time 18:15
     **/
    List<StationInfoDTO> queryList(PageBean<StationInfoDTO> pageBean);

    /**
     * @return com.ahsj.smartparkcore.entity.po.StationInfo
     * @功能说明
     * @Params [title]
     * @Author XJP
     * @Date 2019/10/29
     * @Time 17:12
     **/
    StationInfo selectByTitle(String title);

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.po.StationInfo>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/10/29
     * @Time 17:12
     **/
    List<StationInfo> selectAll() throws Exception;

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.po.StationInfo>
     * @功能说明
     * @Params [ids]
     * @Author XJP
     * @Date 2019/10/29
     * @Time 15:28
     **/
    List<StationInfo> selectByIds(List<Long> ids);

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.vo.StationInfoVO>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/10/30
     * @Time 13:34
     **/
    List<StationInfoVO> listForView();


    List<StationInfo> selectAllpantent();

    StationInfo selectByStationInfo(StationInfo record);
}