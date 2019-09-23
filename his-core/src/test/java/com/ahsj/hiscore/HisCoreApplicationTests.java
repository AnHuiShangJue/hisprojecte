package com.ahsj.hiscore;

import com.ahsj.hiscore.entity.HisHospitalManage;
import com.ahsj.hiscore.feign.IOrganizationService;
import com.ahsj.hiscore.feign.ITranslateService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = HisCoreApplication.class)
public class HisCoreApplicationTests {
    static Logger logger = LoggerFactory.getLogger(HisCoreApplicationTests.class.getSimpleName());

    @Autowired
    ITranslateService iTranslateService;

    @Autowired
    IOrganizationService iOrganizationService;


    @Test
    public void contextLoads() throws Exception {
//        TranslateModel model = new TranslateModel();
//        model.setUserId(1L);
//        List<Translate> list = new ArrayList<>();
//        for (int i = 0; i < 1; i++) {
//            Translate translate = new Translate();
//            translate.setTranslateType("1");
//            translate.setTranslateId(2L);
//            translate.setCreateUserId(1L);
//            translate.setTranslateChina("\n" +
//                    "何大和元年，太学录，六年，作郎。建炎四年，为滕康、刘珏属官，坐失年，太学录，六年，作郎" +
//                    "，坐失坐失洪州除名岭南编管。");
//            list.add(translate);
//        }
//        model.setTranslateList(list);
//        iTranslateService.addTranslate(model);

//        Organization organizationById = iOrganizationService.getOrganizationById(10L);
//        System.out.println(organizationById.getName());

//        List<Translate> translates = iTranslateService.queryTranslateAll();
//        System.out.println(translates.size());
    }
/*
    public static void main(String[] args) {

        String[] arrayA = new String[]{"A", "B", "C", "D", "E", "F"};
        String[] arrayB = new String[]{"B", "D", "F", "G", "H", "K"};
        List<String> listA = Arrays.asList(arrayA);
        List<String> listB = Arrays.asList(arrayB);
        //2个数组取交集 的补集
        //2个数组取交集 的补集
        String s = ArrayUtils.toString(CollectionUtils.disjunction(listA, listB));
        Collection disjunction = CollectionUtils.subtract(listA, listB);
        List<String> collect = (List<String>) disjunction.stream().collect(Collectors.toList());
        collect.stream().forEach(System.out::println);

        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime time = LocalDateTime.now();
        DayOfWeek dayOfWeek = time.getDayOfWeek();

        String format = dft.format(time);
        TemporalAccessor parse = dft.parse(format);
        System.out.println(parse);
        System.out.println(format);
        System.out.println(dayOfWeek);
        System.out.println(time);


    }*/




    /*    list1.add(1);
        list1.add(3);
        list1.add(2);
        list1.add(4);
        list1.add(5);*/


    /**
     * 方法一：使用循环遍历
     */
    private static boolean checkDiffrent(List<Integer> list, List<Integer> list1) {
        long st = System.nanoTime();
        if (list.size() != list1.size()) {
            // System.out.println("消耗时间：" + (System.nanoTime() - st));
            return false;
        }
        for (Integer str : list) {
            if (!list1.contains(str)) {
                //   System.out.println("消耗时间：" + (System.nanoTime() - st));
                return false;
            }
        }
        // System.out.println("消耗时间：" + (System.nanoTime() - st));
        return true;
    }

    public static void main(String[] args) {
      /*  List<Integer> objects = new ArrayList<>();
        for (int i = 0; i < 10 ; i++) {
            objects.add(i);
        }*/
       /* objects.stream().forEach(System.out::println);
        System.out.println("--------------------------------------------------");
     objects.stream().map((e) -> e + 9).forEach(System.out::println);*/
        //DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
       // System.out.println( dateTimeFormatter.format(LocalDateTime.now()));
        // 定义字符串数组
        List<Integer> objects = new ArrayList<>();
        for (int i = 0; i < 10 ; i++) {
            objects.add(i);}



    }












}

