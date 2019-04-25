package com.zaomeng.zaomeng.view.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.tmall.wireless.tangram.dataparser.concrete.Style;
import com.tmall.wireless.tangram.structure.BaseCell;
import com.tmall.wireless.tangram.structure.view.ITangramViewLifeCycle;
import com.zaomeng.zaomeng.R;

import org.json.JSONException;

/**
 * Created by Sampson on 2019/4/12.
 * FastAndroid
 */
public class LocationView extends LinearLayout implements ITangramViewLifeCycle {
    private final int DEFAULT_ICON_SIZE = Style.dp2px(100);
    public ImageView icon;

    public TextView titleTextView;
    private BaseCell cell;

    public LocationView(Context context) {
        super(context);
        init(context);
    }

    public LocationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LocationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(getContext(), R.layout.item_location, this);
        titleTextView = findViewById(R.id.title);
        icon = findViewById(R.id.icon);

//        setOrientation(HORIZONTAL);
//        setGravity(Gravity.CENTER_HORIZONTAL);
//        setBackground(context.getResources().getDrawable(R.drawable.ic_launcher_foreground));
//
//        icon = new ImageView(context);
//        icon.setImageDrawable(context.getResources().getDrawable(R.mipmap.back));
//        icon.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        LayoutParams iconLp = new LayoutParams(DEFAULT_ICON_SIZE, DEFAULT_ICON_SIZE);
//        iconLp.topMargin = Style.dp2px(8);
//        addView(icon, iconLp);
//
//        titleTextView = new TextView(context);
//        titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
//        LayoutParams titleLp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT);
//        titleLp.topMargin = Style.dp2px(4.0);
//        addView(titleTextView, titleLp);

    }

    @Override
    public void cellInited(BaseCell cell) {
        this.cell = cell;
    }

    @Override
    public void postBindView(BaseCell cell) {
        if (cell.style != null) {
            if (cell.style.extras != null) {
                ViewGroup.LayoutParams lp;
                lp = getLayoutParams();
                try {
                    lp.height = cell.style.extras.getInt("height");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setLayoutParams(lp);
            }
        }
        icon.setOnClickListener(cell);
        titleTextView.setText(
                cell.id + " pos: " + cell.pos + " " + "| " + cell
                        .optParam("msg"));
    }

    @Override
    public void postUnBindView(BaseCell cell) {

    }
}
