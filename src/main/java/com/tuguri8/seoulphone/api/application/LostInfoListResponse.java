package com.tuguri8.seoulphone.api.application;

import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.entity.LostInfo;

import java.util.List;

public class LostInfoListResponse {
    List<LostInfo> items;
    private Long totalCount;

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
