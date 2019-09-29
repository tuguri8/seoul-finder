package com.tuguri8.seoulphone.api.application;

import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.entity.PhoneInfo;

import java.util.List;

public class PhoneInfoListResponse {
    List<PhoneInfo> items;
    private Long totalCount;
    private Integer numOfRows;
    private Integer pageNo;

    public Integer getNumOfRows() {
        return numOfRows;
    }

    public void setNumOfRows(Integer numOfRows) {
        this.numOfRows = numOfRows;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public List<PhoneInfo> getItems() {
        return items;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setItems(List<PhoneInfo> items) {
        this.items = items;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }
}
