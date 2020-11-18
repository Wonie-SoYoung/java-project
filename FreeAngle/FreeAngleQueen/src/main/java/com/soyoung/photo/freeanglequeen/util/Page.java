package com.soyoung.photo.freeanglequeen.util;

import java.util.List;

public class Page {
    private int currPage=1;//当前页数
    private int pageSize=10;//每页显示的记录数
    private int totalCount;//总记录数
    private int totalPage;//总页数
    private Object lists;//每页的显示的数据

    public Page() {
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage=(totalCount % pageSize==0)?(totalCount/pageSize):((totalCount/pageSize)+1);
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public Object getLists() {
        return lists;
    }

    public void setLists(Object lists) {
        this.lists = lists;
    }

    @Override
    public String toString() {
        return "Page{" +
                "currPage=" + currPage +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", lists=" + lists +
                '}';
    }
}
