package com.zaomeng.zaomeng.model.repository.http.bean;

/**
 * Created by Sampson on 2019-05-06.
 * FastAndroid
 */
public class SignInBean {

    /**
     * id : 2c9051726a85f8a1016a8b25d1b70000
     * name : 会员18101603953,2019-05-06签到
     * operateTime : 1557112672000
     * operateTimeStr : 2019-05-06 11:17:52
     */

    private String id;
    private String name;
    private long operateTime;
    private String operateTimeStr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(long operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperateTimeStr() {
        return operateTimeStr;
    }

    public void setOperateTimeStr(String operateTimeStr) {
        this.operateTimeStr = operateTimeStr;
    }
}
