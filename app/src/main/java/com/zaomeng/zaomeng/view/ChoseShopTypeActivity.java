package com.zaomeng.zaomeng.view;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.HttpHelper;
import com.zaomeng.zaomeng.model.repository.http.InterfaceLogin;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsSuperBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageDataBean;
import com.zaomeng.zaomeng.view.base.BaseDaggerActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Sampson on 2019-06-10.
 * FastAndroid
 */
public class ChoseShopTypeActivity extends BaseDaggerActivity {
    @Inject
    ApiService apiService;
    @Inject
    HttpHelper httpHelper;

    @Override
    protected String setToolBarTitle() {
        return "店铺类型选择";
    }

    @Override
    protected int setToolBarMenu() {
        return 0;
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_chose_shop_type;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void initView() {
        RecyclerView listView = findViewById(R.id.list);
        apiService.getNodeCategoryList("2a89882dffa242158a5e170215f351ad", 1).observe(this, pageBeanResource -> {
            PageDataBean<GoodsSuperBean> pageDataBean = httpHelper.AnalyticalPageData(pageBeanResource, new InterfaceLogin() {
                @Override
                public void skipLoginActivity() {

                }

                @Override
                public void reLoad() {

                }
            }, this);
            if (pageDataBean != null) {
                List<GoodsSuperBean> rows = pageDataBean.getRows();
                listView.setAdapter(new CommonAdapter<GoodsSuperBean>(getApplicationContext(), R.layout.item_location, rows) {
                    @Override
                    protected void convert(ViewHolder holder, GoodsSuperBean goodsSuperBean, int position) {
                        View itemView = holder.itemView;
                        TextView name = itemView.findViewById(R.id.location);
                        name.setText(goodsSuperBean.getName());
                        itemView.setOnClickListener(v -> {
                            Intent intent = new Intent();
                            intent.putExtra("Data", goodsSuperBean);
                            setResult(Activity.RESULT_OK, intent);
                            finish();
                        });
                    }


                });
            }
        });


    }
}
