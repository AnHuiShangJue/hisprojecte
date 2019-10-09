package com.ahsj.hiscore.controller.ward;
import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.common.utils.*;
import com.ahsj.hiscore.dao.HisWardMapper;
import com.ahsj.hiscore.entity.HisImportInfo;
import com.ahsj.hiscore.entity.HisWard;
import com.ahsj.hiscore.entity.dto.ExcelUploadFile;
import com.ahsj.hiscore.entity.sys.SysCodeDetail;
import com.ahsj.hiscore.feign.ICodeService;
import com.ahsj.hiscore.services.HisBedService;
import com.ahsj.hiscore.services.HisImportInfoService;
import com.ahsj.hiscore.services.HisWradService;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/hisWards/")
public class HisWardsController extends BaseController {

    @Autowired
    HisWradService hisWradService;

    @Autowired
    HisBedService hisBedService;

    @Autowired
    ICodeService iCodeService;

    @Autowired
    HisImportInfoService hisImportInfoService;

    @Autowired
    HisWardMapper hisWardMapper;

    private Logger logger = LoggerFactory.getLogger(HisWardsController.class.getSimpleName());

    /**
     * @param token
     * @return
     * @Description
     * @Author: dingli
     * @Date 2019/7/13
     * @Time 15:36
     **/
    @RequestMapping("list/index.ahsj")
    ModelAndView listIndex(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/ward/list");
        modelAndView.addObject("title", "医疗信息系统");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisWard>
     * @Description 分页查询
     * @Params [model, request, hisWard]
     * @Author dingli
     * @Date 2019-07-03
     * @Time 11:58
     **/
    @ResponseBody
    @RequestMapping(value = "list.ahsj", method = {RequestMethod.POST})
    public PageBean<HisWard> list(Map<String, Object> model, HisWard hisWard) throws Exception {
        PageBean<HisWard> pageBean = new PageBean<HisWard>();
        pageBean.setParameter(hisWard);
        pageBean = hisWradService.getHisWardAll(pageBean);
        return pageBean;
    }


    /**
     * @param hisWard
     * @param token
     * @return
     * @Description
     * @Author: dingli
     * @Date 2019/7/13
     * @Time 15:37
     **/
    @RequestMapping("/addHisWard/index.ahsj")
    ModelAndView addHisWard(HisWard hisWard, String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/ward/add");
        modelAndView.addObject("title", "添加病房");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @return core.message.Message
     * @Description 添加
     * @Params [hisWard]
     * @Author dingli
     * @Date 2019-07-3
     * @Time 11：02
     **/
    @RequestMapping("/toaddHisWard/index.ahsj")
    @ResponseBody
    public Message toaddHisWard(Map<String, Object> model, HisWard hisWard, String token) throws Exception {
        hisWard.setCurrentCount(hisWard.getCount());
        return hisWradService.saveOrUpdate(hisWard);
    }

    /**
     * @return core.message.Message
     * @Description 批量删除
     * @Params [ids]
     * @Author dingli
     * @Date 2019-07-4
     * @Time 18：42
     **/
    @ResponseBody
    @RequestMapping(value = "delete.ahsj", method = {RequestMethod.POST})
    public Message delete(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value = "ids", required = true) Long[] ids
    ) throws Exception {
        if (null != request.getParameter("ids")) {
            return hisWradService.deleteByPrimaryKey(ids);
        } else return MessageUtil.createMessage(false, "参数异常");
    }

    /**
     * @return modelAndView
     * @Description 病房信息
     * @Params [id]
     * @Author dingli
     * @Date 2019-07-4
     * @Time 19：14
     **/
    @RequestMapping("/showHisWard/index.ahsj")
    ModelAndView showHisWard(Long id, String token, String set) throws Exception {
        ModelAndView modelAndView = null;
        if (set.equals("show")) {
            //查看病房
            modelAndView = new ModelAndView("backend/hiscore/ward/show");
        }
        if (set.equals("update")) {
            //修改病房
            modelAndView = new ModelAndView("backend/hiscore/ward/update");
        }
        modelAndView.addObject("title", "病床信息");
        modelAndView.addObject("token", token);
        modelAndView.addObject("hisWard", hisWradService.selectByPrimaryKey(id));
        //格式化新建跟修改时间
        HisWard hisWard = hisWradService.selectByPrimaryKey(id);
        modelAndView.addObject("createName", hisWradService.getUserName(new Long(hisWard.getCreateUserId())));
        modelAndView.addObject("updateName", hisWradService.getUserName(new Long(hisWard.getUpdateUserId())));
        return modelAndView;
    }

    /**
     * @Description [hisWard, token]
     * @Author: dingli
     * core.message.Message
     * @Date 2019/9/4
     * @Time 16:33
     **/
    @RequestMapping("/toUpdate/index.ahsj")
    public Message toUpdateHisWard(HisWard hisWard, String token) throws Exception {
        return hisWradService.saveOrUpdate(hisWard);
    }

    /**
     * @param model
     * @param request
     * @param ids
     * @Description
     * @Author: dingli
     * @return: core.message.Message
     * @Date 2019/8/17
     * @Time 17:03
     **/
    @ResponseBody
    @RequestMapping(value = "/setEnable.ahsj")
    public Message setEnable(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value = "ids", required = true) Long[] ids) throws Exception {
        if (null != request.getParameter("ids")) {
            return hisWradService.setEnable(ids);
        } else return MessageUtil.createMessage(false, "参数异常");
    }

