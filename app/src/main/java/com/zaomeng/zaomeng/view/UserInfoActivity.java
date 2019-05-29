package com.zaomeng.zaomeng.view;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import com.android.tu.loadingdialog.LoadingDailog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityUserInfoBinding;
import com.zaomeng.zaomeng.model.repository.http.HttpHelper;
import com.zaomeng.zaomeng.model.repository.http.InterfaceLogin;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.LoginBean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.LQRPhotoSelectUtils;
import com.zaomeng.zaomeng.utils.http.BitmapUtils;
import com.zaomeng.zaomeng.view.base.MVVMActivity;
import com.zaomeng.zaomeng.view.custom_view.CircleImageView;
import com.zaomeng.zaomeng.view_model.UserInfoVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import java.util.List;

import javax.inject.Inject;

import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

/**
 * Created by Sampson on 2019-05-13.
 * FastAndroid
 */
public class UserInfoActivity extends MVVMActivity<UserInfoVM, ActivityUserInfoBinding> {
    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    HttpHelper httpHelper;
    private LQRPhotoSelectUtils mLqrPhotoSelectUtils;
    private Dialog alertDialog;
    private CircleImageView iconUser;
    private LoginBean loginBean;

    @NonNull
    @Override
    protected UserInfoVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(UserInfoVM.class);
    }

    @Override
    protected void setView() {
        iconUser = mViewDataBinding.iconUser;
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(this)
                .setMessage("加载中...")
                .setCancelable(true)
                .setCancelOutside(true);
        alertDialog = loadBuilder.create();

//        LayoutInflater layoutInflater = getLayoutInflater();
//        View view = layoutInflater.inflate(R.layout.dialog_wait, null, false);
//        alertDialog = new AlertDialog.Builder(this)
//                .setView(view)
//                .setCancelable(false)
//                .create();
        mLqrPhotoSelectUtils = new LQRPhotoSelectUtils(this, (outputFile, outputUri) -> {
            // 4、当拍照或从图库选取图片成功后回调
            String s = BitmapUtils.compressImageUpload(outputFile.getAbsolutePath());
            mViewModel.uploadImg(s);
            showUpDataDialog(true);
        }, false);//true裁剪，false不裁剪
        LiveData<List<LoginBean>> userInfo = mViewModel.getUserInfo();
        if (userInfo != null) {
            userInfo.observe(this, loginBeans -> {
                if (loginBeans != null && loginBeans.size() != 0) {
                    loginBean = loginBeans.get(0);
                    String avatarURL = loginBean.getAvatarURL();
                    mViewModel.oldUserURL = avatarURL;
                    RequestOptions requestOptions = new RequestOptions().
                            placeholder(R.mipmap.touxiang).
                            error(R.mipmap.touxiang);
                    Glide.with(iconUser).load(avatarURL).apply(requestOptions).into(iconUser);
                    mViewModel.ldName.setValue(loginBean.getShortName());
                    mViewModel.oldName = loginBean.getShortName();
                    mViewModel.ldName.observe(this, s -> {
                        EditText name = mViewDataBinding.name;
                        name.setSelection(name.getText().length());//将光标移至文字末尾
                    });

                    mViewModel.ldPhone.setValue(loginBean.getPhone());
                }
            });
        }
        mViewModel.ldUpdateImage.observe(this, s -> {
            if (s != null) {
                Gson gson = new Gson();
                try {
                    Bean bean = gson.fromJson(s, Bean.class);
                    if (bean != null) {
                        RequestOptions requestOptions = new RequestOptions().
                                placeholder(R.mipmap.loading).
                                error(R.mipmap.loading_error);
                        Glide.with(iconUser).load((String) bean.getBody().getData()).apply(requestOptions).into(iconUser);
                    }
                } catch (JsonSyntaxException j) {
                    toast(s);
                }
                showUpDataDialog(false);
            }

        });
        mViewModel.action.observe(this, s -> {
            if (s.equals("selectImage")) {
                PermissionGen.needPermission(UserInfoActivity.this,
                        LQRPhotoSelectUtils.REQ_SELECT_PHOTO,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE});
            } else if (s.contains("toast:")) {
                toast(s.replace("toast:", ""));
            } else if (s.equals("finish")) {
                finish();
            }
        });

    }

    private void showUpDataDialog(boolean isShow) {
        if (isShow) {
            alertDialog.show();
//            Window window = alertDialog.getWindow();
//            if (window != null) {
//                window.setLayout(UIUtil.dip2px(getApplicationContext(), 100),
//                        UIUtil.dip2px(getApplicationContext(), 100));
//                window.setGravity(Gravity.CENTER);
//            }
        } else if (alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_ok) {
            setUserInfo();
            return false;
        }
        return super.onOptionsItemSelected(item);

    }

    private void setUserInfo() {
        LiveData<Resource<Bean<String>>> resourceLiveData = mViewModel.updateMemberInfo();
        if (resourceLiveData != null) {
            resourceLiveData.observe(this, beanResource -> {
                String s = (String) httpHelper.AnalyticalData(beanResource, new InterfaceLogin() {
                    @Override
                    public void skipLoginActivity() {
                        skipTo(LoginActivity.class);
                    }

                    @Override
                    public void reLoad() {

                    }
                }, this);
                if (s != null) {
                    mViewModel.upDateUser(loginBean);
                    finish();
                }
            });
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
        return R.menu.menu_ok;
    }

    @Override
    protected String setToolBarTitle() {
        return "个人信息";
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void doClose() {
        setUserInfo();
    }
}
