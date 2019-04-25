package com.zaomeng.zaomeng.model.repository.http;

import java.util.List;

/**
 * Created by Sampson on 2018/6/11.
 * StockApp
 */
public class Message<T> {


    private int code;
    private String msg;
    private PageModel pageModel;
    private T content;
    private List<T> contentList;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public PageModel getPageModel() {
        return pageModel;
    }

    public void setPageModel(PageModel pageModel) {
        this.pageModel = pageModel;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public List<T> getContentList() {
        return contentList;
    }

    public void setContentList(List<T> contentList) {
        this.contentList = contentList;
    }


}
