package com.zaomeng.zaomeng.model.repository.http.bean;

/**
 * Created by Sampson on 2019/4/19.
 * FastAndroid
 */
public class BodyBean<T> {
    /**
     * data : {"id":"dsfsdfdsf","goodsCategoryID":"6ba58046-7eb2-4f11-bbb3-b934abeb29a8","name":"2","shortName":"1","showName":"2","description":"2","largerImage":"http://wj.haoju.me/3883e6c9761a49788368bcd7a369ab0c.png","littleImage":"http://wj.haoju.me/3883e6c9761a49788368bcd7a369ab0c.png","standPrice":0,"realPrice":1,"stockNumber":1,"stockOut":1,"salesUnit":"1","brandName":"test品牌3","showPrice":0}
     */

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
