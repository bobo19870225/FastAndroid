package com.jinkan.www.fastandroid.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoaderInterface;

/**
 * Created by Sampson on 2019/3/29.
 * FastAndroid
 */
public class GlideImageLoader implements ImageLoaderInterface<ImageView> {


    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).into((ImageView) imageView);
    }

    @Override
    public ImageView createImageView(Context context) {
        return null;
    }
}
