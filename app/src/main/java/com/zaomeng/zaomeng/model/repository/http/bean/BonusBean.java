package com.zaomeng.zaomeng.model.repository.http.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sampson on 2019-05-16.
 * FastAndroid
 */
public class BonusBean implements Parcelable {

    /**
     * id : 0012216e3ca04c3088d3b3353a87adc4
     * name : 商户入驻
     * amount : 8.88
     * startDate : 1557800137000
     * startDateStr : 2019-05-14
     * endDate : 1558577720000
     * endDateStr : 2019-05-23
     * useTime : 1557627341000
     * useTimeStr : 2019-05-12 10:15:41
     * printCode : HB1001
     * status : 2
     */

    private String id;
    private String name;
    private double amount;
    private long startDate;
    private String startDateStr;
    private long endDate;
    private String endDateStr;
    private long useTime;
    private String useTimeStr;
    private String printCode;
    private int status;

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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public String getStartDateStr() {
        return startDateStr;
    }

    public void setStartDateStr(String startDateStr) {
        this.startDateStr = startDateStr;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public String getEndDateStr() {
        return endDateStr;
    }

    public void setEndDateStr(String endDateStr) {
        this.endDateStr = endDateStr;
    }

    public long getUseTime() {
        return useTime;
    }

    public void setUseTime(long useTime) {
        this.useTime = useTime;
    }

    public String getUseTimeStr() {
        return useTimeStr;
    }

    public void setUseTimeStr(String useTimeStr) {
        this.useTimeStr = useTimeStr;
    }

    public String getPrintCode() {
        return printCode;
    }

    public void setPrintCode(String printCode) {
        this.printCode = printCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeDouble(this.amount);
        dest.writeLong(this.startDate);
        dest.writeString(this.startDateStr);
        dest.writeLong(this.endDate);
        dest.writeString(this.endDateStr);
        dest.writeLong(this.useTime);
        dest.writeString(this.useTimeStr);
        dest.writeString(this.printCode);
        dest.writeInt(this.status);
    }

    public BonusBean() {
    }

    protected BonusBean(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.amount = in.readDouble();
        this.startDate = in.readLong();
        this.startDateStr = in.readString();
        this.endDate = in.readLong();
        this.endDateStr = in.readString();
        this.useTime = in.readLong();
        this.useTimeStr = in.readString();
        this.printCode = in.readString();
        this.status = in.readInt();
    }

    public static final Parcelable.Creator<BonusBean> CREATOR = new Parcelable.Creator<BonusBean>() {
        @Override
        public BonusBean createFromParcel(Parcel source) {
            return new BonusBean(source);
        }

        @Override
        public BonusBean[] newArray(int size) {
            return new BonusBean[size];
        }
    };
}
