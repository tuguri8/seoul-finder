package com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LOST_INFO")
public class LostInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // 관리ID
    @Column(name = "ATC_ID")
    private String atcId;
    // 보관장소
    @Column(name = "DEP_PLACE")
    private String depPlace;
    // 습득물 사진 이미지명
    @Column(name = "FD_FILE_PATH_IMG")
    private String fdFilePathImg;
    // 물품명
    @Column(name = "FD_PRDT_NM")
    private String fdPrdtNm;
    // 게시제목
    @Column(name = "FD_SBJT")
    private String fdSbjt;
    // 습득순번
    @Column(name = "FD_SN")
    private String fdSn;
    // 습득일자
    @Column(name = "FD_YMD")
    private String fdYmd;
    // 물품분류명
    @Column(name = "PRDT_CLNM")
    private String prdtClNm;
    // 일련번호
    @Column(name = "RNUM")
    private String rnum;
    // 카테고리 대분류 ID
    @Column(name = "PRDT_CL_CD_01")
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAtcId() {
        return atcId;
    }

    public void setAtcId(String atcId) {
        this.atcId = atcId;
    }

    public String getDepPlace() {
        return depPlace;
    }

    public void setDepPlace(String depPlace) {
        this.depPlace = depPlace;
    }

    public String getFdFilePathImg() {
        return fdFilePathImg;
    }

    public void setFdFilePathImg(String fdFilePathImg) {
        this.fdFilePathImg = fdFilePathImg;
    }

    public String getFdPrdtNm() {
        return fdPrdtNm;
    }

    public void setFdPrdtNm(String fdPrdtNm) {
        this.fdPrdtNm = fdPrdtNm;
    }

    public String getFdSbjt() {
        return fdSbjt;
    }

    public void setFdSbjt(String fdSbjt) {
        this.fdSbjt = fdSbjt;
    }

    public String getFdSn() {
        return fdSn;
    }

    public void setFdSn(String fdSn) {
        this.fdSn = fdSn;
    }

    public String getFdYmd() {
        return fdYmd;
    }

    public void setFdYmd(String fdYmd) {
        this.fdYmd = fdYmd;
    }

    public String getPrdtClNm() {
        return prdtClNm;
    }

    public void setPrdtClNm(String prdtClNm) {
        this.prdtClNm = prdtClNm;
    }

    public String getRnum() {
        return rnum;
    }

    public void setRnum(String rnum) {
        this.rnum = rnum;
    }
}
