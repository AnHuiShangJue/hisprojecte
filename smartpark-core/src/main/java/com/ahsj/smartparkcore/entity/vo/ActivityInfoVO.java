package com.ahsj.smartparkcore.entity.vo;

import com.ahsj.smartparkcore.entity.po.ActivityInfo;
import lombok.Data;

import java.util.Date;

@Data

public class ActivityInfoVO extends ActivityInfo {
    private Long id;

    private String name;

    private String eventLocation;

    private Date registrationDeadline;

    private Date startTime;

    private Date endTime;

    private Long maximumNumber;

    private String description;

    private Integer isEnable;

    private Integer isPublic;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEventLocation() {
        return eventLocation;
    }

    @Override
    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    @Override
    public Date getRegistrationDeadline() {
        return registrationDeadline;
    }

    @Override
    public void setRegistrationDeadline(Date registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
    }

    @Override
    public Date getStartTime() {
        return startTime;
    }

    @Override
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Override
    public Date getEndTime() {
        return endTime;
    }

    @Override
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public Long getMaximumNumber() {
        return maximumNumber;
    }

    @Override
    public void setMaximumNumber(Long maximumNumber) {
        this.maximumNumber = maximumNumber;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Integer getIsEnable() {
        return isEnable;
    }

    @Override
    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    @Override
    public Integer getIsPublic() {
        return isPublic;
    }

    @Override
    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }
}
