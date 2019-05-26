package com.zaomeng.zaomeng.utils.specification;

/**
 * Created by Sampson on 2019-04-30.
 * FastAndroid
 */
public interface InterfaceShowSpecification {
    void toast(String msg);

    void callBack(String objectID, int qty, String objectFeatureItemID);

    void getPrice(String goodsID, String objectFeatureItemID);
}
