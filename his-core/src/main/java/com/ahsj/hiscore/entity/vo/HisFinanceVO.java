package com.ahsj.hiscore.entity.vo;

import com.ahsj.hiscore.common.excel.ColumnCheckAnnotation;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class HisFinanceVO implements Serializable {
    @ColumnCheckAnnotation(index = 0, length = 64, isRepeat = false, nullable = false)
    private Date createDate;//创建日期
    @ColumnCheckAnnotation(index = 1, length = 64, isRepeat = false, nullable = false)
    private BigDecimal medicineTotal;//药品收入
    @ColumnCheckAnnotation(index = 2, length = 64, isRepeat = false, nullable = false)
    private BigDecimal projectTotal;//项目收入
    @ColumnCheckAnnotation(index = 3, length = 64, isRepeat = false, nullable = false)
    private BigDecimal consumablesTotal;//耗材收入
    @ColumnCheckAnnotation(index = 4, length = 64, isRepeat = false, nullable = false)
    private BigDecimal bedTotal;//病床费用
    @ColumnCheckAnnotation(index = 5, length = 64, isRepeat = false, nullable = false)
    private BigDecimal backMedicineTotal;//药品退费
    @ColumnCheckAnnotation(index = 6, length = 64, isRepeat = false, nullable = false)
    private BigDecimal backProjectTotal;//项目退费
    @ColumnCheckAnnotation(index = 7, length = 64, isRepeat = false, nullable = false)
    private BigDecimal backConsumablesTotal;//耗材退费
    @ColumnCheckAnnotation(index = 8, length = 64, isRepeat = false, nullable = false)
    private BigDecimal total;//总价

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getMedicineTotal() {
        return medicineTotal;
    }

    public void setMedicineTotal(BigDecimal medicineTotal) {
        this.medicineTotal = medicineTotal;
    }

    public BigDecimal getProjectTotal() {
        return projectTotal;
    }

    public void setProjectTotal(BigDecimal projectTotal) {
        this.projectTotal = projectTotal;
    }

    public BigDecimal getConsumablesTotal() {
        return consumablesTotal;
    }

    public void setConsumablesTotal(BigDecimal consumablesTotal) {
        this.consumablesTotal = consumablesTotal;
    }

    public BigDecimal getBedTotal() {
        return bedTotal;
    }

    public void setBedTotal(BigDecimal bedTotal) {
        this.bedTotal = bedTotal;
    }

    public BigDecimal getBackMedicineTotal() {
        return backMedicineTotal;
    }

    public void setBackMedicineTotal(BigDecimal backMedicineTotal) {
        this.backMedicineTotal = backMedicineTotal;
    }

    public BigDecimal getBackProjectTotal() {
        return backProjectTotal;
    }

    public void setBackProjectTotal(BigDecimal backProjectTotal) {
        this.backProjectTotal = backProjectTotal;
    }

    public BigDecimal getBackConsumablesTotal() {
        return backConsumablesTotal;
    }

    public void setBackConsumablesTotal(BigDecimal backConsumablesTotal) {
        this.backConsumablesTotal = backConsumablesTotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public HisFinanceVO clone() {           //实现浅克隆
        HisFinanceVO hisFinanceVO = null;
        try {
            hisFinanceVO = (HisFinanceVO) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return hisFinanceVO;
    }

    public void clear() {
        this.createDate = null;
        this.medicineTotal = BigDecimal.valueOf(0);
        this.projectTotal = BigDecimal.valueOf(0);
        this.consumablesTotal = BigDecimal.valueOf(0);
        this.bedTotal = BigDecimal.valueOf(0);
        this.backMedicineTotal = BigDecimal.valueOf(0);
        this.backProjectTotal = BigDecimal.valueOf(0);
        this.backConsumablesTotal = BigDecimal.valueOf(0);
        this.total = BigDecimal.valueOf(0);
    }
}
