package com.zaomeng.zaomeng.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityMessageTypeBinding;
import com.zaomeng.zaomeng.model.repository.http.HttpHelper;
import com.zaomeng.zaomeng.model.repository.http.InterfaceLogin;
import com.zaomeng.zaomeng.model.repository.http.bean.MessageTypeBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageDataBean;
import com.zaomeng.zaomeng.view.base.MVVMActivity;
import com.zaomeng.zaomeng.view_model.MessageTypeVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Sampson on 2019-05-22.
 * FastAndroid
 */
public class MessageTypeActivity extends MVVMActivity<MessageTypeVM, ActivityMessageTypeBinding> {
    @Inject
    ViewModelFactory viewModelFactory;
    private List<MessageTypeBean> list;
    @Inject
    HttpHelper httpHelper;
    @NonNull
    @Override
    protected MessageTypeVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(MessageTypeVM.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void setView() {
        list = new ArrayList<>();
        RecyclerView listView = mViewDataBinding.list;
        mViewModel.getMessageTypeList().observe(this, pageBeanResource -> {
            PageDataBean<MessageTypeBean> messageBeanPageDataBean = httpHelper.AnalyticalPageData(pageBeanResource, new InterfaceLogin() {
                @Override
                public void skipLoginActivity() {
                    skipTo(LoginActivity.class);
                }

                @Override
                public void reLoad() {
                    mViewModel.getMessageTypeList();
                }
            }, this);
            if (messageBeanPageDataBean != null) {
                list = messageBeanPageDataBean.getRows();
                listView.setAdapter(new CommonAdapter<MessageTypeBean>(getApplicationContext(), R.layout.item_message, list) {
                    @Override
                    protected void convert(ViewHolder holder, MessageTypeBean messageTypeBean, int position) {
                        View itemView = holder.itemView;
                        ImageView image = itemView.findViewById(R.id.icon);
                        Glide.with(image).load(messageTypeBean.getLargeIcon()).into(image);
                        TextView text = itemView.findViewById(R.id.text);
                        text.setText(messageTypeBean.getName());
                        TextView subTitle = itemView.findViewById(R.id.subTitle);
                        subTitle.setText(messageTypeBean.getMessageList().get(0).getDescription());
                        itemView.setOnClickListener(v -> skipTo(MessageActivity.class, new String[]{messageTypeBean.getName(), messageTypeBean.getId()}));
                    }
                });
            }
        });

    }

    @Override
    protected int setToolBarMenu() {
        return 0;
    }

    @Override
    protected String setToolBarTitle() {
        return "消息";
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_message_type;
    }
}
