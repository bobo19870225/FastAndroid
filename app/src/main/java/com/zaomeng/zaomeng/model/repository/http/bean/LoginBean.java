package com.zaomeng.zaomeng.model.repository.http.bean;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Sampson on 2019/4/19.
 * FastAndroid
 */
@Entity(tableName = "user")
public class LoginBean {
    /**
     * id : 21432141wsfwerq123
     * applicationID : 8a2f462a66cac9130166ccd9c99304f4
     * companyID : 8a2f462a66cac9130166ccd7c22b04e9
     * name : 小铁
     * shortName : 小铁
     * loginName : 13162617998
     * phone : 13162617998
     * weixinToken : null
     * weixinUnionID : null
     * recommandID : 21432141wsfwerq123
     * recommandCode : 00001
     * recommandChain : 00001
     * siteID : 8a2f462a6763d1d7016763e2c07f0049
     * avatarURL : http://admin.haoju.me:8082/kpbase//group/M00/85/6A/5FA8-8338-4468-92AB-EED0E66BEACF.jpeg?w=23&h=23
     *
     */
    @PrimaryKey
    @NonNull
    private String id = "";
    private String applicationID;
    private String companyID;
    private String name;
    private String shortName;
    private String loginName;
    private String phone;
    private String weixinToken;
    private String weixinUnionID;
    private String recommandID;
    private String recommandCode;
    private String recommandChain;
    private String siteID;
    private String avatarURL;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWeixinToken() {
        return weixinToken;
    }

    public void setWeixinToken(String weixinToken) {
        this.weixinToken = weixinToken;
    }

    public String getWeixinUnionID() {
        return weixinUnionID;
    }

    public void setWeixinUnionID(String weixinUnionID) {
        this.weixinUnionID = weixinUnionID;
    }

    public String getRecommandID() {
        return recommandID;
    }

    public void setRecommandID(String recommandID) {
        this.recommandID = recommandID;
    }

    public String getRecommandCode() {
        return recommandCode;
    }

    public void setRecommandCode(String recommandCode) {
        this.recommandCode = recommandCode;
    }

    public String getRecommandChain() {
        return recommandChain;
    }

    public void setRecommandChain(String recommandChain) {
        this.recommandChain = recommandChain;
    }

    public String getSiteID() {
        return siteID;
    }

    public void setSiteID(String siteID) {
        this.siteID = siteID;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }
}
