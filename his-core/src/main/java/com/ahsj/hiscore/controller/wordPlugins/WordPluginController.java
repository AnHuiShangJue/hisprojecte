package com.ahsj.hiscore.controller.wordPlugins;

import com.ahsj.hiscore.controller.BaseOAController;
import com.ahsj.hiscore.entity.MedicalRecordWord;
import com.ahsj.hiscore.entity.NursingRecordTemplate;
import com.ahsj.hiscore.entity.NursingRecordWord;
import com.ahsj.hiscore.entity.TemplateMedicalRecord;
import com.ahsj.hiscore.services.MedicalRecordWordService;
import com.ahsj.hiscore.services.NursingRecordTemplateService;
import com.ahsj.hiscore.services.NursingRecordWordService;
import com.ahsj.hiscore.services.TemplateMedialReocSerivce;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @program: hisprojecte
 * @description:
 * @author: chenzhicai
 * @create: 2019-09-19-15-59
 **/
@Controller
@RequestMapping("/api/wordplugin/")
public class WordPluginController extends BaseOAController {

    @Autowired
    MedicalRecordWordService medicalRecordWordService;

    @Autowired
    TemplateMedialReocSerivce templateMedialReocSerivce;

    @Autowired
    NursingRecordTemplateService nursingRecordTemplateService;

    @Autowired
    NursingRecordWordService nursingRecordWordService;

    String FILE_DIR = "G:/his/his-core/build/resources/main/static/assets/export/";
//    String FILE_DIR = "/Users/chenzhicai/springcloudprojects/hisprojecte/his-core/src/main/resources/static/assets/export/";

    String NUR_FILE_DIR = "G:/his/his-core/build/resources/main/static/assets/nurserecord/";
//    String NUR_FILE_DIR = "/Users/chenzhicai/springcloudprojects/hisprojecte/his-core/src/main/resources/static/assets/nurserecord/";


    @RequestMapping("index.ahsj")
    private ModelAndView Index(String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/wordplugin/index");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    @RequestMapping("edit/index.ahsj")
    private ModelAndView editIndex(String id, String token,String hospitalNumber,Integer type) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/wordplugin/edit");
        modelAndView.addObject("token", token);
        //1对应的是模版，其余对应的是病历
        if(type.equals(1) ){
           TemplateMedicalRecord templateMedicalRecord= templateMedialReocSerivce.selectById(new Long(id));
            modelAndView.addObject("fileName", templateMedicalRecord.getFileName());
            modelAndView.addObject("filePath", templateMedicalRecord.getFilePath());
        }else{
           MedicalRecordWord medicalRecordWord= medicalRecordWordService.selectById(new Long(id));
            modelAndView.addObject("fileName", medicalRecordWord.getFileName());
            modelAndView.addObject("filePath", medicalRecordWord.getFilePath());
        }
        modelAndView.addObject("id",id);
        modelAndView.addObject("type",type);
        modelAndView.addObject("hospitalNumber", hospitalNumber);
        return modelAndView;
    }

