/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: Page
 * Author:   anhuishangjue
 * Date:     2019/7/5 12:13
 * Description: dtio.Page
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.ahsj.translate.common.dto;

/**
 * 〈一句话功能简述〉<br> 
 * 〈dtio.Page〉
 *
 * @author anhuishangjue
 * @create 2019/7/5
 * @since 1.0.0
 */

public class Page {
    private  Integer pageNum;
    private  Integer pageSize;
    private  Integer count;

    public Page() {
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Page(Integer pageNum, Integer pageSize, Integer count) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.count = count;
    }
}
