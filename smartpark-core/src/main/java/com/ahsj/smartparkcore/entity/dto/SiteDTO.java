package com.ahsj.smartparkcore.entity.dto;

import com.ahsj.smartparkcore.entity.po.Site;
import core.code.CodeValueColumn;
import core.code.Constants;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SiteDTO extends Site {
    private Long id;

    private String siteName;

    private String title;

    private String location;

    private BigDecimal price;

    private BigDecimal upPrice;//上区间

    private BigDecimal lowPrice;//下区间

    private Double area;

    private String name;

    private String phoneNumber;

    private String description;

    private Short isEnable;

    private String capacity;

    private String environmentalLabel;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_lease", typeName = "leaseName")
    private Integer isLease;
    private String leaseName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_verify", typeName = "verifyName")
    private Integer isVerify;
    private String verifyName;

    private Long enterpriseId;

}