    @RequestMapping("detailMedical/index.ahsj")
    private ModelAndView detailMedicalIndex(String id, String token,String hospitalNumber,Integer type) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/wordplugin/detail_medical");
        modelAndView.addObject("token", token);
        //1对应的是模版，其余对应的是病历
        if(type.equals(1) ){
            TemplateMedicalRecord templateMedicalRecord= templateMedialReocSerivce.selectById(new Long(id));
            modelAndView.addObject("fileName", templateMedicalRecord.getFileName());
            modelAndView.addObject("filePath", templateMedicalRecord.getFilePath());
        }else{
            MedicalRecordWord medicalRecordWord= medicalRecordWordService.selectById(new Long(id));
            modelAndView.addObject("fileName", medicalRecordWord.getFileName());
            modelAndView.addObject("filePath", medicalRecordWord.getFilePath());
        }
        modelAndView.addObject("id",id);
        modelAndView.addObject("type",type);
        modelAndView.addObject("hospitalNumber", hospitalNumber);
        return modelAndView;
    }

    @RequestMapping("editNurse/index.ahsj")
    private ModelAndView editNurseIndex(String id, String token,String hospitalNumber,Integer type) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/wordplugin/edit_nurse");
        modelAndView.addObject("token", token);
        //1对应的是模版，其余对应的是病历
        if(type.equals(1) ){
            NursingRecordTemplate templateMedicalRecord= nursingRecordTemplateService.selectById(new Long(id));
            modelAndView.addObject("fileName", templateMedicalRecord.getFileName());
            modelAndView.addObject("filePath", templateMedicalRecord.getFilePath());
        }else{
            NursingRecordWord nursingRecordWord= nursingRecordWordService.selectById(new Long(id));
            modelAndView.addObject("fileName", nursingRecordWord.getFileName());
            modelAndView.addObject("filePath", nursingRecordWord.getFilePath());
        }
        modelAndView.addObject("id",id);
        modelAndView.addObject("type",type);
        modelAndView.addObject("hospitalNumber", hospitalNumber);
        return modelAndView;
    }

    @RequestMapping("detailNurse/index.ahsj")
    private ModelAndView detailNurseIndex(String id, String token,String hospitalNumber,Integer type) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/wordplugin/detail_nurse");
        modelAndView.addObject("token", token);
        //1对应的是模版，其余对应的是病历
        if(type.equals(1) ){
            NursingRecordTemplate templateMedicalRecord= nursingRecordTemplateService.selectById(new Long(id));
            modelAndView.addObject("fileName", templateMedicalRecord.getFileName());
            modelAndView.addObject("filePath", templateMedicalRecord.getFilePath());
        }else{
            NursingRecordWord nursingRecordWord= nursingRecordWordService.selectById(new Long(id));
            modelAndView.addObject("fileName", nursingRecordWord.getFileName());
            modelAndView.addObject("filePath", nursingRecordWord.getFilePath());
        }
        modelAndView.addObject("id",id);
        modelAndView.addObject("type",type);
        modelAndView.addObject("hospitalNumber", hospitalNumber);
        return modelAndView;
    }


    /**
     *@Description 上传测试模版
     *@Params [file, fileName]
     *@return core.message.Message
     *@Author chenzhicai
     *@Date 2019-09-20
     *@Time 01:14
    **/
    @RequestMapping("uploadWord/index.ahsj")
    @ResponseBody
    private Message uploadWord(@RequestParam("file") MultipartFile file,
                               @RequestParam("fileName") String fileName) {

        if (file.isEmpty()) {
            return MessageUtil.createMessage(false, "文件是空的");
        }
        try {
            byte[] bytes = file.getBytes();
            File tmp = new File(FILE_DIR +"12313");
            if(!tmp.exists()) {
                if (!tmp.mkdir()) {
                    return MessageUtil.createMessage(true, "病人文件夹创建失败，请联系管理员");
                }
            }
            File file1 =new File(FILE_DIR +"12313/"+ fileName);
            if(!file1.exists()) {
                if (!file1.createNewFile()) {
                    return MessageUtil.createMessage(true, fileName+"创建失败，请联系管理员");
                }
            }
            Path path = Paths.get(FILE_DIR +"12313/"+ fileName);
            Files.write(path, bytes);
            return MessageUtil.createMessage(true, "文件上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            return MessageUtil.createMessage(false, "服务器异常");
        }
    }


    /**
     * @return core.message.Message
     * @Description 保存病历记录书
     * @Params [file, fileName, hospitalNumber]
     * @Author chenzhicai
     * @Date 2019-09-19
     * @Time 23:52
     **/
    @RequestMapping(value = "saveWordMedical/index.ahsj")
    @ResponseBody
    private Message saveWordMedical(@RequestParam("file") MultipartFile file,
                                    @RequestParam("fileName") String fileName,
                                    @RequestParam("hospitalNumber") String hospitalNumber,
                                    @RequestParam(value = "id",required = false) Long id,
                                    @RequestParam(value="type",required = true) Long type) {

        if (file.isEmpty()) {
            return MessageUtil.createMessage(false, "文件是空的");
        }
        fileName  = fileName.substring(0,fileName.indexOf(",")-1);
        try {
            byte[] bytes = file.getBytes();
            File tmp = new File(FILE_DIR +hospitalNumber);
            if(!tmp.exists()) {
                if (!tmp.mkdir()) {
                    return MessageUtil.createMessage(true, "病人文件夹创建失败，请联系管理员");
                }
            }
            File file1 =new File(FILE_DIR +hospitalNumber+"/"+ fileName);
            if(!file1.exists()) {
                if (!file1.createNewFile()) {
                    return MessageUtil.createMessage(true, fileName+"创建失败，请联系管理员");
                }
            }
            Path path = Paths.get(FILE_DIR +hospitalNumber+"/"+ fileName);
            Files.write(path, bytes);
            MedicalRecordWord medicalRecordWord = new MedicalRecordWord();
            medicalRecordWord.setFileName(fileName);
            medicalRecordWord.setFilePath(hospitalNumber+"/"+ fileName);
            medicalRecordWord.setHospitalNumber(hospitalNumber);
            if(!EmptyUtil.Companion.isNullOrEmpty(id)){
                medicalRecordWord.setId(id);
            }
            medicalRecordWordService.saveOrUpdate(medicalRecordWord,type);
            return MessageUtil.createMessage(true, fileName+"病历保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return MessageUtil.createMessage(false, "服务器异常");
        }
    }

    /**
     * @return core.message.Message
     * @Description 保存病历模版
     * @Params [file, fileName, type]
     * @Author chenzhicai
     * @Date 2019-09-19
     * @Time 23:55
     **/
    @RequestMapping(value ="saveMedicalTemplate/index.ahsj")
    @ResponseBody
    private Message saveMedicalTemplate(@RequestParam("file") MultipartFile file,
                                        @RequestParam("fileName") String fileName,
                                        @RequestParam("type") Integer type,
                                        @RequestParam(value = "id",required = false) Long id) {
        TemplateMedicalRecord templateMedicalRecord = new TemplateMedicalRecord();
        templateMedicalRecord.setFileName(fileName);
        try {
            URLDecoder.decode(fileName,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Long userId = getId();
        if(!EmptyUtil.Companion.isNullOrEmpty(id)){
            templateMedicalRecord.setId(id);
        }
        if (file.isEmpty()) {
            return MessageUtil.createMessage(false, "文件是空的");
        }
        try {
            byte[] bytes = file.getBytes();
            String SavePath;
            switch (type) {
                //保存到公用模版
                case 2:
                    File tmp = new File(FILE_DIR +"commonTempelates");
                    if(!tmp.exists()) {
                        if (!tmp.mkdir()) {
                            return MessageUtil.createMessage(true, "通用文件夹文件夹创建失败，请联系管理员");
                        }
                    }
                    SavePath = FILE_DIR + "commonTempelates/" + fileName;
                    templateMedicalRecord.setFilePath("commonTempelates/" + fileName);
                    templateMedicalRecord.setOwnerId(new Long(-1));
                    break;
                //保存到当前模版
                case 1:
                    File tmp1 = new File(FILE_DIR +userId);
                    if(!tmp1.exists()) {
                        if (!tmp1.mkdir()) {
                            return MessageUtil.createMessage(true, "专属文件夹创建失败，请联系管理员");
                        }
                    }
                    SavePath = FILE_DIR + userId + "/" + fileName;
                    templateMedicalRecord.setFilePath(userId + "/" + fileName);
                    templateMedicalRecord.setOwnerId(new Long(userId));
                    break;
                    //默认保存到当前模版
                default:
                    File tmp2 = new File(FILE_DIR +userId);
                    if(!tmp2.exists()) {
                        if (!tmp2.mkdir()) {
                            return MessageUtil.createMessage(true, "专属文件夹创建失败，请联系管理员");
                        }
                    }
                    SavePath = FILE_DIR + userId + "/" + fileName;
                    templateMedicalRecord.setFilePath(userId + "/" + fileName);
                    templateMedicalRecord.setOwnerId(new Long(userId));
                    break;
            }
            File file1 =new File(SavePath);
            if(!file1.exists()) {
                if (!file1.createNewFile()) {
                    return MessageUtil.createMessage(true, fileName+"创建失败，请联系管理员");
                }
            }
            Path path = Paths.get(SavePath);
            Files.write(path, bytes);
            templateMedialReocSerivce.saveOrUpdate(templateMedicalRecord);
            return MessageUtil.createMessage(true, fileName+"模版保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return MessageUtil.createMessage(false, "服务器异常");
        }
    }


    /**
     * @return
     * @Description 下载文件
     * @Params
     * @Author chenzhicai
     * @Date 2018/11/6
     * @Time 5:43 PM
     **/
    @RequestMapping("download/index.ahsj")
    private void download(String fileName, HttpServletResponse response) {

//            val dbFile = filesMapper.selectByPrimaryKey(id)
        //通过文件的保存文件夹路径加上文件的名字来获得文件
        File file = new File(FILE_DIR, fileName);


        //当文件存在
        if (file.exists()) {
            //首先设置响应的内容格式是force-download，那么你一旦点击下载按钮就会自动下载文件了
            response.setContentType("application/force-download");
            //通过设置头信息给文件命名，也即是，在前端，文件流被接受完还原成原文件的时候会以你传递的文件名来命名
            response.addHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
            //进行读写操作
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                //从源文件中读
                int i = bis.read(buffer);
                while (i != -1) {
                    //写到response的输出流中
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //善后工作，关闭各种流
                try {
                    bis.close();
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * @return
     * @Description 下载文件
     * @Params
     * @Author chenzhicai
     * @Date 2018/11/6
     * @Time 5:43 PM
     **/
    @RequestMapping("downloadNC/index.ahsj")
    private void downloadNC(String fileName, HttpServletResponse response) {

//            val dbFile = filesMapper.selectByPrimaryKey(id)
        //通过文件的保存文件夹路径加上文件的名字来获得文件
        File file = new File(NUR_FILE_DIR, fileName);


        //当文件存在
        if (file.exists()) {
            //首先设置响应的内容格式是force-download，那么你一旦点击下载按钮就会自动下载文件了
            response.setContentType("application/force-download");
            //通过设置头信息给文件命名，也即是，在前端，文件流被接受完还原成原文件的时候会以你传递的文件名来命名
            response.addHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
            //进行读写操作
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                //从源文件中读
                int i = bis.read(buffer);
                while (i != -1) {
                    //写到response的输出流中
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //善后工作，关闭各种流
                try {
                    bis.close();
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @return core.message.Message
     * @Description 保存护理记录书
     * @Params [file, fileName, hospitalNumber]
     * @Author chenzhicai
     * @Date 2019-09-19
     * @Time 23:52
     **/
    @RequestMapping(value = "saveWordNurse/index.ahsj")
    @ResponseBody
    private Message saveWordNurse(@RequestParam("file") MultipartFile file,
                                    @RequestParam("fileName") String fileName,
                                    @RequestParam("hospitalNumber") String hospitalNumber,
                                    @RequestParam(value = "id",required = false) Long id) {

        if (file.isEmpty()) {
            return MessageUtil.createMessage(false, "文件是空的");
        }
        fileName  = fileName.substring(0,fileName.indexOf(",")-1);
        try {
            byte[] bytes = file.getBytes();
            File tmp = new File(NUR_FILE_DIR +hospitalNumber);
            if(!tmp.exists()) {
                if (!tmp.mkdir()) {
                    return MessageUtil.createMessage(true, "病人文件夹创建失败，请联系管理员");
                }
            }
            File file1 =new File(NUR_FILE_DIR +hospitalNumber+"/"+ fileName);
            if(!file1.exists()) {
                if (!file1.createNewFile()) {
                    return MessageUtil.createMessage(true, fileName+"创建失败，请联系管理员");
                }
            }
            Path path = Paths.get(NUR_FILE_DIR +hospitalNumber+"/"+ fileName);
            Files.write(path, bytes);
            NursingRecordWord medicalRecordWord = new NursingRecordWord();
            medicalRecordWord.setFileName(fileName);
            medicalRecordWord.setFilePath(hospitalNumber+"/"+ fileName);
            medicalRecordWord.setHospitalNumber(hospitalNumber);
            if(!EmptyUtil.Companion.isNullOrEmpty(id)){
                medicalRecordWord.setId(id);
            }
            nursingRecordWordService.saveOrUpdate(medicalRecordWord);
            return MessageUtil.createMessage(true, fileName+"护理记录保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return MessageUtil.createMessage(false, "服务器异常");
        }
    }

    /**
     * @return core.message.Message
     * @Description 保存护理记录模版
     * @Params [file, fileName, type]
     * @Author chenzhicai
     * @Date 2019-09-19
     * @Time 23:55
     **/
    @RequestMapping(value ="saveNurseTemplate/index.ahsj")
    @ResponseBody
    private Message saveNurseTemplate(@RequestParam("file") MultipartFile file,
                                        @RequestParam("fileName") String fileName,
                                        @RequestParam("type") Integer type,
                                        @RequestParam(value = "id",required = false) Long id) {
        NursingRecordTemplate templateMedicalRecord = new NursingRecordTemplate();
        templateMedicalRecord.setFileName(fileName);
        try {
            URLDecoder.decode(fileName,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Long userId = getId();
        if(!EmptyUtil.Companion.isNullOrEmpty(id)){
            templateMedicalRecord.setId(id);
        }
        if (file.isEmpty()) {
            return MessageUtil.createMessage(false, "文件是空的");
        }
        try {
            byte[] bytes = file.getBytes();
            String SavePath;
            File tmp = new File(NUR_FILE_DIR +"commonTempelates");
            if(!tmp.exists()) {
                if (!tmp.mkdir()) {
                    return MessageUtil.createMessage(true, "通用文件夹文件夹创建失败，请联系管理员");
                }
            }
            SavePath = NUR_FILE_DIR + "commonTempelates/" + fileName;
            templateMedicalRecord.setFilePath("commonTempelates/" + fileName);
            templateMedicalRecord.setOwnerId(new Long(-1));

            File file1 =new File(SavePath);
            if(!file1.exists()) {
                if (!file1.createNewFile()) {
                    return MessageUtil.createMessage(true, fileName+"创建失败，请联系管理员");
                }
            }
            Path path = Paths.get(SavePath);
            Files.write(path, bytes);
            nursingRecordTemplateService.saveOrUpdate(templateMedicalRecord);
            return MessageUtil.createMessage(true, fileName+"模版保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return MessageUtil.createMessage(false, "服务器异常");
        }
    }
}

    