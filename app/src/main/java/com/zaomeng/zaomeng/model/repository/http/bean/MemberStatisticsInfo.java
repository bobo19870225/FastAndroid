package com.zaomeng.zaomeng.model.repository.http.bean;

/**
 * Created by Sampson on 2019-06-02.
 * FastAndroid
 */
public class MemberStatisticsInfo {

    /**
     * id : 2c9051726a9b0175016ab994bc4f0352
     * name : 会员卢声波的资金统计
     * memberID : 2c9051726a646c47016a647e046e0006
     * applicationID : 8a2f462a66cac9130166ccd9c99304f4
     * point : 60
     * pointTotalUsed : 0
     * pointTotal : 60
     * cashBalance : 0.0
     * cashTotalRecharge : 0.0
     * memberBonusNum : 1
     */

    private String id;
    private String name;
    private String memberID;
    private String applicationID;
    private int point;
    private int pointTotalUsed;
    private int pointTotal;
    private double cashBalance;
    private double cashTotalRecharge;
    private int memberBonusNum;

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

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getPointTotalUsed() {
        return pointTotalUsed;
    }

    public void setPointTotalUsed(int pointTotalUsed) {
        this.pointTotalUsed = pointTotalUsed;
    }

    public int getPointTotal() {
        return pointTotal;
    }

    public void setPointTotal(int pointTotal) {
        this.pointTotal = pointTotal;
    }

    public double getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(double cashBalance) {
        this.cashBalance = cashBalance;
    }

    public double getCashTotalRecharge() {
        return cashTotalRecharge;
    }

    public void setCashTotalRecharge(double cashTotalRecharge) {
        this.cashTotalRecharge = cashTotalRecharge;
    }

    public int getMemberBonusNum() {
        return memberBonusNum;
    }

    public void setMemberBonusNum(int memberBonusNum) {
        this.memberBonusNum = memberBonusNum;
    }
}
