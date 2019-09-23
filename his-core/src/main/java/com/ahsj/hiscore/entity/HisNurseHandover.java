package com.ahsj.hiscore.entity;

import core.entity.BaseEntity;

import java.util.Date;

public class HisNurseHandover extends BaseEntity {
    private Long id;

    private Integer department;

    private Date date;

    private String ward;

    private Integer whiteInfoAll;

    private Integer whiteInfoIn;

    private Integer whiteInfoTransfer;

    private Integer whiteInfoCritically;

    private Integer whiteInfoSick;

    private Integer whiteInfoHand;

    private Integer whiteInfoWait;

    private Integer whiteInfoBorn;

    private Integer whiteInfoWaitborn;

    private Integer whiteInfoTransferout;

    private Integer whiteInfoOut;

    private Integer whiteInfoDie;

    private Integer midInfoAll;

    private Integer midInfoIn;

    private Integer midInfoTransfer;

    private Integer midInfoCritically;

    private Integer midInfoSick;

    private Integer midInfoHand;

    private Integer midInfoWait;

    private Integer midInfoBorn;

    private Integer midInfoWaitborn;

    private Integer midInfoTransferout;

    private Integer midInfoOut;

    private Integer midInfoDie;

    private Integer nightInfoAll;

    private Integer nightInfoIn;

    private Integer nightInfoTransfer;

    private Integer nightInfoCritically;

    private Integer nightInfoSick;

    private Integer nightInfoHand;

    private Integer nightInfoWait;

    private Integer nightInfoBorn;

    private Integer nightInfoWaitborn;

    private Integer nightInfoTransferout;

    private Integer nightInfoOut;

    private Integer nightInfoDie;

    private String remark;

    private String remarkMid;

    private String remarkNight;

    private Long handover;

    private Date dateHandover;

    private Long successor;

    private Date dateSuccessor;

    private Long handoverMid;

    private Date dateHandoverMid;

    private Long successorMid;

    private Date dateSuccessorMid;

    private Long handoverNight;

    private Date dateHandoverNight;

    private Long successorNight;

