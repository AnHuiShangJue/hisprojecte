package com.ahsj.hiscore.controller.WordPrint;

import com.ahsj.hiscore.common.utils.XwpfTUtil;
import com.ahsj.hiscore.entity.SurgicalRecord;
import com.ahsj.hiscore.entity.sys.Organization;
import com.ahsj.hiscore.feign.IOrganizationService;
import com.ahsj.hiscore.services.HisBedService;
import com.ahsj.hiscore.services.SurgicalRecordService;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: consultationPrintController
 * <p>
 * Date:     2019/9/18 13:18
 *
 * @author XJP
 * @create 2019/9/18
 * @since 1.0.0
 */

@RestController
@RequestMapping("/api/consultation")
public class ConsultationPrintController {


    @Autowired
    SurgicalRecordService surgicalRecordService;

    @Autowired
    IOrganizationService iOrganizationService;

    @Autowired
    HisBedService hisBedService;

    @Value("${file.word_name}")
    private String wordName;


    /**
     *@Description POI导出word
     *@Params [response, hisTypographyUserInfo]
     *@return void
     *@Author XJP
     *@Date 2019/7/22
     *@Time 19:20
     **/
    @RequestMapping("/word.ahsj")
    public void exportApplyForm(HttpServletResponse response, SurgicalRecord surgicalRecord) throws Exception {

        surgicalRecord = surgicalRecordService.selectByPrimaryKey(surgicalRecord.getId());
        Organization organization = iOrganizationService.getOrganizationById(surgicalRecord.getDepartment().longValue());
        Map<String, Object> params = new HashMap<>();
        params.put("${departName}", organization.getName());//departName
        params.put("${number}", surgicalRecord.getMedicalNumber());
        XwpfTUtil xwpfTUtil = new XwpfTUtil();
        XWPFDocument doc;
        String fileNameInResource = wordName;// Constants.HIS_SYS_WORD_CONSUMABLES_CH_FILE_URL;  templates/word/export/consultation.docx
        fileNameInResource = this.getClass().getClassLoader().getResource("templates/word/export/ahsj.docx").getPath();
        InputStream is = null;
        File file = new File(fileNameInResource);
        is = new FileInputStream(file);
        //  is = getClass().getResourceAsStream(fileNameInResource);      //本身就在编译路径下。。。。
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
