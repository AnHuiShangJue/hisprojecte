package com.ahsj.hiscore.entity;

import com.ahsj.hiscore.common.excel.ColumnCheckAnnotation;
import com.ahsj.hiscore.common.excel.DataType;
import com.ahsj.hiscore.common.utils.ExcelColumn;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;

import java.util.Date;

public class HisWard extends BaseEntity {

    private Long id;

    @ExcelColumn(value = "病房编号", col = 1)
    @ColumnCheckAnnotation(index = 1, length = 64, isRepeat = true, nullable = false)
    private Integer number;

    private Integer numbers;

    private Date createDate;

    private Date updateDate;


    @ColumnCheckAnnotation(index = 2, length = 1, isRepeat = true, nullable = false, dataType = DataType.Code, codeKey = "room_type")
    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "room_type", typeName = "roomtype")
    private Integer type;

    @ExcelColumn(value = "病房类型", col = 2)
    private String roomtype;

    @ExcelColumn(value = "总床数", col = 3)
    @ColumnCheckAnnotation(index = 3, length = 64, isRepeat = true, nullable = false)
    private Integer count;

    @ExcelColumn(value = "剩余床数", col = 4)
    @ColumnCheckAnnotation(index = 4, length = 64, isRepeat = true, nullable = false)
    private Integer currentCount;

    private String remark;

    private Long typeId;

    private Long enableId;

    @ColumnCheckAnnotation(index = 5, length = 64, isRepeat = true, nullable = false)
    private String userName;

    @ColumnCheckAnnotation(index = 6, length = 1, isRepeat = true, nullable = false, dataType = DataType.Code, codeKey = "is_enable")
    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_enable", typeName = "enable")
    private Integer isEnable;

    @ExcelColumn(value = "是否启用", col = 5)
    private String enable;

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public Date getUpdateDate() {
        return updateDate;
    }

    @Override
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getEnableId() {
        return enableId;
    }

    public void setEnableId(Long enableId) {
        this.enableId = enableId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(Integer currentCount) {
        this.currentCount = currentCount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }
}