package com.zaomeng.zaomeng.view;

import androidx.lifecycle.ViewModelProviders;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.FragmentMeBinding;
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
//            if (s.equals("cancel")) {
//
//            } else {
//                skipTo(OrderActivity.class, s);
//            }

            switch (s) {
                case "allOrder":

                    break;
                case "payment":

                    break;
                case "receivingGoods":

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
            }
        });
    }
}
