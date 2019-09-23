package com.ahsj.translate;

import com.ahsj.translate.common.Constants;
import com.ahsj.translate.common.google.GoogleApi;
import com.ahsj.translate.dao.TranslateMapper;
import com.ahsj.translate.entity.Organization;
import com.ahsj.translate.entity.SysCodeDetail;
import com.ahsj.translate.entity.Translate;
import com.ahsj.translate.entity.TranslateInfo;
import com.ahsj.translate.entity.model.TranslateInfoModel;
import com.ahsj.translate.entity.model.TranslateModel;
import com.ahsj.translate.feign.ICodeService;
import com.ahsj.translate.feign.IOrganizationService;
import com.ahsj.translate.service.TranslateService;
import core.message.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TranslateApplication.class)
public class TranslateApplicationTests {


    @Autowired
    TranslateService translateService;
    @Autowired
    TranslateMapper translateMapper;
    @Autowired
    IOrganizationService iOrganizationService;
    @Autowired
    ICodeService iCodeService;

    public static void main(String[] args) {
        //获取当前类文件
        System.out.println(Translate.class.getResource(""));
        //获取当前类文件的路径
        System.out.println(Translate.class.getResource("").getPath());
        //获取类路径下的所有文件
        System.out.println(Translate.class.getResource("/"));
        //获取类路径
        System.out.println(Translate.class.getResource("/").getPath());
        //平时遇到不知道类名，也在static块下不能用this获取类的情况下，我们可以用Object这个类
        System.out.println(Object.class.getResource("/").getPath());
        //如果不知道类名，通过实例.getClass()获取
        Translate t = new Translate();
        System.out.println(t.getClass());
        //等同于上
       /* System.out.println(t.);
        System.out.println(t.clazz);*/
        System.out.println(Translate.class.getName());
    }

    @Test
    public void getww() throws Exception {
            String str = "101";
            short s = Short.parseShort(str);
            short s1 = Short.valueOf(str);
            System.out.println("转换后:");
            System.out.println(s);
            System.out.println(s1);
    }

    public static void getFieldName(Object t) throws Exception {
        Class clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        int length = fields.length;

        while (length-- > 0) {
            Translate translate = new Translate();
            translate.setTranslateKhmer(t.getClass().getName());
            translate.setTranslateColumn(fields[length].getName());

        }
    }


    @Test
    public void getoo() throws Exception {
        String data = "2018-05-22";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = format.parse(data);
        System.out.println(parse);
      /*  List<Organization> organizations = iOrganizationService.lstAllSysOrganization();
        System.out.println(organizations.size());*/
    }
    @Test
    public void getooee() throws Exception {
        SysCodeDetail sysCodeDetail = new SysCodeDetail();
            sysCodeDetail.setId(415);
            sysCodeDetail.setCode("1");
            sysCodeDetail.setValue("中国");
            toString(sysCodeDetail,SysCodeDetail.class,sysCodeDetail.getId().longValue(),Constants.TRANSLATE_SYS_CODE_DETAIL);
    }

