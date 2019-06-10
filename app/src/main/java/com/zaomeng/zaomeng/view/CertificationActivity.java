package com.zaomeng.zaomeng.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityCertificationBinding;
import com.zaomeng.zaomeng.model.repository.http.HttpHelper;
import com.zaomeng.zaomeng.model.repository.http.InterfaceLogin;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsSuperBean;
import com.zaomeng.zaomeng.model.repository.http.bean.MemberShopBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageDataBean;
import com.zaomeng.zaomeng.utils.LQRPhotoSelectUtils;
import com.zaomeng.zaomeng.utils.http.BitmapUtils;
import com.zaomeng.zaomeng.view.base.MVVMActivity;
import com.zaomeng.zaomeng.view_model.CertificationVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import org.greenrobot.eventbus.EventBus;

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
    @Inject
    HttpHelper httpHelper;
    private LQRPhotoSelectUtils mLqrPhotoSelectUtils;
    private AlertDialog alertDialog;
    //    private List<GoodsSuperBean> listShopType = new ArrayList<>();
    //    private CommonPopupWindow commonPopupWindow;
//    private ShopTypeAdapter shopTypeAdapter;
    private int which = -1;

    @NonNull
    @Override
    protected CertificationVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(CertificationVM.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void setView() {
        if (transferData instanceof MemberShopBean) {//编辑店铺
            mViewModel.isEdit = true;
            mViewModel.memberShopID = ((MemberShopBean) transferData).getId();
            mViewModel.ldName.setValue(((MemberShopBean) transferData).getName());
            mViewModel.getShopType().observe(this, pageBeanResource -> {
                PageDataBean<GoodsSuperBean> goodsSuperBeanPageDataBean = httpHelper.AnalyticalPageData(pageBeanResource, new InterfaceLogin() {
                    @Override
                    public void skipLoginActivity() {
//                    skipTo(LoginActivity.class, null);
                    }

                    @Override
                    public void reLoad() {
//                    mViewModel.getShopType();
                    }
                }, this);
                if (goodsSuperBeanPageDataBean != null) {
                    List<GoodsSuperBean> rows = goodsSuperBeanPageDataBean.getRows();
//                shopTypeAdapter.setList(listShopType);
                    if (mViewModel.isEdit) {
                        for (GoodsSuperBean goodsSuperBean : rows) {
                            if (goodsSuperBean.getId().equals(mViewModel.ldShopTypeID.getValue())) {
                                mViewModel.ldShopType.setValue(goodsSuperBean.getName());
                                mViewModel.ldShopTypeID.setValue(goodsSuperBean.getId());
                                break;
                            }
                        }

                    }
                }

            });
//            mViewModel.ldShopType.setValue(((MemberShopBean) transferData).getShopType());
            mViewModel.ldShopTypeID.setValue(((MemberShopBean) transferData).getShopCategoryID());
            mViewModel.ldContact.setValue(((MemberShopBean) transferData).getContact());
            mViewModel.ldContactPhone.setValue(((MemberShopBean) transferData).getContactPhone());
            mViewModel.ldAddress.setValue(((MemberShopBean) transferData).getAddress());
            mViewDataBinding.rlIdBack.setVisibility(View.GONE);
            mViewDataBinding.rlIdFront.setVisibility(View.GONE);
            mViewDataBinding.rlLicense.setVisibility(View.GONE);
            mViewDataBinding.rlShopFace.setVisibility(View.GONE);

            String shopFaceImage = ((MemberShopBean) transferData).getShopFaceImage();
            mViewModel.shopFaceImage = shopFaceImage;
            ImageView imgShop = mViewDataBinding.imgShop;
            Glide.with(imgShop).load(shopFaceImage).into(imgShop);

            String businessImage = ((MemberShopBean) transferData).getBusinessImage();
            mViewModel.businessImage = businessImage;
            ImageView imgLicense = mViewDataBinding.imgLicense;
            Glide.with(imgLicense).load(businessImage).into(imgLicense);


            String contactIdCardFaceImage = ((MemberShopBean) transferData).getContactIdCardFaceImage();
            mViewModel.contactIdCardFaceImage = contactIdCardFaceImage;
            ImageView imgIcFront = mViewDataBinding.imgIcFront;
            Glide.with(imgIcFront).load(contactIdCardFaceImage).into(imgIcFront);


            String contactIdCardBackImage = ((MemberShopBean) transferData).getContactIdCardBackImage();
            mViewModel.contactIdCardBackImage = contactIdCardBackImage;
            ImageView imgIcBack = mViewDataBinding.imgIcBack;
            Glide.with(imgIcBack).load(contactIdCardBackImage).into(imgIcBack);
            mViewDataBinding.submit.setText("保存");
        }
        init();

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
//                    commonPopupWindow.showAsDropDown(mViewDataBinding.imageType, 0, 0);
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), ChoseShopTypeActivity.class);
                    startActivityForResult(intent, 10086);
                    break;
            }

        });
        mViewModel.ldToast.observe(this, this::toast);
        mViewModel.ldUpDataImage.observe(this, strings -> {
            String s = strings[1];
            switch (strings[0]) {
                case "0":
                    setImage(s, mViewDataBinding.imgShop, 0);
                    break;
                case "1":
                    setImage(s, mViewDataBinding.imgLicense, 1);
                    break;
                case "2":
                    setImage(s, mViewDataBinding.imgIcFront, 2);
                    break;
                case "3":
                    setImage(s, mViewDataBinding.imgIcBack, 3);
                    break;
            }


        });
        mViewModel.ldSubmit.observe(this, beanResource -> {
            String s = (String) httpHelper.AnalyticalData(beanResource, new InterfaceLogin() {
                @Override
                public void skipLoginActivity() {
                    skipTo(LoginActivity.class, null);
                }

                @Override
                public void reLoad() {

                }
            }, this);
            if (s != null) {
                toast("提交成功！");
                EventBus.getDefault().post("refresh");
                finish();
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

    private void setImage(String s, ImageView image, int i) {
        if (s != null) {

            Bean bean = null;
            try {
                Gson gson = new Gson();
                bean = gson.fromJson(s, Bean.class);
            } catch (JsonSyntaxException j) {
                toast(s + ":" + j.toString());
            }

            showUpDataDialog(false);
            if (bean != null) {
                switch (i) {
                    case 0:
                        mViewDataBinding.rlShopFace.setVisibility(View.GONE);
                        break;
                    case 1:
                        mViewDataBinding.rlLicense.setVisibility(View.GONE);
                        break;
                    case 2:
                        mViewDataBinding.rlIdFront.setVisibility(View.GONE);
                        break;
                    case 3:
                        mViewDataBinding.rlIdBack.setVisibility(View.GONE);
                        break;
                }
                Glide.with(image).load((String) bean.getBody().getData()).into(image);
            }

        }
    }


    private void init() {
        LayoutInflater layoutInflater = getLayoutInflater();
        @SuppressLint("InflateParams") View view = layoutInflater.inflate(R.layout.dialog_wait, null, false);
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

//        commonPopupWindow = new CommonPopupWindow(getApplication(), R.layout.window_shop_type, 500, 300) {
//            @Override
//            protected void initView(View contentView) {
//                shopTypeAdapter = new ShopTypeAdapter();
//                shopTypeAdapter.setOnItemClick((view1, ItemObject, position) -> {
//                    mViewModel.ldShopType.setValue(ItemObject.getName());
//                    mViewModel.ldShopTypeID.setValue(ItemObject.getId());
//                    commonPopupWindow.dismiss();
//                });
//                RecyclerView recyclerView = contentView.findViewById(R.id.list);
//                recyclerView.setAdapter(shopTypeAdapter);
//
//            }
//
//            @Override
//            protected void initEvent() {
//
//            }
//        };
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
        if (requestCode == 10086 && resultCode == Activity.RESULT_OK) {
            GoodsSuperBean goodsSuperBean = data.getParcelableExtra("Data");
            if (goodsSuperBean != null) {
                mViewModel.ldShopType.setValue(goodsSuperBean.getName());
                mViewModel.ldShopTypeID.setValue(goodsSuperBean.getId());
            }
        }

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
