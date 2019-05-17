package com.zaomeng.zaomeng.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Sampson on 2019-05-17.
 * FastAndroid
 */
@Singleton
public class GlideUtils {

    private int radius = 8;
    private int width = 400;
    private int height = 400;

    @Inject
    public GlideUtils() {
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void into(ImageView imageView, String url) {
        //设置图片圆角角度
        RoundedCorners roundedCorners = new RoundedCorners(radius);
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(width, height);
        Glide.with(imageView).load(url).apply(options).into(imageView);
    }

}
