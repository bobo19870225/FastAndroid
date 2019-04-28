package com.zaomeng.zaomeng.view.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/12/13.
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private String[] titles;
    private List<String> IDs;

    public FragmentAdapter(FragmentManager fm, List<Fragment> list, String[] titles, List<String> IDs) {
        super(fm);
        this.list = list;
        this.titles = titles;
        this.IDs = IDs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (IDs != null) {
            Bundle bundle = new Bundle();
            bundle.putString("DATA", IDs.get(position));
            list.get(position).setArguments(bundle);
        }
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
