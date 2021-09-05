package com.didarakulov.restaurantvoting.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageDto<T> {
    private ListDto<T> data;
    private int pageNumber;
    private long totalElemets;
    private long totalPages;
    private String error;

    public PageDto(Page pagingInfo, List<T> dtoList){
        this.setData(new ListDto<>(dtoList));
        this.setPageNumber((pagingInfo.getNumber()));
        this.setTotalElemets(pagingInfo.getTotalElements());
        this.setTotalPages(pagingInfo.getTotalPages());
    }

    public PageDto(String error){
        this.setError(error);
    }

    public ListDto<T> getData() {
        return data;
    }

    public void setData(ListDto<T> data) {
        this.data = data;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public long getTotalElemets() {
        return totalElemets;
    }

    public void setTotalElemets(long totalElemets) {
        this.totalElemets = totalElemets;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
