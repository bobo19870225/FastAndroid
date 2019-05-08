package com.zaomeng.zaomeng.view;

import android.Manifest;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityFeedbackBinding;
import com.zaomeng.zaomeng.utils.LQRPhotoSelectUtils;
import com.zaomeng.zaomeng.utils.http.BitmapUtils;
import com.zaomeng.zaomeng.view.adapter.feedback.FeedbackImageAdapter;
import com.zaomeng.zaomeng.view.base.MVVMActivity;
import com.zaomeng.zaomeng.view_model.FeedbackVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

/**
 * Created by Sampson on 2019-05-08.
 * FastAndroid
 */
public class FeedbackActivity extends MVVMActivity<FeedbackVM, ActivityFeedbackBinding> {
    @Inject
    ViewModelFactory viewModelFactory;
    private List<String> list = new ArrayList<>();
    private LQRPhotoSelectUtils mLqrPhotoSelectUtils;
    @NonNull
    @Override
    protected FeedbackVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(FeedbackVM.class);
    }

    @Override
    protected void setView() {
        mViewDataBinding.contant.setHorizontallyScrolling(false);
        mViewDataBinding.contant.setSingleLine(false);
        mViewModel.ldContent.observe(this, s -> {
            int length = s.length();
            String s1 = length + "/" + "200";
            mViewModel.ldTextNumber.setValue(s1);
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mViewDataBinding.listImage.setLayoutManager(linearLayoutManager);
        FeedbackImageAdapter feedbackImageAdapter = new FeedbackImageAdapter();
        feedbackImageAdapter.setOnAddItemClick((view, ItemObject, position) -> {
            // 3、调用从图库选取图片方法
            PermissionGen.needPermission(FeedbackActivity.this,
                    LQRPhotoSelectUtils.REQ_SELECT_PHOTO,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE}
            );

        });
        mViewDataBinding.listImage.setAdapter(feedbackImageAdapter);

        mLqrPhotoSelectUtils = new LQRPhotoSelectUtils(this, (outputFile, outputUri) -> {
            // 4、当拍照或从图库选取图片成功后回调
            String s = BitmapUtils.compressImageUpload(outputFile.getAbsolutePath());
            list.add(s);
            feedbackImageAdapter.setList(list);
//            mViewModel.uploadImg(s);
        }, false);//true裁剪，false不裁剪
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
        return "意见反馈";
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_feedback;
    }
}
