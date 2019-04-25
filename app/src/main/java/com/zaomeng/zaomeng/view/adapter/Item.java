package com.zaomeng.zaomeng.view.adapter;

/**
 * Created by Sampson on 2019-04-22.
 * FastAndroid
 */
public class Item<T> {
    private T data;
    private int type;

    public Item(T data, int type) {
        this.data = data;
        this.type = type;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
