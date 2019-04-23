package com.jinkan.www.fastandroid.view.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;

import com.youth.banner.Banner;

/**
 * Created by Sampson on 2019-04-23.
 * FastAndroid
 */
public class CircularBanner extends Banner {
    public CircularBanner(Context context) {
        super(context);
    }

    public CircularBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircularBanner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Path path = new Path();
        path.addRoundRect(new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight()), 18, 18, Path.Direction.CW);
        canvas.clipPath(path, Region.Op.REPLACE);
        super.dispatchDraw(canvas);
    }
}