    private Date dateSuccessorNight;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward == null ? null : ward.trim();
    }

    public Integer getWhiteInfoAll() {
        return whiteInfoAll;
    }

    public void setWhiteInfoAll(Integer whiteInfoAll) {
        this.whiteInfoAll = whiteInfoAll;
    }

    public Integer getWhiteInfoIn() {
        return whiteInfoIn;
    }

    public void setWhiteInfoIn(Integer whiteInfoIn) {
        this.whiteInfoIn = whiteInfoIn;
    }

    public Integer getWhiteInfoTransfer() {
        return whiteInfoTransfer;
    }

    public void setWhiteInfoTransfer(Integer whiteInfoTransfer) {
        this.whiteInfoTransfer = whiteInfoTransfer;
    }

    public Integer getWhiteInfoCritically() {
        return whiteInfoCritically;
    }

    public void setWhiteInfoCritically(Integer whiteInfoCritically) {
        this.whiteInfoCritically = whiteInfoCritically;
    }

    public Integer getWhiteInfoSick() {
        return whiteInfoSick;
    }

    public void setWhiteInfoSick(Integer whiteInfoSick) {
        this.whiteInfoSick = whiteInfoSick;
    }

    public Integer getWhiteInfoHand() {
        return whiteInfoHand;
    }

    public void setWhiteInfoHand(Integer whiteInfoHand) {
        this.whiteInfoHand = whiteInfoHand;
    }

    public Integer getWhiteInfoWait() {
        return whiteInfoWait;
    }

    public void setWhiteInfoWait(Integer whiteInfoWait) {
        this.whiteInfoWait = whiteInfoWait;
    }

    public Integer getWhiteInfoBorn() {
        return whiteInfoBorn;
    }

    public void setWhiteInfoBorn(Integer whiteInfoBorn) {
        this.whiteInfoBorn = whiteInfoBorn;
    }

    public Integer getWhiteInfoWaitborn() {
        return whiteInfoWaitborn;
    }

    public void setWhiteInfoWaitborn(Integer whiteInfoWaitborn) {
        this.whiteInfoWaitborn = whiteInfoWaitborn;
    }

    public Integer getWhiteInfoTransferout() {
        return whiteInfoTransferout;
    }

    public void setWhiteInfoTransferout(Integer whiteInfoTransferout) {
        this.whiteInfoTransferout = whiteInfoTransferout;
    }

    public Integer getWhiteInfoOut() {
        return whiteInfoOut;
    }

    public void setWhiteInfoOut(Integer whiteInfoOut) {
        this.whiteInfoOut = whiteInfoOut;
    }

    public Integer getWhiteInfoDie() {
        return whiteInfoDie;
    }

    public void setWhiteInfoDie(Integer whiteInfoDie) {
        this.whiteInfoDie = whiteInfoDie;
    }

    public Integer getMidInfoAll() {
        return midInfoAll;
    }

    public void setMidInfoAll(Integer midInfoAll) {
        this.midInfoAll = midInfoAll;
    }

    public Integer getMidInfoIn() {
        return midInfoIn;
    }

    public void setMidInfoIn(Integer midInfoIn) {
        this.midInfoIn = midInfoIn;
    }

    public Integer getMidInfoTransfer() {
        return midInfoTransfer;
    }

    public void setMidInfoTransfer(Integer midInfoTransfer) {
        this.midInfoTransfer = midInfoTransfer;
    }

    public Integer getMidInfoCritically() {
        return midInfoCritically;
    }

    public void setMidInfoCritically(Integer midInfoCritically) {
        this.midInfoCritically = midInfoCritically;
    }

    public Integer getMidInfoSick() {
        return midInfoSick;
    }

    public void setMidInfoSick(Integer midInfoSick) {
        this.midInfoSick = midInfoSick;
    }

    public Integer getMidInfoHand() {
        return midInfoHand;
    }

    public void setMidInfoHand(Integer midInfoHand) {
        this.midInfoHand = midInfoHand;
    }

    public Integer getMidInfoWait() {
        return midInfoWait;
    }

    public void setMidInfoWait(Integer midInfoWait) {
        this.midInfoWait = midInfoWait;
    }

    public Integer getMidInfoBorn() {
        return midInfoBorn;
    }

    public void setMidInfoBorn(Integer midInfoBorn) {
        this.midInfoBorn = midInfoBorn;
    }

    public Integer getMidInfoWaitborn() {
        return midInfoWaitborn;
    }

    public void setMidInfoWaitborn(Integer midInfoWaitborn) {
        this.midInfoWaitborn = midInfoWaitborn;
    }

    public Integer getMidInfoTransferout() {
        return midInfoTransferout;
    }

    public void setMidInfoTransferout(Integer midInfoTransferout) {
        this.midInfoTransferout = midInfoTransferout;
    }

    public Integer getMidInfoOut() {
        return midInfoOut;
    }

    public void setMidInfoOut(Integer midInfoOut) {
        this.midInfoOut = midInfoOut;
    }

    public Integer getMidInfoDie() {
        return midInfoDie;
    }

    public void setMidInfoDie(Integer midInfoDie) {
        this.midInfoDie = midInfoDie;
    }

    public Integer getNightInfoAll() {
        return nightInfoAll;
    }

    public void setNightInfoAll(Integer nightInfoAll) {
        this.nightInfoAll = nightInfoAll;
    }

    public Integer getNightInfoIn() {
        return nightInfoIn;
    }

    public void setNightInfoIn(Integer nightInfoIn) {
        this.nightInfoIn = nightInfoIn;
    }

    public Integer getNightInfoTransfer() {
        return nightInfoTransfer;
    }

    public void setNightInfoTransfer(Integer nightInfoTransfer) {
        this.nightInfoTransfer = nightInfoTransfer;
    }

    public Integer getNightInfoCritically() {
        return nightInfoCritically;
    }

    public void setNightInfoCritically(Integer nightInfoCritically) {
        this.nightInfoCritically = nightInfoCritically;
    }

    public Integer getNightInfoSick() {
        return nightInfoSick;
    }

    public void setNightInfoSick(Integer nightInfoSick) {
        this.nightInfoSick = nightInfoSick;
    }

    public Integer getNightInfoHand() {
        return nightInfoHand;
    }

    public void setNightInfoHand(Integer nightInfoHand) {
        this.nightInfoHand = nightInfoHand;
    }

    public Integer getNightInfoWait() {
        return nightInfoWait;
    }

    public void setNightInfoWait(Integer nightInfoWait) {
        this.nightInfoWait = nightInfoWait;
    }

    public Integer getNightInfoBorn() {
        return nightInfoBorn;
    }

    public void setNightInfoBorn(Integer nightInfoBorn) {
        this.nightInfoBorn = nightInfoBorn;
    }

    public Integer getNightInfoWaitborn() {
        return nightInfoWaitborn;
    }

    public void setNightInfoWaitborn(Integer nightInfoWaitborn) {
        this.nightInfoWaitborn = nightInfoWaitborn;
    }

    public Integer getNightInfoTransferout() {
        return nightInfoTransferout;
    }

    public void setNightInfoTransferout(Integer nightInfoTransferout) {
        this.nightInfoTransferout = nightInfoTransferout;
    }

    public Integer getNightInfoOut() {
        return nightInfoOut;
    }

    public void setNightInfoOut(Integer nightInfoOut) {
        this.nightInfoOut = nightInfoOut;
    }

    public Integer getNightInfoDie() {
        return nightInfoDie;
    }

    public void setNightInfoDie(Integer nightInfoDie) {
        this.nightInfoDie = nightInfoDie;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getRemarkMid() {
        return remarkMid;
    }

    public void setRemarkMid(String remarkMid) {
        this.remarkMid = remarkMid == null ? null : remarkMid.trim();
    }

    public String getRemarkNight() {
        return remarkNight;
    }

    public void setRemarkNight(String remarkNight) {
        this.remarkNight = remarkNight == null ? null : remarkNight.trim();
    }

    public Long getHandover() {
        return handover;
    }

    public void setHandover(Long handover) {
        this.handover = handover;
    }

    public Date getDateHandover() {
        return dateHandover;
    }

    public void setDateHandover(Date dateHandover) {
        this.dateHandover = dateHandover;
    }

    public Long getSuccessor() {
        return successor;
    }

    public void setSuccessor(Long successor) {
        this.successor = successor;
    }

    public Date getDateSuccessor() {
        return dateSuccessor;
    }

    public void setDateSuccessor(Date dateSuccessor) {
        this.dateSuccessor = dateSuccessor;
    }

    public Long getHandoverMid() {
        return handoverMid;
    }

    public void setHandoverMid(Long handoverMid) {
        this.handoverMid = handoverMid;
    }

    public Date getDateHandoverMid() {
        return dateHandoverMid;
    }

    public void setDateHandoverMid(Date dateHandoverMid) {
        this.dateHandoverMid = dateHandoverMid;
    }

    public Long getSuccessorMid() {
        return successorMid;
    }

    public void setSuccessorMid(Long successorMid) {
        this.successorMid = successorMid;
    }

    public Date getDateSuccessorMid() {
        return dateSuccessorMid;
    }

    public void setDateSuccessorMid(Date dateSuccessorMid) {
        this.dateSuccessorMid = dateSuccessorMid;
    }

    public Long getHandoverNight() {
        return handoverNight;
    }

    public void setHandoverNight(Long handoverNight) {
        this.handoverNight = handoverNight;
    }

    public Date getDateHandoverNight() {
        return dateHandoverNight;
    }

    public void setDateHandoverNight(Date dateHandoverNight) {
        this.dateHandoverNight = dateHandoverNight;
    }

    public Long getSuccessorNight() {
        return successorNight;
    }

    public void setSuccessorNight(Long successorNight) {
        this.successorNight = successorNight;
    }

    public Date getDateSuccessorNight() {
        return dateSuccessorNight;
    }

    public void setDateSuccessorNight(Date dateSuccessorNight) {
        this.dateSuccessorNight = dateSuccessorNight;
    }


}