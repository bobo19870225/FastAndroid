package com.jinkan.www.fastandroid.model.repository.http.bean;

/**
 * Created by Sampson on 2019/4/19.
 * FastAndroid
 */
public class BodyBean<T> {
    /**
     * data : {"total":10,"currentPage":1,"currentPgeNumber":2,"pageNumber":10,"totalPage":1,"hasNextPage":false,"rows":[{"picturePath":"http://wj.haoju.me/bd33215507a444ca96082ebb594e5c6f.png"},{"picturePath":"http://wj.haoju.me/58ab2a95c1394ac8b36de37f9c5701e8.png"}]}
     */

    private DataBean<T> data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean<T> data) {
        this.data = data;
    }

}
