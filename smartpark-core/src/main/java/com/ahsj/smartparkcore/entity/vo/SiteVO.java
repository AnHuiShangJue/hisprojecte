package com.ahsj.smartparkcore.entity.vo;

import com.ahsj.smartparkcore.entity.po.Site;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SiteVo extends Site {
    private Long id;

    private String siteName;

    private String title;

    private String location;

    private BigDecimal price;

    private Double area;

    private String name;

    private String phoneNumber;

    private String description;

    private Short isEnable;

    private String capacity;

    private String environmentalLabel;

    private Integer isLease;

    private Integer isVerify;

    private Long enterpriseId;

}