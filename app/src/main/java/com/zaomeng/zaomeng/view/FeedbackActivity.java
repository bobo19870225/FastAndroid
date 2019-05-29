package com.zaomeng.zaomeng.view;

import android.content.Intent;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityFeedbackBinding;
import com.zaomeng.zaomeng.model.repository.http.HttpHelper;
import com.zaomeng.zaomeng.model.repository.http.InterfaceLogin;
import com.zaomeng.zaomeng.utils.LQRPhotoSelectUtils;
import com.zaomeng.zaomeng.view.base.MVVMActivity;
import com.zaomeng.zaomeng.view_model.FeedbackVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import kr.co.namee.permissiongen.PermissionSuccess;

/**
 * Created by Sampson on 2019-05-08.
 * FastAndroid
 */
public class FeedbackActivity extends MVVMActivity<FeedbackVM, ActivityFeedbackBinding> {
    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    HttpHelper httpHelper;
    private int oldPosition = -1;
    @NonNull
    @Override
    protected FeedbackVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(FeedbackVM.class);
    }

    @Override
    protected void setView() {
        setEditText();
//        setImageList();
        setTitleList();
        mViewModel.action.observe(this, s -> {
            if (s.contains("toast:")) {
                toast(s.replaceAll("toast:", ""));
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
                toast("提交成功");
                finish();
            }

        });
    }

    private void setTitleList() {
        RecyclerView recyclerView = mViewDataBinding.list;
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getApplicationContext());
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setAlignItems(AlignItems.STRETCH);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        recyclerView.setLayoutManager(layoutManager);
        List<String> itemList = new ArrayList<>();
        itemList.add("页面信息有误");
        itemList.add("APP不稳定");
        itemList.add("支付遇到问题");
        itemList.add("账号相关问题");
        itemList.add("其他");
        recyclerView.setAdapter(new CommonAdapter<String>(getApplication(), R.layout.flexbox_item_text, itemList) {
            @Override
            protected void convert(ViewHolder holder, String itemListBean, int position) {

                TextView te = holder.getView(R.id.imageview);
                te.setText(itemListBean);
                if (oldPosition == position) {
                    te.setBackground(getApplication().getResources().getDrawable(R.drawable.button_them_color_select));
                } else {
                    te.setBackground(getApplication().getResources().getDrawable(R.drawable.button_them_color_un_select));
                }
                te.setOnClickListener(v -> {
                            oldPosition = position;
                            mViewModel.title = itemList.get(position);
                            notifyDataSetChanged();
                        }
                );
                ViewGroup.LayoutParams lp = te.getLayoutParams();
                if (lp instanceof FlexboxLayoutManager.LayoutParams) {
                    FlexboxLayoutManager.LayoutParams flexBoxLp = (FlexboxLayoutManager.LayoutParams) lp;
//                    flexBoxLp.setFlexGrow(1.0f);
                    flexBoxLp.width = itemListBean.getBytes().length * 20;
                }

            }

        });
    }

//    private void setImageList() {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
//        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
//        mViewDataBinding.listImage.setLayoutManager(linearLayoutManager);
//        FeedbackImageAdapter feedbackImageAdapter = new FeedbackImageAdapter();
//        feedbackImageAdapter.setOnAddItemClick((view, ItemObject, position) -> {
//            // 3、调用从图库选取图片方法
//            PermissionGen.needPermission(FeedbackActivity.this,
//                    LQRPhotoSelectUtils.REQ_SELECT_PHOTO,
//                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
//                            Manifest.permission.WRITE_EXTERNAL_STORAGE}
//            );
//
//        });
//        mViewDataBinding.listImage.setAdapter(feedbackImageAdapter);
//
//        mLqrPhotoSelectUtils = new LQRPhotoSelectUtils(this, (outputFile, outputUri) -> {
//            // 4、当拍照或从图库选取图片成功后回调
//            String s = BitmapUtils.compressImageUpload(outputFile.getAbsolutePath());
//            list.add(s);
//            feedbackImageAdapter.setList(list);
////            mViewModel.uploadImg(s);
//        }, false);//true裁剪，false不裁剪
//    }

    private void setEditText() {
        mViewDataBinding.contant.setHorizontallyScrolling(false);
        mViewDataBinding.contant.setSingleLine(false);
        mViewModel.ldContent.observe(this, s -> {
            int length = s.length();
            String s1 = length + "/" + "200";
            mViewModel.ldTextNumber.setValue(s1);
        });
    }

    @PermissionSuccess(requestCode = LQRPhotoSelectUtils.REQ_SELECT_PHOTO)
//    private void selectPhoto() {
//        mLqrPhotoSelectUtils.selectPhoto();
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 2、在Activity中的onActivityResult()方法里与LQRPhotoSelectUtils关联
//        mLqrPhotoSelectUtils.attachToActivityForResult(requestCode, resultCode, data);
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
