package com.ahsj.smartparkcore.services.impl;

import com.ahsj.smartparkcore.common.Constants;
import com.ahsj.smartparkcore.core.CodeHelper;
import com.ahsj.smartparkcore.core.ResultModel;
import com.ahsj.smartparkcore.core.ResultStatus;
import com.ahsj.smartparkcore.dao.BookMapper;
import com.ahsj.smartparkcore.dao.ConferenceRoomInfoMapper;
import com.ahsj.smartparkcore.dao.SiteMapper;
import com.ahsj.smartparkcore.dao.StationInfoMapper;
import com.ahsj.smartparkcore.entity.dto.BookDTO;
import com.ahsj.smartparkcore.entity.dto.ReserveSiteDTO;
import com.ahsj.smartparkcore.entity.dto.SiteDTO;
import com.ahsj.smartparkcore.entity.po.*;
import com.ahsj.smartparkcore.entity.sys.SysAttachmentInfo;
import com.ahsj.smartparkcore.entity.vo.BookVO;
import com.ahsj.smartparkcore.entity.vo.ConferenceRoomInfoVO;
import com.ahsj.smartparkcore.entity.vo.SiteVo;
import com.ahsj.smartparkcore.entity.vo.StationInfoVO;
import com.ahsj.smartparkcore.services.BookService;
import com.ahsj.smartparkcore.services.EnterpriseInfoService;
import com.ahsj.smartparkcore.services.SysAttachmentInfoService;
import core.entity.PageBean;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServicelmpl implements BookService {

    @Autowired
    BookMapper bookMapper;

    @Autowired
    SiteMapper siteMapper;

    @Autowired
    ConferenceRoomInfoMapper conferenceRoomInfoMapper;

    @Autowired
    StationInfoMapper stationInfoMapper;

    @Autowired
    SysAttachmentInfoService sysAttachmentInfoService;

    @Autowired
    EnterpriseInfoService enterpriseInfoService;

    private static final String filePath = Constants.FILE_PATHS;

    private static final String SUB_FILEPATH = Constants.FILE_PATHS_LOCAL;

    /**
     * @return org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Description 预定   会议室/场地/工位   预定表的新增
     * @Params [bookDTO]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 9:56
     **/
    @Override
    @Transactional(readOnly = false)
    public ResponseEntity<ResultModel> save(BookDTO bookDTO) throws Exception {

        List<Book> books = bookMapper.selectTime(bookDTO);//查询是否有时间冲突
        DozerBeanMapper mapper = new DozerBeanMapper();
        //DTO转化为PO 存入数据库
        Book book = mapper.map(bookDTO, Book.class);
        //预留 新增订单 对接付钱接口
        //订单增加后  设置状态为已付未取消
        book.setIsPay(1); //已付
        book.setIsCancel(2);//未取消
        book.setIsAudit(2);//未审核
        //预留设置支付金额
//        book.setPaymentAmount(支付接口支付金额);
        if (EmptyUtil.Companion.isNullOrEmpty(books)) {
            bookMapper.insert(book);
            return new ResponseEntity<>(new ResultModel(ResultStatus.SUCCESS_INSERT), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResultModel(ResultStatus.ERROR_INSERT, "预约时间冲突"), HttpStatus.OK);
        }
    }

    /**
     * @Description 预约分页查询
     * @Params: [pageBean]
     * @Author: dingli
     * @Return: org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Date 2019/10/14
     * @Time 11:01
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<BookDTO> list(PageBean<BookDTO> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(bookMapper.list(pageBean)));
        return pageBean;
    }

    /**
     * @Description 场地删除
     * @Params: [bookDTO]
     * @Author: dingli
     * @Return: org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Date 2019/10/14
     * @Time 11:11
     **/
    @Override
    @Transactional(readOnly = false)
    public ResponseEntity<ResultModel> delete(BookDTO bookDTO) throws Exception {
        bookMapper.deleteByPrimaryKey(bookDTO.getId());
        return new ResponseEntity<>(new ResultModel(ResultStatus.SUCCESS_DELETE), HttpStatus.OK);
    }

    /**
     * @Description 场地预约修改
     * @Params: [reserveSiteDTO]
     * @Author: dingli
     * @Return: org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Date 2019/9/12
     * @Time 9:42
     **/

    @Override
    @Transactional(readOnly = false)
    public ResponseEntity<ResultModel> update(BookDTO bookDTO) throws Exception {
        Book book = bookMapper.selectByPrimaryKey(bookDTO.getId());//查询库里的当前预定信息
        List<Book> list = bookMapper.selectTime(bookDTO);//查询是否有时间冲突
        DozerBeanMapper mapper = new DozerBeanMapper(); //对象转换组件
        Book bk = mapper.map(bookDTO, Book.class);
        if ((bookDTO.getBookType() == book.getBookType()) && (bookDTO.getTargetId() == book.getTargetId())) {//没有修改预约
            if (EmptyUtil.Companion.isNullOrEmpty(list) || (list.size() == 1 && list.get(0).getId() == book.getId())) { //活动时间不冲突，或者是跟库里本身的冲突
                bookMapper.updateByPrimaryKeySelective(bk);
                return new ResponseEntity<>(new ResultModel(ResultStatus.SUCCESS_UPDATE), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ResultModel(ResultStatus.ERROR_UPDATE, "活动时间冲突"), HttpStatus.OK);
            }
        } else { //修改了预约
            if (EmptyUtil.Companion.isNullOrEmpty(list)) {
                bookMapper.updateByPrimaryKeySelective(bk);
                return new ResponseEntity<>(new ResultModel(ResultStatus.SUCCESS_UPDATE), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ResultModel(ResultStatus.ERROR_UPDATE, "活动时间冲突"), HttpStatus.OK);
            }
        }
    }

    /**
     * @Description 查询预约
     * @Params: [id]
     * @Author: dingli
     * @Return: com.ahsj.smartparkcore.entity.po.Book
     * @Date 2019/10/14
     * @Time 14:40
     **/
    @Override
    @Transactional(readOnly = true)
    public Book selectByPrimaryKey(Long id) throws Exception {
        return CodeHelper.getInstance().setCodeValue(bookMapper.selectByPrimaryKey(id));
    }

    /**
     * @Description 获取所有的租赁信息
     * @Params: []
     * @Author: dingli
     * @Return: com.ahsj.smartparkcore.entity.dto.BookDTO
     * @Date 2019/10/21
     * @Time 15:46
     **/
    @Override
    @Transactional(readOnly = true)
    public List<BookDTO> selectAllBook() throws Exception {
        List<BookDTO> list = new ArrayList<>();
        List<Site> siteDTOS = siteMapper.selectAll();
        for (Site site : siteDTOS) {
            BookDTO bookDTO = new BookDTO();
            if (site.getDescription() == null) {
                site.setDescription("");
            }
            if (site.getPhoneNumber() == null) {
                site.setPhoneNumber("");
            }
            if (site.getSiteName() == null) {
                site.setSiteName("");
            }

            SysAttachmentInfo sysAttachmentInfo = new SysAttachmentInfo();
            sysAttachmentInfo.setRelateId(site.getId());
            sysAttachmentInfo.setRelateKey("site");
            sysAttachmentInfo.setRelatePage("list");
            List<SysAttachmentInfo> sysAttachmentInfos = sysAttachmentInfoService.querySysAttachmentInfo(sysAttachmentInfo);
            if (EmptyUtil.Companion.isNullOrEmpty(sysAttachmentInfos)){
                continue;
            }
            SysAttachmentInfo sysAttachmentInfo1 = sysAttachmentInfos.get(0);
            String replace = Constants.LOCALHOST+sysAttachmentInfo1.getLocation().replace(Constants.STATIC, Constants.SMARTPARKCORE);
            site.setFilePath(replace);



            bookDTO.setBookType(2);
            bookDTO.setBookTypeName("场地");
            bookDTO.setTargetId(site.getId());
            bookDTO.setArea(site.getArea());
            bookDTO.setCapacity(site.getCapacity());
            bookDTO.setDescription(site.getDescription());
            bookDTO.setLocation(site.getLocation());
            bookDTO.setName(site.getSiteName());
            bookDTO.setPhoneNumber(site.getPhoneNumber());
            bookDTO.setContactName(site.getName());
            bookDTO.setPrice(site.getPrice());
            bookDTO.setFilePath(site.getFilePath());
            list.add(bookDTO);
        }
        List<ConferenceRoomInfo> conferenceRoomInfoVOS = conferenceRoomInfoMapper.selectAll();
        for (ConferenceRoomInfo conferenceRoomInfoVO : conferenceRoomInfoVOS) {
          SysAttachmentInfo sysAttachmentInfo = new SysAttachmentInfo();
            sysAttachmentInfo.setRelateId(conferenceRoomInfoVO.getId());
            sysAttachmentInfo.setRelateKey("conferenceRoomInfo");
            sysAttachmentInfo.setRelatePage("list");
            List<SysAttachmentInfo> sysAttachmentInfos = sysAttachmentInfoService.querySysAttachmentInfo(sysAttachmentInfo);
            if (EmptyUtil.Companion.isNullOrEmpty(sysAttachmentInfos)){
                continue;
            }
            SysAttachmentInfo sysAttachmentInfo1 = sysAttachmentInfos.get(0);
            String replace = Constants.LOCALHOST+sysAttachmentInfo1.getLocation().replace(Constants.STATIC, Constants.SMARTPARKCORE);
            conferenceRoomInfoVO.setFilePath(replace);



            BookDTO bookDTO = new BookDTO();
            if (conferenceRoomInfoVO.getDescription() == null) {
                conferenceRoomInfoVO.setDescription("");
            }
            if (conferenceRoomInfoVO.getPhoneNumber() == null) {
                conferenceRoomInfoVO.setPhoneNumber("");
            }
            if (conferenceRoomInfoVO.getConferenceName() == null) {
                conferenceRoomInfoVO.setConferenceName("");
            }
            bookDTO.setBookType(1);
            bookDTO.setBookTypeName("会议室");
            bookDTO.setTargetId(conferenceRoomInfoVO.getId());
            bookDTO.setArea(conferenceRoomInfoVO.getArea());
            bookDTO.setCapacity(conferenceRoomInfoVO.getCapacity());
            bookDTO.setDescription(conferenceRoomInfoVO.getDescription());
            bookDTO.setLocation(conferenceRoomInfoVO.getLocation());
            bookDTO.setName(conferenceRoomInfoVO.getConferenceName());
            bookDTO.setPhoneNumber(conferenceRoomInfoVO.getPhoneNumber());
            bookDTO.setContactName(conferenceRoomInfoVO.getName());
            bookDTO.setPrice(conferenceRoomInfoVO.getPrice());
            bookDTO.setFilePath(conferenceRoomInfoVO.getFilePath());
            list.add(bookDTO);
        }
        List<StationInfo> stationInfos = stationInfoMapper.selectAll();
        for (StationInfo stationInfo : stationInfos) {
            BookDTO bookDTO = new BookDTO();
            if (stationInfo.getDescription() == null) {
                stationInfo.setDescription("");
            }
            if (stationInfo.getPhoneNumber() == null) {
                stationInfo.setPhoneNumber("");
            }
            if (stationInfo.getTitle() == null) {
                stationInfo.setTitle("");
            }

           SysAttachmentInfo sysAttachmentInfo = new SysAttachmentInfo();
            sysAttachmentInfo.setRelateId(stationInfo.getId());
            sysAttachmentInfo.setRelateKey("stationInfo");
            sysAttachmentInfo.setRelatePage("list");
            List<SysAttachmentInfo> sysAttachmentInfos = sysAttachmentInfoService.querySysAttachmentInfo(sysAttachmentInfo);
            if (EmptyUtil.Companion.isNullOrEmpty(sysAttachmentInfos)){
                continue;
            }
            SysAttachmentInfo sysAttachmentInfo1 = sysAttachmentInfos.get(0);
            String replace = Constants.LOCALHOST+sysAttachmentInfo1.getLocation().replace(Constants.STATIC, Constants.SMARTPARKCORE);
            stationInfo.setFilePath(replace);




            bookDTO.setBookType(3);
            bookDTO.setBookTypeName("工位");
            bookDTO.setTargetId(stationInfo.getId());
            bookDTO.setArea(stationInfo.getArea());
            bookDTO.setCapacity(stationInfo.getCapacity());
            bookDTO.setDescription(stationInfo.getDescription());
            bookDTO.setLocation(stationInfo.getLocation());
            bookDTO.setName(stationInfo.getTitle());
            bookDTO.setPhoneNumber(stationInfo.getPhoneNumber());
            bookDTO.setContactName(stationInfo.getName());
            bookDTO.setPrice(stationInfo.getPrice());
            bookDTO.setFilePath(stationInfo.getFilePath());
            list.add(bookDTO);
        }
        return list;
    }

    /**
     * @Description 查询租赁
     * @Params: [pageBean]
     * @Author: dingli
     * @Return: core.entity.PageBean<com.ahsj.smartparkcore.entity.dto.BookDTO>
     * @Date 2019/10/21
     * @Time 16:38
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<BookDTO> listByDate(PageBean<BookDTO> pageBean) throws Exception {
        List<Book> books = bookMapper.listByDate(pageBean);
        List<BookDTO> bs = new ArrayList<>();
        for (Book book : books) {
            if (book.getDescription() == null) {
                book.setDescription("");
            }
            if (book.getPhoneNumber() == null) {
                book.setPhoneNumber("");
            }
            if (book.getSubscriberName() == null) {
                book.setSubscriberName("");
            }
            DozerBeanMapper mapper = new DozerBeanMapper(); //对象转换组件
            BookDTO bk = mapper.map(book, BookDTO.class);
            bs.add(bk);
        }
        pageBean.setData(CodeHelper.getInstance().setCodeValue(bs));
        return pageBean;
    }

    /**
     * @Description 预约看房
     * @Params: [bookDTO]
     * @Author: dingli
     * @Return: org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Date 2019/10/23
     * @Time 15:09
     **/
    @Override
    @Transactional(readOnly = false)
    public ResponseEntity<ResultModel> visit(BookDTO bookDTO) throws Exception {
        DozerBeanMapper mapper = new DozerBeanMapper();
        //DTO转化为PO 存入数据库
        Book book = mapper.map(bookDTO, Book.class);
        //预留 新增订单 对接付钱接口
        //订单增加后  设置状态为已付未取消
        book.setIsPay(2); //已付
        book.setIsCancel(2);//未取消
        book.setIsAudit(2);//未审核
        //预留设置支付金额
//        book.setPaymentAmount(支付接口支付金额);
        bookMapper.insert(book);
        return new ResponseEntity<>(new ResultModel(ResultStatus.SUCCESS_INSERT), HttpStatus.OK);
    }
}
