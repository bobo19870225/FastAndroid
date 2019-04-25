package com.zaomeng.zaomeng.model.repository.http;

/**
 * Created by Sampson on 2018/6/14.
 * StockApp
 */
public class PageModel {
    /**
     * 分页总数据条数
     */
    private int recordCount;
    /**
     * 当前页面
     */
    private int pageIndex;
    /**
     * 每页分多少条数据
     */
    private int pageSize;
    /**
     * 总页数
     */
    private int totalSize;

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }
}
