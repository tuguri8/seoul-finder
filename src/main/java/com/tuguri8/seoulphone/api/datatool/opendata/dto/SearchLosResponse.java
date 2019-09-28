package com.tuguri8.seoulphone.api.datatool.opendata.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchLosResponse {
    @XmlElement(name = "header")
    private Header header;
    @XmlElement(name = "body")
    private Body body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Header {
        @XmlElement(name = "resultCode")
        private String resultCode;
        @XmlElement(name = "resultMsg")
        private String resultMsg;

        public String getResultCode() {
            return resultCode;
        }

        public void setResultCode(String resultCode) {
            this.resultCode = resultCode;
        }

        public String getResultMsg() {
            return resultMsg;
        }

        public void setResultMsg(String resultMsg) {
            this.resultMsg = resultMsg;
        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Body {
        @XmlElement(name = "items")
        private Items items;
        @XmlElement(name = "numOfRows")
        private String numOfRows;
        @XmlElement(name = "pageNo")
        private String pageNo;
        @XmlElement(name = "totalCount")
        private String totalCount;

        public Items getItems() {
            return items;
        }

        public void setItems(Items items) {
            this.items = items;
        }

        public String getNumOfRows() {
            return numOfRows;
        }

        public void setNumOfRows(String numOfRows) {
            this.numOfRows = numOfRows;
        }

        public String getPageNo() {
            return pageNo;
        }

        public void setPageNo(String pageNo) {
            this.pageNo = pageNo;
        }

        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Items {
        @XmlElement(name = "item")
        private List<Item> item;

        public List<Item> getItem() {
            return item;
        }

        public void setItem(List<Item> item) {
            this.item = item;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Item {
        private String atcId;
        private String depPlace;
        private String fdFilePathImg;
        private String fdPrdtNm;
        private String fdSbjt;
        private String fdSn;
        private String fdYmd;
        private String prdtClNm;
        private String rnum;

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
}
