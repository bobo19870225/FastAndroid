package com.jinkan.www.fastandroid.view;

import androidx.recyclerview.widget.RecyclerView;

import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.view.adapter.LocationAdapter;
import com.jinkan.www.fastandroid.view.base.BaseDaggerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sampson on 2019/4/18.
 * FastAndroid
 */
public class LocationActivity extends BaseDaggerActivity {
    @Override
    protected int setToolBarMenu() {
        return 0;
    }

    @Override
    protected String setToolBarTitle() {
        return "区域选择";
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_location;
    }

    @Override
    protected void initView() {
        RecyclerView listView = findViewById(R.id.list);
        List<String> listLocation = new ArrayList<>();
        listLocation.add("1");
        listLocation.add("2");
        listLocation.add("3");
        listLocation.add("4");
        LocationAdapter locationAdapter = new LocationAdapter(listLocation);
        locationAdapter.setOnItemClick((view, ItemObject, position) -> LocationActivity.this.toast((String) ItemObject));
        listView.setAdapter(locationAdapter);
    }
}
