package com.ahsj.userinfor.entity.TranslateModel;

import java.io.Serializable;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisProjectTranslate
 * <p>
 * Date:     2019/8/1 19:17
 *
 * @author XJP
 * @create 2019/8/1
 * @since 1.0.0
 */

public class HisProjectTranslate  implements Serializable {

    private static final long serialVersionUID = -8632316295057003850L;

    private Long id;

    private String name;

    private String pinyinCode;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyinCode() {
        return pinyinCode;
    }

    public void setPinyinCode(String pinyinCode) {
        this.pinyinCode = pinyinCode;
    }
}
