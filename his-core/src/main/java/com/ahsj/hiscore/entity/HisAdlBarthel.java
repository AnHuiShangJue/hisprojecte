package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HisAdlBarthel extends BaseEntity {
    private Long id;

    private Integer eat;

    private Integer eatrate1;

    private Integer eatrate2;

    private Integer eatrate3;

    private Date dateseat;

    private Integer shower;

    private Integer showerrate1;

    private Integer showerrate2;

    private Date datesshower;

    private Integer modification;

    private Integer modificationrate1;

    private Integer modificationrate2;

    private Date datesmodification;

    private Integer wear;

    private Integer wearrate1;

    private Integer wearrate2;

    private Integer wearrate3;

    private Date dateswear;

    private Integer shit;

    private Integer shitrate1;

    private Integer shitrate2;

    private Integer shitrate3;

    private Date datesshit;

    private Integer pee;

    private Integer peerate1;

    private Integer peerate2;

    private Integer peerate3;

    private Date datespee;

    private Integer toilet;

    private Integer toiletrate1;

    private Integer toiletrate2;

    private Integer toiletrate3;

    private Date datestoilet;

    private Integer bedsidechair;

    private Integer bedsidechairrate1;

    private Integer bedsidechairrate2;

    private Integer bedsidechairrate3;

    private Integer bedsidechairrate4;

    private Date datesbedsidechair;

    private Integer walk;

    private Integer walkrate1;

    private Integer walkrate2;

    private Integer walkrate3;

    private Integer walkrate4;

    private Date dateswalk;

    private Integer stairs;

    private Integer stairsrate1;

    private Integer stairsrate2;

    private Integer stairsrate3;

    private Date datesstair;

    private String diagnosis;

    private String hospitalnumber;

    private Integer score;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEat() {
        return eat;
    }

    public void setEat(Integer eat) {
        this.eat = eat;
    }

    public Integer getEatrate1() {
        return eatrate1;
    }

    public void setEatrate1(Integer eatrate1) {
        this.eatrate1 = eatrate1;
    }

    public Integer getEatrate2() {
        return eatrate2;
    }

    public void setEatrate2(Integer eatrate2) {
        this.eatrate2 = eatrate2;
    }

    public Integer getEatrate3() {
        return eatrate3;
    }

    public void setEatrate3(Integer eatrate3) {
        this.eatrate3 = eatrate3;
    }

    public Date getDateseat() {
        return dateseat;
    }

    public void setDateseat(Date dateseat) {
        this.dateseat = dateseat;
    }



    public Date getDatesshower() {
        return datesshower;
    }

    public void setDatesshower(Date datesshower) {
        this.datesshower = datesshower;
    }

    public Integer getModification() {
        return modification;
    }

    public void setModification(Integer modification) {
        this.modification = modification;
    }

    public Integer getModificationrate1() {
        return modificationrate1;
    }

    public void setModificationrate1(Integer modificationrate1) {
        this.modificationrate1 = modificationrate1;
    }

    public Integer getModificationrate2() {
        return modificationrate2;
    }

    public void setModificationrate2(Integer modificationrate2) {
        this.modificationrate2 = modificationrate2;
    }

    public Date getDatesmodification() {
        return datesmodification;
    }

    public void setDatesmodification(Date datesmodification) {
        this.datesmodification = datesmodification;
    }

    public Integer getWear() {
        return wear;
    }

    public void setWear(Integer wear) {
        this.wear = wear;
    }

    public Integer getWearrate1() {
        return wearrate1;
    }

    public void setWearrate1(Integer wearrate1) {
        this.wearrate1 = wearrate1;
    }

    public Integer getWearrate2() {
        return wearrate2;
    }

    public void setWearrate2(Integer wearrate2) {
        this.wearrate2 = wearrate2;
    }

    public Integer getWearrate3() {
        return wearrate3;
    }

    public void setWearrate3(Integer wearrate3) {
        this.wearrate3 = wearrate3;
    }

    public Date getDateswear() {
        return dateswear;
    }

    public void setDateswear(Date dateswear) {
        this.dateswear = dateswear;
    }

    public Integer getShit() {
        return shit;
    }

    public void setShit(Integer shit) {
        this.shit = shit;
    }

    public Integer getShitrate1() {
        return shitrate1;
    }

    public void setShitrate1(Integer shitrate1) {
        this.shitrate1 = shitrate1;
    }

    public Integer getShitrate2() {
        return shitrate2;
    }

    public void setShitrate2(Integer shitrate2) {
        this.shitrate2 = shitrate2;
    }

    public Integer getShitrate3() {
        return shitrate3;
    }

    public void setShitrate3(Integer shitrate3) {
        this.shitrate3 = shitrate3;
    }

    public Date getDatesshit() {
        return datesshit;
    }

    public void setDatesshit(Date datesshit) {
        this.datesshit = datesshit;
    }

    public Integer getPee() {
        return pee;
    }

    public void setPee(Integer pee) {
        this.pee = pee;
    }

    public Integer getPeerate1() {
        return peerate1;
    }

    public void setPeerate1(Integer peerate1) {
        this.peerate1 = peerate1;
    }

    public Integer getPeerate2() {
        return peerate2;
    }

    public void setPeerate2(Integer peerate2) {
        this.peerate2 = peerate2;
    }

    public Integer getPeerate3() {
        return peerate3;
    }

    public void setPeerate3(Integer peerate3) {
        this.peerate3 = peerate3;
    }

    public Date getDatespee() {
        return datespee;
    }

    public void setDatespee(Date datespee) {
        this.datespee = datespee;
    }

    public Integer getToilet() {
        return toilet;
    }

    public void setToilet(Integer toilet) {
        this.toilet = toilet;
    }

    public Integer getToiletrate1() {
        return toiletrate1;
    }

    public void setToiletrate1(Integer toiletrate1) {
        this.toiletrate1 = toiletrate1;
    }

    public Integer getToiletrate2() {
        return toiletrate2;
    }

    public void setToiletrate2(Integer toiletrate2) {
        this.toiletrate2 = toiletrate2;
    }

    public Integer getToiletrate3() {
        return toiletrate3;
    }

    public void setToiletrate3(Integer toiletrate3) {
        this.toiletrate3 = toiletrate3;
    }

    public Date getDatestoilet() {
        return datestoilet;
    }

    public void setDatestoilet(Date datestoilet) {
        this.datestoilet = datestoilet;
    }

    public Integer getBedsidechair() {
        return bedsidechair;
    }

    public void setBedsidechair(Integer bedsidechair) {
        this.bedsidechair = bedsidechair;
    }

    public Integer getBedsidechairrate1() {
        return bedsidechairrate1;
    }

    public void setBedsidechairrate1(Integer bedsidechairrate1) {
        this.bedsidechairrate1 = bedsidechairrate1;
    }

    public Integer getBedsidechairrate2() {
        return bedsidechairrate2;
    }

    public void setBedsidechairrate2(Integer bedsidechairrate2) {
        this.bedsidechairrate2 = bedsidechairrate2;
    }

    public Integer getBedsidechairrate3() {
        return bedsidechairrate3;
    }

    public void setBedsidechairrate3(Integer bedsidechairrate3) {
        this.bedsidechairrate3 = bedsidechairrate3;
    }

    public Integer getBedsidechairrate4() {
        return bedsidechairrate4;
    }

    public void setBedsidechairrate4(Integer bedsidechairrate4) {
        this.bedsidechairrate4 = bedsidechairrate4;
    }

    public Date getDatesbedsidechair() {
        return datesbedsidechair;
    }

    public void setDatesbedsidechair(Date datesbedsidechair) {
        this.datesbedsidechair = datesbedsidechair;
    }

    public Integer getWalk() {
        return walk;
    }

    public void setWalk(Integer walk) {
        this.walk = walk;
    }

    public Integer getWalkrate1() {
        return walkrate1;
    }

    public void setWalkrate1(Integer walkrate1) {
        this.walkrate1 = walkrate1;
    }

    public Integer getWalkrate2() {
        return walkrate2;
    }

    public void setWalkrate2(Integer walkrate2) {
        this.walkrate2 = walkrate2;
    }

    public Integer getWalkrate3() {
        return walkrate3;
    }

    public void setWalkrate3(Integer walkrate3) {
        this.walkrate3 = walkrate3;
    }

    public Integer getWalkrate4() {
        return walkrate4;
    }

    public void setWalkrate4(Integer walkrate4) {
        this.walkrate4 = walkrate4;
    }

    public Date getDateswalk() {
        return dateswalk;
    }

    public void setDateswalk(Date dateswalk) {
        this.dateswalk = dateswalk;
    }

    public Integer getStairs() {
        return stairs;
    }

    public void setStairs(Integer stairs) {
        this.stairs = stairs;
    }

    public Integer getStairsrate1() {
        return stairsrate1;
    }

    public void setStairsrate1(Integer stairsrate1) {
        this.stairsrate1 = stairsrate1;
    }

    public Integer getStairsrate2() {
        return stairsrate2;
    }

    public void setStairsrate2(Integer stairsrate2) {
        this.stairsrate2 = stairsrate2;
    }

    public Integer getStairsrate3() {
        return stairsrate3;
    }

    public void setStairsrate3(Integer stairsrate3) {
        this.stairsrate3 = stairsrate3;
    }

    public Date getDatesstair() {
        return datesstair;
    }

    public void setDatesstair(Date datesstair) {
        this.datesstair = datesstair;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis == null ? null : diagnosis.trim();
    }

    public String getHospitalnumber() {
        return hospitalnumber;
    }

    public void setHospitalnumber(String hospitalnumber) {
        this.hospitalnumber = hospitalnumber == null ? null : hospitalnumber.trim();
    }


    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getShower() {
        return shower;
    }

    public void setShower(Integer shower) {
        this.shower = shower;
    }

    public Integer getShowerrate1() {
        return showerrate1;
    }

    public void setShowerrate1(Integer showerrate1) {
        this.showerrate1 = showerrate1;
    }

    public Integer getShowerrate2() {
        return showerrate2;
    }

    public void setShowerrate2(Integer showerrate2) {
        this.showerrate2 = showerrate2;
    }

    @Override
    public Date getUpdateDate() {
        return updateDate;
    }

    @Override
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}