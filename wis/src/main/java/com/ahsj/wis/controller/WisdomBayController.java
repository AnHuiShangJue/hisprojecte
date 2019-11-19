package com.ahsj.wis.controller;

import com.ahsj.wis.Constants;
import com.ahsj.wis.entity.*;
import com.ahsj.wis.service.*;
import com.ahsj.wis.entity.WisdomIndex;
import com.ahsj.wis.service.WisdomIndexService;
import com.ahsj.wis.utils.DeletePicture;
import core.message.Message;
import core.message.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Api(value = "/api/wisdombay", tags = "智慧湾")
@RestController
@RequestMapping("/api/wisdombay")
public class WisdomBayController {

    @Autowired
    WisdomIndexService wisdomIndexService;

    @Autowired
    WisdomBayService wisdomBayService;

    @Autowired
    OrganizationalStructureService organizationalStructureService;

    @Autowired
    IntelligentService intelligentService;

    @Autowired
    macroeService macroeService;

    @Autowired
    SmartparkingService smartparkingService;

    @Autowired
    SmartparkService smartparkService;

    @Autowired
    SocialcreditService socialcreditService;

    @Autowired
    ContactUsUpdateService contactUsUpdateService;

    @Autowired
    AboutWisdomService aboutWisdomService;

    @ApiOperation(value = "进入智慧湾", notes = "进入智慧湾")
    @RequestMapping("/list/index.ahsj")
    ModelAndView listIndex() throws Exception {
        List<WisdomBay> wisdomBays = wisdomBayService.selectAll();
        WisdomBay wisdomBay = wisdomBays.get(0);
        ModelAndView modelAndView = new ModelAndView("backend/wis/wisdom_bay");
        modelAndView.addObject("title", "人工智能实验室");
        modelAndView.addObject("wisdomBay", wisdomBay);
        return modelAndView;
    }

    @RequestMapping("/intelligent/index.ahsj")
    ModelAndView intelligentIndex() throws Exception {
        List<Intelligent> intelligents = intelligentService.selectAll();
        Intelligent intelligent = intelligents.get(0);
        ModelAndView modelAndView = new ModelAndView("backend/wis/intelligent_acquisition");
        modelAndView.addObject("title", "智能采集平台");
        modelAndView.addObject("intelligent", intelligent);
        return modelAndView;
    }

    @RequestMapping("/smartpark/index.ahsj")
    ModelAndView smartparkIndex() throws Exception {
        List<Smartpark> smartparks = smartparkService.selectAll();
        Smartpark smartpark = smartparks.get(0);
        ModelAndView modelAndView = new ModelAndView("backend/wis/smart_park");
        modelAndView.addObject("smartpark", "智慧园区");
        modelAndView.addObject("smartpark", smartpark);
        return modelAndView;
    }

    @RequestMapping("/smartparking/index.ahsj")
    ModelAndView smartparkingIndex() throws Exception {
        List<Smartparking> smartparkings = smartparkingService.selectAll();
        Smartparking smartparking = smartparkings.get(0);
        ModelAndView modelAndView = new ModelAndView("backend/wis/smart_parking");
        modelAndView.addObject("title", "智慧停车");
        modelAndView.addObject("smartparking", smartparking);
        return modelAndView;
    }

    @RequestMapping("/socialcredit/index.ahsj")
    ModelAndView socialcreditIndex() throws Exception {
        List<Socialcredit> socialcredits = socialcreditService.selectAll();
        Socialcredit socialcredit = socialcredits.get(0);
        ModelAndView modelAndView = new ModelAndView("backend/wis/social_credit");
        modelAndView.addObject("title", "社会信用");
        modelAndView.addObject("socialcredit", socialcredit);
        return modelAndView;
    }

    @RequestMapping("/macroe/index.ahsj")
    ModelAndView macroeIndex() throws Exception {
        List<macroe> macroes1 = macroeService.selectAll();
        macroe macroe2 = macroes1.get(0);
        ModelAndView modelAndView = new ModelAndView("backend/wis/macroeconomics");
        modelAndView.addObject("title", "宏观经济");
        modelAndView.addObject("macroe", macroe2);
        return modelAndView;
    }

