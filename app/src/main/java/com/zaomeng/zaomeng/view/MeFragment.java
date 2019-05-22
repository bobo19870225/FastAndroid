package com.zaomeng.zaomeng.view;

import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.FragmentMeBinding;
import com.zaomeng.zaomeng.model.repository.dataBase.UserDao;
import com.zaomeng.zaomeng.model.repository.http.bean.LoginBean;
import com.zaomeng.zaomeng.view.base.MVVMFragment;
import com.zaomeng.zaomeng.view.custom_view.CircleImageView;
import com.zaomeng.zaomeng.view_model.MeFragmentVM;

import javax.inject.Inject;

/**
 * Created by Sampson on 2019/4/16.
 * FastAndroid
 */
public class MeFragment extends MVVMFragment<MeFragmentVM, FragmentMeBinding> {

    @Inject
    public MeFragment() {
    }

    @Inject
    UserDao userDao;
    @Override
    protected MeFragmentVM createdViewModel() {
        return ViewModelProviders.of(this).get(MeFragmentVM.class);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initUI() {
        mViewModel.action.observe(this, s -> {
            switch (s) {
                case "allOrder":
                case "payment":
                case "receivingGoods":
                    skipTo(OrderActivity.class, s);
                    break;
                case "cancel":
                    skipTo(ServiceActivity.class, null);
                    break;
                case "address":
                    skipTo(AddressManageActivity.class, null);
                    break;
                case "calendar":
                    skipTo(CalendarActivity.class, null);
                    break;
                case "point":
                    skipTo(PointActivity.class, null);
                    break;
                case "bonus":
                    skipTo(BonusActivity.class, null);
                    break;
                case "feedback":
                    skipTo(FeedbackActivity.class, null);
                    break;
                case "setting":
                    skipTo(SettingActivity.class, null);
                    break;
                case "userInfo":
                    skipTo(UserInfoActivity.class, null);
                    break;
                case "customerService":
                    skipTo(CustomerServiceActivity.class, null);
                    break;
                case "message":
                    skipTo(MessageTypeActivity.class, null);
                    break;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        CircleImageView iconUser = mViewDataBinding.iconUser;
        userDao.getAllUser().observe(this, loginBeans -> {
            if (loginBeans != null && loginBeans.size() != 0) {
                LoginBean loginBean = loginBeans.get(0);
                if (loginBean != null) {
                    RequestOptions options = new RequestOptions()
                            .placeholder(R.mipmap.touxiang)//加载成功之前占位图
                            .error(R.mipmap.touxiang);//加载错误之后的错误图
                    Glide.with(iconUser).
                            load(loginBean.getAvatarURL()).
                            apply(options).
                            into(iconUser);
                    mViewDataBinding.userName.setText(loginBean.getShortName());
                    mViewDataBinding.userPhone.setText(loginBean.getPhone());
                }
            }
        });
    }
}
