package com.ahsj.smartparkcore.entity.vo;

import com.ahsj.smartparkcore.entity.po.Book;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BookVO extends Book {

    private Long id;

    private Date createDate;

    private Date updateDate;

    private Integer bookType;

    private Date startTime;

    private Date endTime;

    private Integer isAudit;

    private Integer isCancel;

    private Integer isPay;

    private BigDecimal paymentAmount;

    private BigDecimal deposit;

    private String phoneNumber;

    private String subscriberName;

    private Long targetId;

    private String remarks;

    private String description;

}
