package com.jinkan.www.fastandroid.model.repository;

/**
 * Created by Sampson on 2019/2/26.
 * FastAndroid
 */
public class NetWorkState {
    private Status status;
    private String msg;

    public NetWorkState(Status status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static NetWorkState loaded() {
        return new NetWorkState(Status.SUCCESS, null);
    }

    public static NetWorkState loading() {
        return new NetWorkState(Status.RUNNING, null);
    }

    public static NetWorkState error(String msg) {
        return new NetWorkState(Status.FAILED, msg);
    }
}
