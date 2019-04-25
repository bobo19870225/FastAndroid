package com.zaomeng.zaomeng.model.repository.http.bean;

import java.util.List;

/**
 * Created by Sampson on 2019/4/19.
 * FastAndroid
 */
public class PageDataBean<T> {
    /**
     * total : 10
     * currentPage : 1
     * currentPgeNumber : 2
     * pageNumber : 10
     * totalPage : 1
     * hasNextPage : false
     * rows : [{"picturePath":"http://wj.haoju.me/bd33215507a444ca96082ebb594e5c6f.png"},{"picturePath":"http://wj.haoju.me/58ab2a95c1394ac8b36de37f9c5701e8.png"}]
     */

    private int total;
    private int currentPage;
    private int currentPgeNumber;
    private int pageNumber;
    private int totalPage;
    private boolean hasNextPage;
    private List<T> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentPgeNumber() {
        return currentPgeNumber;
    }

    public void setCurrentPgeNumber(int currentPgeNumber) {
        this.currentPgeNumber = currentPgeNumber;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}
