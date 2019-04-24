package com.jinkan.www.fastandroid.model.repository.http.bean;

/**
 * Created by Sampson on 2019/4/19.
 * FastAndroid
 */
public class PageBean<T> {

    /**
     * header : {"code":0,"msg":"getFocusPictureList success!"}
     * body : {"data":{"total":10,"currentPage":1,"currentPgeNumber":2,"pageNumber":10,"totalPage":1,"hasNextPage":false,"rows":[{"picturePath":"http://wj.haoju.me/bd33215507a444ca96082ebb594e5c6f.png"},{"picturePath":"http://wj.haoju.me/58ab2a95c1394ac8b36de37f9c5701e8.png"}]}}
     */

    private HeaderBean header;
    private PageBodyBean<T> body;

    public HeaderBean getHeader() {
        return header;
    }

    public void setHeader(HeaderBean header) {
        this.header = header;
    }

    public PageBodyBean<T> getBody() {
        return body;
    }

    public void setBody(PageBodyBean<T> body) {
        this.body = body;
    }


}
