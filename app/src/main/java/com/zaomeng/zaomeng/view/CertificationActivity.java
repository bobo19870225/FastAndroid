package com.zaomeng.zaomeng.view;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityCertificationBinding;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.utils.HttpHelper;
import com.zaomeng.zaomeng.utils.LQRPhotoSelectUtils;
import com.zaomeng.zaomeng.utils.http.BitmapUtils;
import com.zaomeng.zaomeng.view.base.MVVMActivity;
import com.zaomeng.zaomeng.view_model.CertificationVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import javax.inject.Inject;

import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

/**
 * Created by Sampson on 2019/4/15.
 * FastAndroid
 */
public class CertificationActivity extends MVVMActivity<CertificationVM, ActivityCertificationBinding> {
    @Inject
    ViewModelFactory viewModelFactory;
    private LQRPhotoSelectUtils mLqrPhotoSelectUtils;
    private AlertDialog alertDialog;
    @NonNull
    @Override
    protected CertificationVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(CertificationVM.class);
    }

    @Override
    protected void setView() {
        init();
        mViewModel.action.observe(this, s -> {
            if (s.equals("setShopImage")) {
                // 3、调用从图库选取图片方法
                PermissionGen.needPermission(CertificationActivity.this,
                        LQRPhotoSelectUtils.REQ_SELECT_PHOTO,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE}
                );
            }
        });
        mViewModel.ldUpDataImage.observe(this, s -> {
            if (s != null) {
                Gson gson = new Gson();
                Bean bean = gson.fromJson(s, Bean.class);
                showUpDataDialog(false);
                if (bean != null) {
                    Glide.with(mViewDataBinding.imgShop).load((String) bean.getBody().getData()).into(mViewDataBinding.imgShop);
                } else {
                    toast(s);
                }

            }
        });
        mViewModel.ldSubmit.observe(this, beanResource -> {
            String s = new HttpHelper<String>(getApplicationContext()).AnalyticalData(beanResource);
            if (s != null) {
                toast("提交成功！");
            }
        });
    }

    private void init() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_wait, null, false);
        alertDialog = new AlertDialog.Builder(getApplicationContext())
                .setView(view)
                .setCancelable(false)
                .create();
        // 1、创建LQRPhotoSelectUtils（一个Activity对应一个LQRPhotoSelectUtils）
        mLqrPhotoSelectUtils = new LQRPhotoSelectUtils(this, (outputFile, outputUri) -> {
            // 4、当拍照或从图库选取图片成功后回调
            String s = BitmapUtils.compressImageUpload(outputFile.getAbsolutePath());
            mViewModel.uploadImg(s);
            showUpDataDialog(true);
        }, false);//true裁剪，false不裁剪

    }

    private void showUpDataDialog(boolean isShow) {
        if (isShow) {
            alertDialog.show();
        } else if (alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    @PermissionSuccess(requestCode = LQRPhotoSelectUtils.REQ_SELECT_PHOTO)
    private void selectPhoto() {
        mLqrPhotoSelectUtils.selectPhoto();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 2、在Activity中的onActivityResult()方法里与LQRPhotoSelectUtils关联
        mLqrPhotoSelectUtils.attachToActivityForResult(requestCode, resultCode, data);
    }

    @Override
    protected int setToolBarMenu() {
        return 0;
    }

    @Override
    protected String setToolBarTitle() {
        return null;
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_certification;
    }
}
