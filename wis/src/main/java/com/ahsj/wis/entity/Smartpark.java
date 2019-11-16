package com.ahsj.wis.entity;

import java.util.Date;

public class Smartpark {
    private Long id;

    private String title;

    private String description;

    private String productTitle;

    private String product1;

    private String product2;

    private String product3;

    private String certificationTitle;

    private String certification;

    private Date createDate;

    private Date updateDate;

    private String productContent1;

    private String productContent2;

    private String productContent3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle == null ? null : productTitle.trim();
    }

    public String getProduct1() {
        return product1;
    }

    public void setProduct1(String product1) {
        this.product1 = product1 == null ? null : product1.trim();
    }

    public String getProduct2() {
        return product2;
    }

    public void setProduct2(String product2) {
        this.product2 = product2 == null ? null : product2.trim();
    }

    public String getProduct3() {
        return product3;
    }

    public void setProduct3(String product3) {
        this.product3 = product3 == null ? null : product3.trim();
    }

    public String getCertificationTitle() {
        return certificationTitle;
    }

    public void setCertificationTitle(String certificationTitle) {
        this.certificationTitle = certificationTitle == null ? null : certificationTitle.trim();
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification == null ? null : certification.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getProductContent1() {
        return productContent1;
    }

    public void setProductContent1(String productContent1) {
        this.productContent1 = productContent1 == null ? null : productContent1.trim();
    }

    public String getProductContent2() {
        return productContent2;
    }

    public void setProductContent2(String productContent2) {
        this.productContent2 = productContent2 == null ? null : productContent2.trim();
    }

    public String getProductContent3() {
        return productContent3;
    }

    public void setProductContent3(String productContent3) {
        this.productContent3 = productContent3 == null ? null : productContent3.trim();
    }
}