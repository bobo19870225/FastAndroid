package com.zaomeng.zaomeng.view;

import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.FragmentMeBinding;
import com.zaomeng.zaomeng.model.repository.dataBase.UserDao;
import com.zaomeng.zaomeng.model.repository.http.bean.LoginBean;
import com.zaomeng.zaomeng.view.base.MVVMFragment;
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
        userDao.getAllUser().observe(this, loginBeans -> {
            if (loginBeans != null && loginBeans.size() != 0) {
                LoginBean loginBean = loginBeans.get(0);
                if (loginBean != null) {
                    Glide.with(mViewDataBinding.iconUser).
                            load(loginBean.getAvatarURL()).
                            into(mViewDataBinding.iconUser);
                    mViewDataBinding.userName.setText(loginBean.getShortName());
                    mViewDataBinding.userPhone.setText(loginBean.getPhone());
                }
            }
        });


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
            }
        });
    }
}
