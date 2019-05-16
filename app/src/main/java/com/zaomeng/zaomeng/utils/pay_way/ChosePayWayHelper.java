package com.zaomeng.zaomeng.utils.pay_way;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.zaomeng.zaomeng.R;

/**
 * Created by Sampson on 2019-04-30.
 * FastAndroid
 */
public class ChosePayWayHelper {
    private IChosePayWay IChosePayWay;
    private BottomSheetDialog bottomSheetDialog;
    private int payWay;

    public ChosePayWayHelper(@NonNull Context context, IChosePayWay IChosePayWay) {
        this.IChosePayWay = IChosePayWay;
        bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setCancelable(false);
    }

    public void showPayWayDialog(LayoutInflater layoutInflater) {

        @SuppressLint("InflateParams") View view = layoutInflater.inflate(R.layout.dialog_pay_way, null, false);
        Button ok = view.findViewById(R.id.ok);
        ImageView cancel = view.findViewById(R.id.cancel);
        cancel.setOnClickListener(v -> {
            if (bottomSheetDialog.isShowing())
                bottomSheetDialog.dismiss();
        });
        RadioGroup radioGroup = view.findViewById(R.id.radio_group);
        payWay = radioGroup.getCheckedRadioButtonId();
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> payWay = checkedId);
        bottomSheetDialog.setContentView(view);
        ok.setOnClickListener(v -> {
            IChosePayWay.callBack(payWay);
            if (bottomSheetDialog.isShowing()) {
                bottomSheetDialog.dismiss();
            }
        });

        if (!bottomSheetDialog.isShowing()) {
            bottomSheetDialog.show();
        }
    }
}
