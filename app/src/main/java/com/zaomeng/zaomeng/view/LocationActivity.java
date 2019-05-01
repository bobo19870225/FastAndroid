package com.zaomeng.zaomeng.view;

import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.dataBase.Address;
import com.zaomeng.zaomeng.utils.DBUtils;
import com.zaomeng.zaomeng.view.adapter.LocationAdapter;
import com.zaomeng.zaomeng.view.base.BaseDaggerActivity;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Sampson on 2019/4/18.
 * FastAndroid
 */
public class LocationActivity extends BaseDaggerActivity {
    @Inject
    DBUtils dbUtils;
    private List<Address> addressList;
    private List<Address> addressList1;
    private List<Address> addressList2;
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

        addressList = dbUtils.getProvincesFromDB();
        RecyclerView listView = findViewById(R.id.list);
        LocationAdapter locationAdapter = new LocationAdapter();
        locationAdapter.setOnItemClick((view, ItemObject, position) -> {
            List<Address> provincesFromDBByPID = dbUtils.getProvincesFromDBByPID(ItemObject.ID);
            locationAdapter.setList(provincesFromDBByPID);
        });
        listView.setAdapter(locationAdapter);
        locationAdapter.setList(addressList);
    }


}
