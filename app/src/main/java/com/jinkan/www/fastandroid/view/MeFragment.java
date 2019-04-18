package com.jinkan.www.fastandroid.view;

import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.databinding.FragmentMeBinding;
import com.jinkan.www.fastandroid.view.base.MVVMFragment;
import com.jinkan.www.fastandroid.view_model.MeFragmentVM;

import javax.inject.Inject;

import androidx.lifecycle.ViewModelProviders;

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
            if (s.equals("cancel")) {
                skipTo(ServiceActivity.class, null);
            } else {
                skipTo(OrderActivity.class, s);
            }

//            switch (s) {
//                case "allOrder":
//
//                    break;
//                case "payment":
//
//                    break;
//                case "receivingGoods":
//
//                    break;
//                case "cancel":
//
//                    break;
//            }
        });
    }
}
