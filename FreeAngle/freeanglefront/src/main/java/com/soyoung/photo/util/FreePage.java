package com.soyoung.photo.util;

import java.util.List;

public class FreePage {
    //总页数
    private int totalPageCount=0;
    //页面大小,即每页显示记录数
    private int pageSize=0;
    //记录总数
    private int totalCount;
    //当前页码
    private int currPageNo=1;
    //每页数据集合
    private Object list;
    //其他数据
    private Object rest;

    public Object getRest() {
        return rest;
    }

    public void setRest(Object rest) {
        this.rest = rest;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }
    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }
    public int getPageSize() {
        return pageSize;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        if(totalCount>0){
            this.totalCount = totalCount;
            //计算总页数
            this.totalPageCount = (this.totalCount%this.pageSize==0)?(this.totalCount/this.pageSize):((this.totalCount/this.pageSize)+1);
        }

    }
    public int getCurrPageNo() {
        return currPageNo;
    }
    public void setCurrPageNo(int currPageNo) {
        this.currPageNo = currPageNo;
    }
    public Object getList() {
        return list;
    }
    public void setList(Object list) {
        this.list = list;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