    /**
     * 2
     *
     * @param
     * @Description 获取所有的病床
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisWard>
     * @Date 2019/8/17
     * @Time 17:07
     **/
    @RequestMapping("/getWardAll/index.ahsj")
    @ResponseBody
    public List<HisWard> getWardAll() throws Exception {
        return hisWradService.selectHisWard();
    }

    /**
     * @param number
     * @param request
     * @param response
     * @param session
     * @Description 导出excel表格
     * @Author: dingli
     * @return: void
     * @Date 2019/8/28
     * @Time 15:12
     **/
    @RequestMapping("exportExcel")
    @ResponseBody
    public void exportExcels(@RequestParam(value = "ids", required = false) Long[] ids, Integer number, Integer isEnable,
                             String param, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        HisWard hisWard = new HisWard();
        hisWard.setNumber(number);
        hisWard.setIsEnable(isEnable);
        hisWradService.exportExcels(ids, hisWard, param, request, response, session);
    }

    /**
     * @param request
     * @param fileName
     * @Description 导入Excel表格
     * @Author: dingli
     * @return: void
     * @Date 2019/8/29
     * @Time 10:26
     **/
    @RequestMapping(value = "importExcel.ahsj", method = RequestMethod.POST)
    @ResponseBody
    public String importExcel(HttpServletRequest request, @RequestParam(value = "excelFile") MultipartFile fileName) throws Exception {
        List<String> errorFilelist = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        SimpleDateFormat formatOne = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(new Date());
        String dateOne = formatOne.format(new Date());
        String voucher = "ERROR" + date;
        Integer errorNull = Integer.parseInt(date.substring(8));//编号为空填充字段
        String filename = fileName.getOriginalFilename();
        List<HisImportInfo> importInfoList = new ArrayList<>();
        List<HisWard> hisWardList = ExcelUtils.readExcel("", HisWard.class, fileName);
        for (int i = 0; i < hisWardList.size(); i++) {
            if (EmptyUtil.Companion.isNullOrEmpty(hisWardList.get(i).getNumber())) {
                HisImportInfo hisImportInfo = new HisImportInfo();
                String errors = dateOne + " : 第" + (i + 1) + "条记录的病房编号存在空值";
                hisWardList.get(i).setNumber(errorNull);
                errorFilelist.add(errors);
                hisImportInfo.setUploadErrorInfo(errors);
                hisImportInfo.setUploadFileName(filename);
                hisImportInfo.setVoucher(voucher);
                importInfoList.add(hisImportInfo);
            }
        }
        List<Integer> collectList = hisWardList.stream().map(HisWard::getNumber).collect(Collectors.toList());//所有的number集合
        int[] collect = collectList.stream().mapToInt(Integer::valueOf).toArray();//Integer 集合转数组
        CheckRepeatUtils checkRepeatUtils = new CheckRepeatUtils();
        Map<Integer, List<Integer>> allRepeatedElementsString = checkRepeatUtils.findAllRepeatedElementsInteger(collect);
        if (!EmptyUtil.Companion.isNullOrEmpty(allRepeatedElementsString)) {
            for (Map.Entry<Integer, List<Integer>> errorInfo : allRepeatedElementsString.entrySet()) {
                HisImportInfo hisImportInfo = new HisImportInfo();
                Integer key = errorInfo.getKey();
                List<Integer> errorRepeaNum = new ArrayList<>();
                List<Integer> values = errorInfo.getValue();
                for (Integer value : values) {
                    errorRepeaNum.add(value + 1);
                }
                String errors = dateOne + " : 病床编号为 : " + key + " 重复 , " + "具体在 " + errorRepeaNum + "条";
                errorFilelist.add(errors);
                hisImportInfo.setUploadErrorInfo(errors);
                hisImportInfo.setUploadFileName(filename);
                hisImportInfo.setVoucher(voucher);
                importInfoList.add(hisImportInfo);
            }
        }
        List<HisWard> arrayList = hisWardList.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(
                        () -> new TreeSet<>(Comparator.comparing(e -> e.getNumber()))), ArrayList::new)
        );
        List<HisWard> list = new ArrayList<>();
        for (HisWard hisWard : arrayList) {
            if (EmptyUtil.Companion.isNullOrEmpty(hisWard.getRoomtype()) || EmptyUtil.Companion.isNullOrEmpty(hisWard.getCount()) ||
                    EmptyUtil.Companion.isNullOrEmpty(hisWard.getCurrentCount()) || EmptyUtil.Companion.isNullOrEmpty(hisWard.getEnable())
            ) {
                String errors = "";
                if (hisWard.getNumber().equals(errorNull)) {
                    errors = dateOne + "病房编号未填写和必填字段未填写或者填写类型有误";
                } else {
                    errors = dateOne + " : 病房编号为 : " + hisWard.getNumber() + "的记录信息必填字段未填写或者填写类型有误";
                }
                errorFilelist.add(errors);
                HisImportInfo hisImportInfo = new HisImportInfo();
                hisImportInfo.setUploadErrorInfo(errors);
                hisImportInfo.setUploadFileName(filename);
                hisImportInfo.setVoucher(voucher);
                importInfoList.add(hisImportInfo);
            } else {
                //字典
                SysCodeDetail sysCodeDetail = new SysCodeDetail();
                sysCodeDetail.setValue(hisWard.getRoomtype());
                sysCodeDetail.setType(Constants.HIS_SYS_CODE_ROOM_TYPE);//字典字段
                sysCodeDetail = iCodeService.selectSysCode(sysCodeDetail);

                SysCodeDetail sysCodeDetail1 = new SysCodeDetail();
                sysCodeDetail1.setValue(hisWard.getEnable());
                sysCodeDetail1.setType(Constants.HIS_SYS_IS_ENABLE);//字典字段
                sysCodeDetail1 = iCodeService.selectSysCode(sysCodeDetail1);

                if (EmptyUtil.Companion.isNullOrEmpty(sysCodeDetail.getId()) || EmptyUtil.Companion.isNullOrEmpty(sysCodeDetail1.getId())) {
                    String errors = dateOne + " : 病房编号为 : " + hisWard.getNumber() + "的记录信息启用状态和病房类型填写有误";
                    errorFilelist.add(errors);
                    HisImportInfo hisImportInfo = new HisImportInfo();
                    hisImportInfo.setUploadErrorInfo(errors);
                    hisImportInfo.setUploadFileName(filename);
                    hisImportInfo.setVoucher(voucher);
                    importInfoList.add(hisImportInfo);
                    continue;
                } else {
                    list.add(hisWard);
                }
            }
        }
        List<HisWard> hisWardList1 = hisWardMapper.selectHisWard();//数据库的
        List<HisWard> successList = new ArrayList<HisWard>(); //可以导入的组
        HashMap<Integer, HisWard> IntegerMap = new HashMap<Integer, HisWard>();
        hisWardList1.stream().forEach(e -> IntegerMap.put(e.getNumber(), e));  //将数据库的添加到IntegerMap集合
        list.stream().filter(f -> !IntegerMap.containsKey(f.getNumber())).forEach(e -> successList.add(e));//与数据库不同的元素
        List<HisWard> collect1 = list.stream().filter(f -> IntegerMap.containsKey(f.getNumber())).collect(Collectors.toList());//与数据库相同的元素
        for (HisWard hisWard : collect1) {
            String errors = dateOne + " : 病床编号为 : " + hisWard.getNumber() + "已存在";
            logger.info("---------与数据库相同的病房编号" + hisWard.getNumber() + "------------");
            errorFilelist.add(errors);
            HisImportInfo hisImportInfo = new HisImportInfo();
            hisImportInfo.setUploadErrorInfo(errors);
            hisImportInfo.setUploadFileName(filename);
            hisImportInfo.setVoucher(voucher);
            importInfoList.add(hisImportInfo);
        }
        Map<String, Integer> map = new HashMap<String, Integer>();
        if (!CollectionUtils.isEmpty(successList)) {
            List<HisWard> collect2 = successList.stream().filter(e -> (!e.getNumber().equals(errorNull))).collect(Collectors.toList());
            map = hisWradService.importExcel(collect2, filename);
        }
        File file = new File(Constants.HIS_SYS_EXCEL_DOCTOR_ERROR_FILE_URL);
        hisImportInfoService.inserHisImportInfo(importInfoList);//错误日志导入数据库
        Result result = new Result();
        ExcelUploadFile excelUploadFile = new ExcelUploadFile();
        if (!EmptyUtil.Companion.isNullOrEmpty(importInfoList)) {
            String vouchers = importInfoList.get(0).getVoucher();
            //错误日志集合
            List<String> errorList = importInfoList.stream().map(HisImportInfo::getUploadErrorInfo).collect(Collectors.toList());
            excelUploadFile.setErrorList(errorList);
            excelUploadFile.setVouchers(vouchers);
            excelUploadFile.setErrorListSize(errorList.size());
        }
        List<String> errorList = importInfoList.stream().map(HisImportInfo::getUploadErrorInfo).collect(Collectors.toList());//错误日志
        FileUtil.listToFile(errorFilelist, file);
        int success = EmptyUtil.Companion.isNullOrEmpty(map.get("success")) ? 0 : map.get("success").intValue();
        int error = hisWardList.size() - success;
        logger.info("病房信息导入成功" + success + "条");
        logger.info(errorNull.toString());
        excelUploadFile.setFaildNum(error);
        excelUploadFile.setSuccessNum(success);
        excelUploadFile.setErrorList(errorList);
        excelUploadFile.setImportListSize(hisWardList.size());
        excelUploadFile.setErrorListSize(errorList.size());
        result.setStatus(200);
        result.setData(excelUploadFile);
        return JsonUtils.serialize(result);
    }

    @GetMapping("/error.ahsj")
    @ResponseBody
    public void error(HttpServletRequest request, @RequestParam("vouchers") String vouchers, HttpServletResponse response) throws Exception {
        HisImportInfo hisImportInfo = new HisImportInfo();
        hisImportInfo.setVoucher(vouchers);
        List<HisImportInfo>hisImportInfoList =  hisImportInfoService.queryHisImportInfo(hisImportInfo);
        List<String> collect = hisImportInfoList.stream().map(HisImportInfo::getUploadErrorInfo).collect(Collectors.toList());
        String path  = null;
        path = this.getClass().getClassLoader().getResource("templates/errorImport/errorInfo.txt").getPath();
        path = Constants.HIS_SYS_EXCEL_PROJECT_ERROR_FILE_URL;
        //清空txt文件数据
        FileUtil.clearInfoForFile(path);
        //将错误数据存到txt文件里
        File file = new File(path);
        FileUtil.listToFile(collect, file);
        //导出
        FileUtil.download(response,path);
    }
    /**
     * @param response
     * @Description
     * @Author: dingli
     * @return: void
     * @Date 2019/9/3
     * @Time 17:00
     **/
    @GetMapping("/download.ahsj")
    @ResponseBody
    public void exportExcel(HttpServletResponse response) throws Exception {
     //   String psth = this.getClass().getClassLoader().getResource("templates/excel/import/HisWard.xlsx").getPath();
      String  psth =Constants.HIS_SYS_EXCEL_HISWARD_IMPORT_FILE_URL;
        FileUtil.download(response, psth);
    }

}