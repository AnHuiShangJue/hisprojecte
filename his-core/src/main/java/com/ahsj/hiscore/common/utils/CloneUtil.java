package com.ahsj.hiscore.common.utils;

import java.io.*;

public class CloneUtil {
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T clone(T object) throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bout);
        oos.writeObject(object);
        ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bin);
        // 此处不需要释放资源，说明：调用ByteArrayInputStream或ByteArrayOutputStream对象的close方法没有任何意义
        // 这两个基于内存的流只要垃圾回收器清理对象就能够释放资源，这一点不同于对外部资源（如文件流）的释放
        return (T) ois.readObject();
    }
}
