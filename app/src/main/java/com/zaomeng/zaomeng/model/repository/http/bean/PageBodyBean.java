package com.zaomeng.zaomeng.model.repository.http.bean;

/**
 * Created by Sampson on 2019/4/19.
 * FastAndroid
 */
public class PageBodyBean<T> {
    /**
     * data : {"total":10,"currentPage":1,"currentPgeNumber":2,"pageNumber":10,"totalPage":1,"hasNextPage":false,"rows":[{"picturePath":"http://wj.haoju.me/bd33215507a444ca96082ebb594e5c6f.png"},{"picturePath":"http://wj.haoju.me/58ab2a95c1394ac8b36de37f9c5701e8.png"}]}
     */

    private PageDataBean<T> data;

    public PageDataBean<T> getData() {
        return data;
    }

    public void setData(PageDataBean<T> data) {
        this.data = data;
    }

}