    /**
     * @Description 欢迎页
     * @Params: []
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/11/5
     * @Time 14:18
     **/
    @RequestMapping("/lanhu/index.ahsj")
    ModelAndView lanhuIndex() throws Exception {
        List<WisdomIndex> wisdomIndices = wisdomIndexService.selectAll();
        WisdomIndex wisdomIndex = wisdomIndices.get(0);
        ModelAndView modelAndView = new ModelAndView("backend/wis/index");
        modelAndView.addObject("title", "欢迎页");
        modelAndView.addObject("wisdomIndex", wisdomIndex);
        return modelAndView;
    }

    /**
     * @return
     * @Description 跳转至联系我们页面
     * @Params
     * @Author jin
     * @Date 2019/11/4
     * @Time 11:43
     */
    @RequestMapping("/contactus/index.ahsj")
    ModelAndView contactusIndex() throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/wis/contact_us");
        modelAndView.addObject("title", "联系我们");
        ContactUs contactUs = contactUsUpdateService.select();
        modelAndView.addObject("contactUs", contactUs);
        return modelAndView;
    }

    /**
     * @Description
     * @Params: [parameter]
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/11/4
     * @Time 9:41
     **/
    @RequestMapping("/wisdomBay/index.ahsj")
    ModelAndView wisdomBay(String parameter) throws Exception{
        ModelAndView modelAndView = new ModelAndView("backend/wisdom/wisdom_bay");
        modelAndView.addObject("title", "");
        modelAndView.addObject("parameter", parameter);
        return modelAndView;
    }

    /**
     * @Description 修改图片
     * @Params: `
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/11/4
     * @Time 10:44
     **/
    @PostMapping("/uploadFile.ahsj")
    public Message uploadFile(@RequestParam("file") MultipartFile file, String name) throws Exception{
        String fileName = file.getOriginalFilename();//获取文件名加后缀
        if (file.getSize() > 10 * 1024 * 1024) {
            return MessageUtil.createMessage(false, "修改失败,文件太大，请上传小于10M的图片");
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(fileName) && !EmptyUtil.Companion.isNullOrEmpty(name)) {
            String returnUrl = Constants.ImagePath;//存储路径
            try {
                DeletePicture.deletePicture(name); //删除原图片
                //将上传的文件写到服务器上指定的文件。
                FileOutputStream imgOut = new FileOutputStream(new File(returnUrl, name));//根据 dir 抽象路径名和 img 路径名字符串创建一个新 File 实例。
                imgOut.write(file.getBytes());//返回一个字节数组文件的内容
                imgOut.close();
                return MessageUtil.createMessage(true, "修改成功!");
            } catch (Exception e) {
                e.printStackTrace();
                return MessageUtil.createMessage(false, "上传失败!");
            }
        }
        return null;
    }

    /**
     * @Description 修改图片
     * @Params: [name]
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/11/4
     * @Time 11:25
     **/
    @RequestMapping("/wisdomBay/upload.ahsj")
    ModelAndView upload(String name) throws Exception{
        ModelAndView modelAndView = new ModelAndView("backend/wisdom/upload");
        modelAndView.addObject("title", "");
        modelAndView.addObject("name", name);
        modelAndView.addObject("title", "联系我们");
        return modelAndView;
    }

    /**
     * @return
     * @Description 跳转关于智慧湾页面
     * @Params
     * @Author jin
     * @Date 2019/11/4
     * @Time 11:41
     */
    @RequestMapping("/aboutwisdom/index.ahsj")
    ModelAndView aboutwisdomIndex() throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/wis/aboutwisdom");
        modelAndView.addObject("title", "关于智慧湾");
        AboutWisdom aboutWisdom = aboutWisdomService.select();
        modelAndView.addObject("aboutWisdom", aboutWisdom);
        return modelAndView;
    }

    @RequestMapping("/organization/index.ahsj")
    ModelAndView organization() throws Exception {
        List<OrganizationalStructure> organizationalStructures = organizationalStructureService.selectAll();
        OrganizationalStructure organizationalStructure = organizationalStructures.get(0);
        ModelAndView modelAndView = new ModelAndView("backend/wis/organization");
        modelAndView.addObject("title", "组织架构");
        modelAndView.addObject("organizationalStructure", organizationalStructure);
        return modelAndView;
    }

    /**
     * @Description 获取所有欢迎页的内容
     * @Params: []
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.wisdom.entity.WisdomIndex>
     * @Date 2019/11/4
     * @Time 16:16
     **/
    @ApiOperation(value = "获取所有欢迎页的内容", notes = "获取所有欢迎页的内容")
    @RequestMapping("/selectAll.ahsj")
    public List<WisdomIndex> selectAll() throws Exception {
        return wisdomIndexService.selectAll();
    }

    /**
     * @Description 获取所有人工智能实验室信息
     * @Author muxu
     * @Date 2019/11/15
     * @Time 15:31
     * @Return java.util.List<com.ahsj.wis.entity.WisdomBay>
     * @Params []
     **/
    @RequestMapping("/selectAllBay.ahsj")
    public List<WisdomBay> selectAllBay() throws Exception {
        return wisdomBayService.selectAll();
    }


    /**
     * @Description 更新人工智能实验室信息ModelAndView
     * @Author muxu
     * @Date 2019/11/15
     * @Time 16:12
     * @Return org.springframework.web.servlet.ModelAndView
     * @Params []
     **/
    @RequestMapping("/wisdomBayUpdate/index.ahsj")
    ModelAndView wisdomBayIndexUpdate() throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/wisdom/wisdom_bay_update");
        List<WisdomBay> wisdomBays = wisdomBayService.selectAll();
        if (EmptyUtil.Companion.isNullOrEmpty(wisdomBays)){
            WisdomBay wisdomBay = new WisdomBay();
            modelAndView.addObject("wisdomBay",wisdomBay);
        }else {
            WisdomBay wisdomBay = wisdomBays.get(0);
            modelAndView.addObject("wisdomBay", wisdomBay);
        }
        modelAndView.addObject("title", "人工智能实验室");
        return modelAndView;
    }

    @RequestMapping("/intelligentUpdate/index.ahsj")
    ModelAndView intelligentUpdate() throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/wisdom/intelligent_acquisition_update");
        List<Intelligent> intelligents = intelligentService.selectAll();
        if (EmptyUtil.Companion.isNullOrEmpty(intelligents)){
            Intelligent intelligent = new Intelligent();
            modelAndView.addObject("intelligent",intelligent);
        }else {
            Intelligent intelligent = intelligents.get(0);
            modelAndView.addObject("intelligent", intelligent);
        }
        modelAndView.addObject("title", "智能采集平台");
        return modelAndView;
    }


    @RequestMapping("/macroeUpdate/index.ahsj")
    ModelAndView macroeUpdate() throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/wisdom/macroeconomics_update");
        List<macroe> macroes1 = macroeService.selectAll();
        if(EmptyUtil.Companion.isNullOrEmpty(macroes1)){
            macroe macroe3 = new macroe();
            modelAndView.addObject("macroe", macroe3);
        }else {
            macroe macroe2 = macroes1.get(0);
            modelAndView.addObject("macroe", macroe2);
        }
        modelAndView.addObject("title", "宏观经济");
        return modelAndView;
    }


    @RequestMapping("/smartparkingUpdate/index.ahsj")
    ModelAndView smartparkingUpdate() throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/wisdom/smartparking_update");
        List<Smartparking> smartparkings = smartparkingService.selectAll();
        if (EmptyUtil.Companion.isNullOrEmpty(smartparkings)){
            Smartparking smartparking = new Smartparking();
            modelAndView.addObject("smartparking", smartparking);
        }else {
            Smartparking smartparking = smartparkings.get(0);
            modelAndView.addObject("smartparking", smartparking);
        }
        modelAndView.addObject("title", "智慧停车");
        return modelAndView;
    }

    @RequestMapping("/smartparkUpdate/index.ahsj")
    ModelAndView smartparkUpdate() throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/wisdom/smartpark_update");
        List<Smartpark> smartparks = smartparkService.selectAll();
        if (EmptyUtil.Companion.isNullOrEmpty(smartparks)){
            Smartpark smartpark = new Smartpark();
            modelAndView.addObject("smartpark", smartpark);
        }else {
            Smartpark smartpark = smartparks.get(0);
            modelAndView.addObject("smartpark", smartpark);
        }
        modelAndView.addObject("title", "智慧园区");
        return modelAndView;
    }

    @RequestMapping("/socialcreditUpdate/index.ahsj")
    ModelAndView socialcreditUpdate() throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/wisdom/social_credit_update");
        List<Socialcredit> socialcredits = socialcreditService.selectAll();
        if (EmptyUtil.Companion.isNullOrEmpty(socialcredits)){
            Socialcredit socialcredit = new Socialcredit();
            modelAndView.addObject("socialcredit", socialcredit);
        }else {
            Socialcredit socialcredit = socialcredits.get(0);
            modelAndView.addObject("socialcredit", socialcredit);
        }
        modelAndView.addObject("title", "社会信用");
        return modelAndView;
    }

    /**
     * @Description 更新组织架构信息ModelAndView
     * @Author muxu
     * @Date 2019/11/15
     * @Time 16:12
     * @Return org.springframework.web.servlet.ModelAndView
     * @Params []
     **/
    @RequestMapping("/organizationUpdate/index.ahsj")
    ModelAndView organizationIndexUpdate() throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/wisdom/organizational_structure_update");
        List<OrganizationalStructure> organizationalStructures = organizationalStructureService.selectAll();
        if (EmptyUtil.Companion.isNullOrEmpty(organizationalStructures)){
            OrganizationalStructure organizationalStructure = new OrganizationalStructure();
            modelAndView.addObject("organizationalStructure", organizationalStructure);
        }else {
            OrganizationalStructure organizationalStructure = organizationalStructures.get(0);
            modelAndView.addObject("organizationalStructure", organizationalStructure);
        }
        modelAndView.addObject("title", "组织架构修改");
        return modelAndView;
    }


    @RequestMapping("/lanhuUpdate/index.ahsj")
    ModelAndView lanhuIndexUpdate() throws Exception {
        List<WisdomIndex> wisdomIndices = wisdomIndexService.selectAll();
        WisdomIndex wisdomIndex = wisdomIndices.get(0);
        ModelAndView modelAndView = new ModelAndView("backend/wisdom/index_update");
        modelAndView.addObject("title", "欢迎页");
        modelAndView.addObject("wisdomIndex", wisdomIndex);
        return modelAndView;
    }

    /**
     * @Description 修改智慧湾首页
     * @Params: [record]
     * @Author: dingli
     * @Return: void
     * @Date 2019/11/6
     * @Time 10:24
     **/
    @RequestMapping("/toUpdateIndex.ahsj")
    public Message updateIndex(WisdomIndex record)throws Exception {
        return wisdomIndexService.updateByPrimaryKeySelective(record);
    }

    /**
     * @Description 更新人工智能实验室信息
     * @Author muxu
     * @Date 2019/11/15
     * @Time 15:46
     * @Return org.springframework.web.servlet.ModelAndView
     * @Params []
     **/
    @RequestMapping("/updateWisdomBayIndex.ahsj")
    public Message updateWisdomBayIndex(WisdomBay record) {
        return wisdomBayService.updateWisdomBay(record);
    }


    @RequestMapping("/updateIntelligentIndex.ahsj")
    public Message updateIntelligentIndex(Intelligent record) {
        return intelligentService.updateIntelligent(record);
    }

    @RequestMapping("/UpdatemacroeIndex.ahsj")
    public Message UpdatemacroeIndex(macroe record) {
        return macroeService.updateMacroe(record);
    }


    @RequestMapping("/updatesmartparkingIndex.ahsj")
    public Message updatesmartparkingIndex(Smartparking record) {
        return smartparkingService.updateSmartparking(record);
    }


    @RequestMapping("/updatesmartparkIndex.ahsj")
    public Message updatesmartparkIndex(Smartpark record) {
        return smartparkService.updateSmartpark(record);
    }

    @RequestMapping("/updatesocialcreditIndex.ahsj")
    public Message updatesocialcreditIndex(Socialcredit record) {
        return socialcreditService.updateSocialcredit(record);
    }


    @RequestMapping("/updateOrganizationIndex.ahsj")
    public Message updateOrganizationIndex(OrganizationalStructure record) {
        return organizationalStructureService.updateOrganization(record);
    }

    @RequestMapping("/test.ahsj")
    ModelAndView text() throws Exception {
        ModelAndView modelAndView = new ModelAndView("public/footer");
        return modelAndView;
    }
}
