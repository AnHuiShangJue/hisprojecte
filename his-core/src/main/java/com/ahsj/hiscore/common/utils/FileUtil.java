package com.ahsj.hiscore.common.utils;

import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ahsj.hiscore.entity.HisImportInfo;
import org.apache.commons.io.output.FileWriterWithEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;


public class FileUtil {

   private static Logger log = LoggerFactory.getLogger(FileUtil.class.getSimpleName());

    public FileUtil(){}
    
    /**
     * 实体类数据写入文件中
     * @param <T>
     * @param t 实体类
     * @param fileName 文件名（带后缀）
     * @param fileDir 文件夹路径，不包括文件名及后缀
     * @return
     */
    public static <T> boolean beanToFile(T t,String fileName,String fileDir){
        boolean bl = false;
        try {
            //取得类中所有属性
            Class clazz = t.getClass();
            Field[] fields = clazz.getDeclaredFields();
            
            //判断文件夹是否存在，不存在则创建
            File fileDir2 = new File(fileDir);
            if(!fileDir2.exists()){
                fileDir2.mkdirs();
            }
            //文件
            File file = new File(fileDir,fileName);
            
            //需要封装的list
            List<String> list = new ArrayList<String>();
            //header数组，用来设置数据字符串，使其一一按顺序对应
            String[] titles = null;
            if(!file.exists()){//如果文件不存在，则创建文件并加header
                //header字符串设置
                StringBuilder titleStr = new StringBuilder();
                for (int i = 0; i < fields.length; i++) {
                    if(i > 0){
                        titleStr.append(",");
                    }
                    titleStr.append(fields[i].getName());
                }
                list.add(titleStr.toString());
                
                titles = titleStr.toString().split(",");
            }else{//如果文件存在，则继续追加数据
                //读取文件第一行获得header
                titles = readFirstLine(file).replace("\"","").split(",");
            }
            //封装数据字符串，与header按顺序对于设置
            list.add(beanToStr(t,titles,clazz));
            bl = listToFile(list,file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bl;
    }
    
    /**
     * 根据传入的key数组设置bean中数据，按key的顺序封装成字符串
     * @param obj javaBean
     * @param keys 列名数组
     * @param clazz Class
     * @return
     */
    public static String beanToStr(Object obj,String[] titles,Class<?> clazz){
        try {
            StringBuilder sb = new StringBuilder();
            
            for (int i = 0; i < titles.length; i++) {
                PropertyDescriptor pd = new PropertyDescriptor(titles[i], clazz);
                //得到get方法
                Method method = pd.getReadMethod();
                //执行get方法
                Object value = method.invoke(obj);
                if(i > 0){
                    sb.append(",");
                }
                //封装数据，用","隔开
                sb.append(value != null ? String.valueOf(value) : "");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 读取文件第一行
     * @param file
     * @return
     */
    public static String readFirstLine(File file){
        String res = null;
        
        InputStream is = null;
        BufferedReader buffer = null;
        try {
            is = new FileInputStream(file);
            buffer = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            
            res = buffer.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(buffer != null){
                try {
                    buffer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    buffer = null;
                }
            }
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    is = null;
                }
            }
        }
        return res;
    }
    
    /**
     * list 写入文件
     * @param list
     * @param file
     * @return
     */
    public static boolean listToFile(List<String> list,File file){
        boolean bl = true;
        
        Writer writer = null;
        BufferedWriter buffer = null;
        try {
            writer = new FileWriterWithEncoding(file,"UTF-8",true);
            buffer = new BufferedWriter(writer);
            
            if(list != null && !list.isEmpty()){
                for (String str : list) {
                    buffer.write(str);
                    buffer.newLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            bl = false;
        }finally{
            if(buffer != null){
                try {
                    buffer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    buffer = null;
                }
            }
            if(writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    writer = null;
                }
            }
        }
        return bl;
    }

    public static void download(HttpServletResponse response, String filePath) {
        String encode = "UTF-8";
        response.setContentType("text/html;charset=" + encode);
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        String downLoadPath = filePath;
        try {
            File file = new File(downLoadPath);
            long fileLength = file.length();
            String fileName = file.getName();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes(encode), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(downLoadPath));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if (bis != null)
                try {
                    bis.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            if (bos != null)
                try {
                    bos.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
        }
    }

    /**
     * 清空文件内容
     * @param fileName
     */
    public static void clearInfoForFile(String fileName) {
        File file =new File(fileName);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String readFile(InputStream inputStream) throws Exception {

        StringBuilder builder = new StringBuilder();
        try {
            InputStreamReader reader = new InputStreamReader(inputStream , "UTF-8" );
            BufferedReader bfReader = new BufferedReader( reader );
            String tmpContent = null;
            while ( ( tmpContent = bfReader.readLine() ) != null ) {
                builder.append( tmpContent );
            }
            bfReader.close();
        } catch ( UnsupportedEncodingException e ) {
            // 忽略
        }
        return this.filter( builder.toString() );
    }

    // 过滤输入字符串, 剔除多行注释以及替换掉反斜杠
    private   String filter ( String input ) {
        return input.replaceAll( "/\\*[\\s\\S]*?\\*/", "" );
    }

}