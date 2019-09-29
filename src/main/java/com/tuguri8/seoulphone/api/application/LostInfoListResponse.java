package com.tuguri8.seoulphone.api.application;

import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.entity.LostInfo;

import java.util.List;

public class LostInfoListResponse {
    List<LostInfo> items;
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

    public List<LostInfo> getItems() {
        return items;
    }

    public void setItems(List<LostInfo> items) {
        this.items = items;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }
}
