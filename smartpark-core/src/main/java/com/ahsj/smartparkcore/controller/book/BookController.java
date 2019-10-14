package com.ahsj.smartparkcore.controller.book;

import com.ahsj.smartparkcore.core.ResultModel;
import com.ahsj.smartparkcore.core.ResultStatus;
import com.ahsj.smartparkcore.entity.dto.BookDTO;
import com.ahsj.smartparkcore.entity.dto.SiteDTO;
import com.ahsj.smartparkcore.services.BookService;
import core.controller.BaseController;
import core.entity.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

@RestController
@RequestMapping("/api/book/")
public class BookController extends BaseController {
    @Autowired
    BookService bookService;

    @Value("${file.path}")
    private String foo;

    @RequestMapping(value = "/hi")
    public String hi() {
        return foo;
    }

    /**
     * @return
     * @Description 预定   会议室/场地/工位   预定表的新增
     * @Params
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 9:46
     **/
    public ResponseEntity<ResultModel> save(BookDTO bookDTO) throws Exception {
        //判断必要信息不为空
        if (EmptyUtil.Companion.isNullOrEmpty(bookDTO.getStartTime()) || EmptyUtil.Companion.isNullOrEmpty(bookDTO.getEndTime()) ||
                EmptyUtil.Companion.isNullOrEmpty(bookDTO.getSubscriberName()) || EmptyUtil.Companion.isNullOrEmpty(bookDTO.getPhoneNumber())) {
            return new ResponseEntity<>(new ResultModel(ResultStatus.ERROR_INFO_NOTCOMPELETE), HttpStatus.OK);
        }
        return bookService.save(bookDTO);
    }

    /**
     * @Description 条件查询预约
     * @Params: [bookDTO, token]
     * @Author: dingli
     * @Return: org.springframework.http.ResponseEntity<core.entity.PageBean < com.ahsj.smartparkcore.entity.dto.BookDTO>>
     * @Date 2019/10/14
     * @Time 13:08
     **/
    @RequestMapping("/list.index.ahsj")
    @ResponseBody
    public ResponseEntity<PageBean<BookDTO>> list(BookDTO bookDTO, String token) throws Exception {
        PageBean<BookDTO> pageBean = new PageBean<>();
        pageBean.setParameter(bookDTO);
        return ResponseEntity.ok(bookService.list(pageBean));
    }

    /**
     * @Description 跳转预约
     * @Params: [token]
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/10/14
     * @Time 13:26
     **/
    @RequestMapping(value = "/list/index.ahsj")
    ModelAndView list(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/smartparkcore/book/list");
        modelAndView.addObject("title", "预约信息");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @Description 修改预订信息
     * @Params: [token, id]
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/10/14
     * @Time 15:16
     **/
    @RequestMapping(value = "/update/index.ahsj")
    ModelAndView update(String token, Long id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/smartparkcore/book/update");
        modelAndView.addObject("title", "修改预定信息");
        modelAndView.addObject("token", token);
        modelAndView.addObject("bookDTO", bookService.selectByPrimaryKey(id));
        return modelAndView;
    }

    /**
     * @Description
     * @Params: [siteDTO]
     * @Author: dingli
     * @Return: org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Date 2019/10/14
     * @Time 15:18
     **/
    @RequestMapping("/update.index.ahsj")
    @ResponseBody
    public ResponseEntity<ResultModel> update(BookDTO bookDTO) throws Exception {
        return bookService.update(bookDTO);
    }

    /**
     * @Description 跳转预约审核
     * @Params: [token, id]
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/10/14
     * @Time 17:52
     **/
    @RequestMapping(value = "/isAudit/index.ahsj")
    ModelAndView isAudit(String token, Long id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/smartparkcore/book/isAudit");
        modelAndView.addObject("title", "审核预定信息");
        modelAndView.addObject("token", token);
        modelAndView.addObject("bookDTO", bookService.selectByPrimaryKey(id));
        return modelAndView;
    }

    /**
     * @Description 预约审核
     * @Params: [bookDTO]
     * @Author: dingli
     * @Return: org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Date 2019/10/14
     * @Time 17:52
     **/
    @RequestMapping("/isAudit.index.ahsj")
    @ResponseBody
    public ResponseEntity<ResultModel> isAudit(BookDTO bookDTO) throws Exception {
        return bookService.update(bookDTO);
    }

    /**
     * @Description
     * @Params: [token, id]
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/10/14
     * @Time 17:52
     **/
    @RequestMapping(value = "/show/index.ahsj")
    ModelAndView show(String token, Long id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/smartparkcore/book/show");
        modelAndView.addObject("title", "查看预约信息");
        modelAndView.addObject("token", token);
        modelAndView.addObject("bookDTO", bookService.selectByPrimaryKey(id));
        return modelAndView;
    }
}
