package com.ahsj.hiscore.controller.typography;

import com.ahsj.hiscore.common.utils.ExcelUtils;
import com.ahsj.hiscore.common.utils.JsonUtils;
import com.ahsj.hiscore.common.utils.Result;
import com.ahsj.hiscore.common.utils.XwpfTUtil;
import com.ahsj.hiscore.entity.HisProject;
import com.ahsj.hiscore.entity.HisTypographyUserInfo;
import com.ahsj.hiscore.entity.model.HisTypographyDateModel;
import com.ahsj.hiscore.services.HisTypographyUserInfoService;
import com.alibaba.fastjson.JSON;
import core.controller.BaseController;
import core.message.Message;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisTypographyUserInfoController
 * <p>
 * Date:     2019/7/18 10:45
 *
 * @author XJP
 * @create 2019/7/18
 * @since 1.0.0
 */

@Controller
@RequestMapping("/api/excel/word")
public class HisTypographyUserInfoController extends BaseController {

    @Autowired
    HisTypographyUserInfoService hisTypographyUserInfoService;

    @Value("${file.word_name}")
    private String wordName;

    /**
     *@Description      * 导出 excel
     *@Params [hisTypographyUserInfo, response]
     *@return void
     *@Author XJP
     *@Date 2019/7/22
     *@Time 19:20
    **/
    @GetMapping("/exportExcel")
    @ResponseBody
    public void exportExcel(HisTypographyUserInfo hisTypographyUserInfo,HttpServletResponse response) throws Exception {
        List<HisTypographyUserInfo> userInfoAll = hisTypographyUserInfoService.selectHisTypographyUserInfoDateAll(hisTypographyUserInfo);
        ExcelUtils.writeExcel(response, userInfoAll, HisTypographyUserInfo.class);
    }

    /**
     *@Description      * 导入 excel
     *@Params [file]
     *@return core.message.Message
     *@Author XJP
     *@Date 2019/7/22
     *@Time 19:20
    **/
    @PostMapping("/readExcel")
    @ResponseBody
    public Message readExcel(MultipartFile file) throws Exception {
        long t1 = System.currentTimeMillis();
        List<HisTypographyUserInfo> excel = ExcelUtils.readExcel("", HisTypographyUserInfo.class, file);
//        List<HisTypographyUserInfo> excelData = (List<HisTypographyUserInfo>) map.get("dataList");
//        List<String> errorData = (List<String>) map.get("errorList");
//        List<String> headErrorList = (List<String>) map.get("headErrorList");
        // long t2 = System.currentTimeMillis();
//        System.out.println(String.format("read over! cost:%sms", (t2 - t1)));
//        for (HisTypographyUserInfo info : list) {
//            System.out.println(info.toString());
//        }
        return hisTypographyUserInfoService.saveHisTypographyUserInfos(excel);
    }





    /**
     *@Description POI导出word
     *@Params [response, hisTypographyUserInfo]
     *@return void
     *@Author XJP
     *@Date 2019/7/22
     *@Time 19:20
    **/
    @RequestMapping("/exportApplyForm.ahsj")
    @ResponseBody
    public void exportApplyForm(HttpServletResponse response ,HisTypographyUserInfo hisTypographyUserInfo) throws Exception {
        List<HisTypographyUserInfo> userInfoAll = hisTypographyUserInfoService.selectHisTypographyUserInfoDateAll(hisTypographyUserInfo);
        String name = hisTypographyUserInfo.getDepartmentName();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = hisTypographyUserInfo.getStartDate();
        String s = format.format(startDate);
        Map<String, Object> params = new HashMap<>();
        params.put("${dateTime}", s);
        params.put("${deptName}", name);
        params.put("${count}", userInfoAll.size()+"");
        XwpfTUtil xwpfTUtil = new XwpfTUtil();
        XWPFDocument doc;
        String fileNameInResource = wordName;
        InputStream is;
        is = getClass().getClassLoader().getResourceAsStream(fileNameInResource);      //本身就在编译路径下。。。。
        doc = new XWPFDocument(is);
        xwpfTUtil.replaceInPara(doc, params);
        //替换表格里面的变量
        xwpfTUtil.replaceInTable(doc, params);
        OutputStream os = response.getOutputStream();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition","attachment;filename=ahsj.docx");
        doc.write(os);
        xwpfTUtil.close(os);
        xwpfTUtil.close(is);
        os.flush();
        os.close();
    }


}
