package com.ahsj.wis.controller;

import com.ahsj.wis.Constants;
import com.ahsj.wis.entity.WisdomBay;
import com.ahsj.wis.entity.WisdomIndex;
import com.ahsj.wis.service.WisdomBayService;
import com.ahsj.wis.service.WisdomIndexService;
import com.ahsj.wis.utils.DeletePicture;
import core.message.Message;
import core.message.MessageUtil;
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

@RestController
@RequestMapping("/api/wisdombay")
public class WisdomBayController {

    @Autowired
    WisdomIndexService wisdomIndexService;

    @Autowired
    WisdomBayService wisdomBayService;

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
    ModelAndView intelligentIndex() {
        ModelAndView modelAndView = new ModelAndView("backend/wis/intelligent_acquisition");
        modelAndView.addObject("title", "智能采集平台");
        return modelAndView;
    }

    @RequestMapping("/smartpark/index.ahsj")
    ModelAndView smartparkIndex() {
        ModelAndView modelAndView = new ModelAndView("backend/wis/smart_park");
        modelAndView.addObject("title", "智慧园区");
        return modelAndView;
    }

    @RequestMapping("/smartparking/index.ahsj")
    ModelAndView smartparkingIndex() {
        ModelAndView modelAndView = new ModelAndView("backend/wis/smart_parking");
        modelAndView.addObject("title", "智慧停车");
        return modelAndView;
    }

    @RequestMapping("/socialcredit/index.ahsj")
    ModelAndView socialcreditIndex() {
        ModelAndView modelAndView = new ModelAndView("backend/wis/social_credit");
        modelAndView.addObject("title", "社会信用");
        return modelAndView;
    }

    @RequestMapping("/macroe/index.ahsj")
    ModelAndView macroeIndex() {
        ModelAndView modelAndView = new ModelAndView("backend/wis/macroeconomics");
        modelAndView.addObject("title", "宏观经济");
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
    ModelAndView contactusIndex() {
        ModelAndView modelAndView = new ModelAndView("backend/wis/contact_us");
        modelAndView.addObject("title", "");
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
    ModelAndView wisdomBay(String parameter) {
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
    public Message uploadFile(@RequestParam("file") MultipartFile file, String name) {
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
    ModelAndView upload(String name) {
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
    ModelAndView aboutwisdomIndex() {
        ModelAndView modelAndView = new ModelAndView("backend/wis/aboutwisdom");
        modelAndView.addObject("title", "关于智慧湾");
        return modelAndView;
    }

    @RequestMapping("/organization/index.ahsj")
    ModelAndView organization() throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/wis/organization");
        modelAndView.addObject("title", "");
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
    @RequestMapping("/selectAll.ahsj")
    public List<WisdomIndex> selectAll() throws Exception {
        return wisdomIndexService.selectAll();
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
    public Message updateIndex(WisdomIndex record) {
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


    @RequestMapping("/test.ahsj")
    ModelAndView text() throws Exception {
        ModelAndView modelAndView = new ModelAndView("public/footer");
        return modelAndView;
    }


}
