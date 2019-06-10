package com.zaomeng.zaomeng.model.repository.http.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sampson on 2019-05-04.
 * FastAndroid
 */
public class MemberShopBean implements Parcelable {


    /**
     * id : 2c9051726af4a957016af821a66f001b
     * memberID : 2c9051726a646c47016a647e046e0006
     * shopCategoryID : a74eeee1-750c-497d-afaa-cd867014f5f8
     * shopCategoryName : 西餐
     * applicationID : 8a2f462a66cac9130166ccd9c99304f4
     * name : 上海最好吃的早餐店
     * shopType : 0
     * bizStatus : 0
     * logoURL : null
     * shopFaceImage : http://qn.wgclm.com/FjCpe5CRe3TpR_KRImWu1q2Nmfkz
     * businessImage : http://qn.wgclm.com/Fo-u99uq_8VJFrayKoiNGTa_UdxJ
     * address : 上海市浦东新区办公中心
     * contact : 卢声波
     * contactPhone : 18101603953
     * contactIdCardFaceImage : http://qn.wgclm.com/FtACAkLkn0rrdjoc9yjoMrO3GUZg
     * contactIdCardBackImage : http://qn.wgclm.com/FsodFg3UsLOdQjMnBtPWBAz5HJ13
     * verifyStatus : 1
     * verifyDate : null
     * verifyDateStr : null
     */

    private String id;
    private String memberID;
    private String shopCategoryID;
    private String shopCategoryName;
    private String applicationID;
    private String name;
    private int shopType;
    private int bizStatus;
    private String logoURL;
    private String shopFaceImage;
    private String businessImage;
    private String address;
    private String contact;
    private String contactPhone;
    private String contactIdCardFaceImage;
    private String contactIdCardBackImage;
    private int verifyStatus;
    private String verifyDate;
    private String verifyDateStr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getShopCategoryID() {
        return shopCategoryID;
    }

    public void setShopCategoryID(String shopCategoryID) {
        this.shopCategoryID = shopCategoryID;
    }

    public String getShopCategoryName() {
        return shopCategoryName;
    }

    public void setShopCategoryName(String shopCategoryName) {
        this.shopCategoryName = shopCategoryName;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getShopType() {
        return shopType;
    }

    public void setShopType(int shopType) {
        this.shopType = shopType;
    }

    public int getBizStatus() {
        return bizStatus;
    }

    public void setBizStatus(int bizStatus) {
        this.bizStatus = bizStatus;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public String getShopFaceImage() {
        return shopFaceImage;
    }

    public void setShopFaceImage(String shopFaceImage) {
        this.shopFaceImage = shopFaceImage;
    }

    public String getBusinessImage() {
        return businessImage;
    }

    public void setBusinessImage(String businessImage) {
        this.businessImage = businessImage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactIdCardFaceImage() {
        return contactIdCardFaceImage;
    }

    public void setContactIdCardFaceImage(String contactIdCardFaceImage) {
        this.contactIdCardFaceImage = contactIdCardFaceImage;
    }

    public String getContactIdCardBackImage() {
        return contactIdCardBackImage;
    }

    public void setContactIdCardBackImage(String contactIdCardBackImage) {
        this.contactIdCardBackImage = contactIdCardBackImage;
    }

    public int getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(int verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getVerifyDate() {
        return verifyDate;
    }

    public void setVerifyDate(String verifyDate) {
        this.verifyDate = verifyDate;
    }

    public String getVerifyDateStr() {
        return verifyDateStr;
    }

    public void setVerifyDateStr(String verifyDateStr) {
        this.verifyDateStr = verifyDateStr;
    }

    public MemberShopBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.memberID);
        dest.writeString(this.shopCategoryID);
        dest.writeString(this.shopCategoryName);
        dest.writeString(this.applicationID);
        dest.writeString(this.name);
        dest.writeInt(this.shopType);
        dest.writeInt(this.bizStatus);
        dest.writeString(this.logoURL);
        dest.writeString(this.shopFaceImage);
        dest.writeString(this.businessImage);
        dest.writeString(this.address);
        dest.writeString(this.contact);
        dest.writeString(this.contactPhone);
        dest.writeString(this.contactIdCardFaceImage);
        dest.writeString(this.contactIdCardBackImage);
        dest.writeInt(this.verifyStatus);
        dest.writeString(this.verifyDate);
        dest.writeString(this.verifyDateStr);
    }

    protected MemberShopBean(Parcel in) {
        this.id = in.readString();
        this.memberID = in.readString();
        this.shopCategoryID = in.readString();
        this.shopCategoryName = in.readString();
        this.applicationID = in.readString();
        this.name = in.readString();
        this.shopType = in.readInt();
        this.bizStatus = in.readInt();
        this.logoURL = in.readString();
        this.shopFaceImage = in.readString();
        this.businessImage = in.readString();
        this.address = in.readString();
        this.contact = in.readString();
        this.contactPhone = in.readString();
        this.contactIdCardFaceImage = in.readString();
        this.contactIdCardBackImage = in.readString();
        this.verifyStatus = in.readInt();
        this.verifyDate = in.readString();
        this.verifyDateStr = in.readString();
    }

    public static final Creator<MemberShopBean> CREATOR = new Creator<MemberShopBean>() {
        @Override
        public MemberShopBean createFromParcel(Parcel source) {
            return new MemberShopBean(source);
        }

        @Override
        public MemberShopBean[] newArray(int size) {
            return new MemberShopBean[size];
        }
    };
}
