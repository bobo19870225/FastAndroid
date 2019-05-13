package com.zaomeng.zaomeng.view;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityCertificationBinding;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsSuperBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageDataBean;
import com.zaomeng.zaomeng.utils.HttpHelper;
import com.zaomeng.zaomeng.utils.LQRPhotoSelectUtils;
import com.zaomeng.zaomeng.utils.http.BitmapUtils;
import com.zaomeng.zaomeng.view.adapter.shop_type.ShopTypeAdapter;
import com.zaomeng.zaomeng.view.base.MVVMActivity;
import com.zaomeng.zaomeng.view.custom_view.CommonPopupWindow;
import com.zaomeng.zaomeng.view_model.CertificationVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

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
    private List<GoodsSuperBean> listShopType = new ArrayList<>();
    private CommonPopupWindow commonPopupWindow;
    private ShopTypeAdapter shopTypeAdapter;
    private int which = -1;
    @NonNull
    @Override
    protected CertificationVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(CertificationVM.class);
    }

    @Override
    protected void setView() {
        init();
        mViewModel.getShopType().observe(this, pageBeanResource -> {
            PageDataBean<GoodsSuperBean> goodsSuperBeanPageDataBean = new HttpHelper<GoodsSuperBean>(getApplication()).AnalyticalPageData(pageBeanResource);
            if (goodsSuperBeanPageDataBean != null) {
                listShopType = goodsSuperBeanPageDataBean.getRows();
                shopTypeAdapter.setList(listShopType);
            }

        });
        mViewModel.action.observe(this, s -> {
            switch (s) {
                case "setShopImage":
                    which = 0;
                    choseImage();
                    break;
                case "setLicenseImage":
                    which = 1;
                    choseImage();
                    break;
                case "setIcFront":
                    which = 2;
                    // 3、调用从图库选取图片方法
                    choseImage();
                    break;
                case "setIcBack":
                    which = 3;
                    // 3、调用从图库选取图片方法
                    choseImage();
                    break;

                case "choiceType":
                    commonPopupWindow.showAsDropDown(mViewDataBinding.imageType, 0, 0);
                    break;
            }

        });
        mViewModel.ldToast.observe(this, this::toast);
        mViewModel.ldUpDataImage.observe(this, strings -> {
            String s = strings[1];
            switch (strings[0]) {
                case "0":
                    setImage(s, mViewDataBinding.imgShop);
                    break;
                case "1":
                    setImage(s, mViewDataBinding.imgLicense);
                    break;
                case "2":
                    setImage(s, mViewDataBinding.imgIcFront);
                    break;
                case "3":
                    setImage(s, mViewDataBinding.imgIcBack);
                    break;
            }


        });
        mViewModel.ldSubmit.observe(this, beanResource -> {
            String s = new HttpHelper<String>(getApplicationContext()).AnalyticalData(beanResource);
            if (s != null) {
                toast("提交成功！");
            }
        });


    }

    private void choseImage() {
        // 3、调用从图库选取图片方法
        PermissionGen.needPermission(CertificationActivity.this,
                LQRPhotoSelectUtils.REQ_SELECT_PHOTO,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE});
    }

    private void setImage(String s, ImageView image) {
        if (s != null) {
            Gson gson = new Gson();
            Bean bean = gson.fromJson(s, Bean.class);
            showUpDataDialog(false);
            if (bean != null) {
                Glide.with(image).load((String) bean.getBody().getData()).into(mViewDataBinding.imgShop);
            } else {
                toast(s);
            }

        }
    }


    private void init() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_wait, null, false);
        alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create();
        // 1、创建LQRPhotoSelectUtils（一个Activity对应一个LQRPhotoSelectUtils）
        mLqrPhotoSelectUtils = new LQRPhotoSelectUtils(this, (outputFile, outputUri) -> {
            // 4、当拍照或从图库选取图片成功后回调
            String s = BitmapUtils.compressImageUpload(outputFile.getAbsolutePath());
            mViewModel.uploadImg(s, which);
            showUpDataDialog(true);
        }, false);//true裁剪，false不裁剪

        commonPopupWindow = new CommonPopupWindow(getApplication(), R.layout.window_shop_type, 500, 300) {
            @Override
            protected void initView(View contentView) {
                shopTypeAdapter = new ShopTypeAdapter();
                shopTypeAdapter.setOnItemClick((view1, ItemObject, position) -> {
                    mViewModel.ldShopType.setValue(ItemObject.getName());
                    mViewModel.ldShopTypeID.setValue(ItemObject.getId());
                    commonPopupWindow.dismiss();
                });
                RecyclerView recyclerView = contentView.findViewById(R.id.list);
                recyclerView.setAdapter(shopTypeAdapter);

            }

            @Override
            protected void initEvent() {

            }
        };
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
