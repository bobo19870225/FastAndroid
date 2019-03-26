package com.jinkan.www.fastandroid.model.repository.dataBase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Sampson on 2018/6/11.
 * StockApp
 */
@Entity(tableName = "user")
public class User {
    /**
     * id : 1
     * username : 超级管理员
     * loginname : admin
     * password : 123456
     * status : 2
     * createDate : 1457746468000
     */
    @PrimaryKey
    private int id;
    private String username;
    private String loginname;
    private String password;
    private int status;
    private long createDate;
    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