    @Test
    public void contextLoads() throws Exception {
        TranslateModel model = new TranslateModel();
        model.setUserId(1L);
        List<Translate> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Translate translate = new Translate();
            translate.setTranslateType("1");
            translate.setTranslateId(2L);
            translate.setCreateUserId(1L);
            translate.setTranslateChina("\n" +
                    "何大和元年，太学录，六年，作郎。建炎四年，为滕康、刘珏属官，坐失年，太学录，六年，作郎" +
                    "，坐失坐失洪州除名岭南编管。");
            list.add(translate);
        }
        model.setTranslateList(list);
        Message message = translateService.addTranslateList(model);
        Translate translate1 = new Translate();
    }


    public static void getField(Class<?> t) throws Exception {
        String typeName = t.getTypeName();
        System.out.println(typeName);
        Class<?> c = null;
        c = Class.forName("wml.demo.Person");
        Field[] fields = c.getDeclaredFields();

        for (Field f : fields) {
            f.setAccessible(true);
        }
        //输出p1的所有属性
        System.out.println("=============About p1===============");
        for (Field f : fields) {
            String field = f.toString().substring(f.toString().lastIndexOf(".") + 1);         //取出属性名称
            System.out.println("p1." + field + " --> " + f.get(t));
        }

    }

    @Test
    public  void date(){

    }


    public void toString(Object obj, Class<?> clazz, Long id, String constants) {
        if (obj == null) {
            return;
        }
        List<Translate> list = new ArrayList<>();
        TranslateModel model = new TranslateModel();
        // BaseLoginUser loginUser =  new BaseLoginUser();
        model.setUserId(1L);
        Field[] fields = clazz.getDeclaredFields();// 根据Class对象获得属性 私有的也可以获得
        String s = "";
        try {
            for (Field f : fields) {
                Translate translate = new Translate();
                translate.setTranslateId(id);
                translate.setTranslateType(constants);
                translate.setTranslateColumn(f.getName());// 得到此属性的名称
                f.setAccessible(true); // 设置些属性是可以访问的
                Object china = f.get(obj);// 得到此属性的值
                if (china == null) {
                    continue;
                }
                translate.setTranslateChina(china.toString());
                list.add(translate);
            }
            model.setTranslateList(list);
            translateService.addTranslateList(model);
        } catch (Exception e) {
        }
        //return s;
    }

    @Test
    public void getDate(){
        Date date = new Date();//当前时间
        Date beforeDate = new Date();

        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(date);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -1); //设置为前一天
        beforeDate = calendar.getTime(); //得到前一天的时间

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        String defaultStartDate = sdf.format(beforeDate); //格式化前一天
        String defaultEndDate = sdf.format(date); //格式化当前时间

        System.out.println("前一天的时间是：" + defaultStartDate);
        System.out.println("生成的时间是：" + defaultEndDate);
    }


   /* @Test
    public  void getT(){
        Translate translate = new Translate();
        translate.setTranslateType("是的");
        translate.setTranslateId(100L);
        translate.setId(12L);
        translate.setTranslateJoin("sjsjssj");
        TranslateInfoModel translateInfoModel = toTranslateInfo(translate, Translate.class, translate.getId(), Constants.TRANSLATE_HIS_HISANKLETEMPLATE);
        List<TranslateInfo> translateList = translateInfoModel.getTranslateList();
        for (TranslateInfo translateInfo : translateList) {
            System.out.println(translateInfo.toString());
        }
    }
*/
    /**
     * @return void
     * @功能说明
     * @Params [obj, clazz, id, constants, userId]
     * @Author XJP
     * @Date 2019/7/29
     * @Time 11:37
     **/
   /* public TranslateInfoModel toTranslateInfo(Object obj, Class<?> clazz, Long id, String constants) {
        if (obj == null) {
            return null;
        }
        List<TranslateInfo> list = new ArrayList<>();
        TranslateInfoModel model = new TranslateInfoModel();

        Field[] fields = clazz.getDeclaredFields();// 根据Class对象获得属性 私有的也可以获得
        String s = "";
        try {
            for (Field f : fields) {
                TranslateInfo translate = new TranslateInfo();
                translate.setTranslateId(id);
                *//*translate.setCreateUserId(userId.toString());*//*
                translate.setTranslateType(constants);
                translate.setTranslateColumn(f.getName());// 得到此属性的名称
                f.setAccessible(true); // 设置些属性是可以访问的
                Object china = f.get(obj);// 得到此属性的值
                if (china == null) {
                    continue;
                }
                translate.setTranslateChina(china.toString());
                list.add(translate);
            }
            model.setTranslateList(list);
            // translateService.addTranslateList(model);
        } catch (Exception e) {
        }
        return model;
    }
*/

}
