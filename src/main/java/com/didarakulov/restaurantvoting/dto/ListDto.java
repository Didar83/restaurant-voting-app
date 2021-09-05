package com.didarakulov.restaurantvoting.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class ListDto<T> {
    public static final ListDto NULL_OBJECT = null;

    private int size;
    private List<T> data = new ArrayList<>();
    private String responseInfo;
    public ListDto(List<T> data){
        if(nonNull(data) && !data.isEmpty()){
            setData(data);
        }
    }

    public void setData(List<T> data){
        this.size = data.size();
        this.data = data;
    }

    public boolean isEmpty(){
        return size <= 0;
    }

    public List<T> getData(){
        return data;
    }

    public String getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(String responseInfo) {
        this.responseInfo = responseInfo;
    }
}
