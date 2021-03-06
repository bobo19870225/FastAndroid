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
public class ListFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private List<String> titles;
    private List<Integer> listSortType;

    public ListFragmentAdapter(FragmentManager fm, List<Fragment> list, List<String> titles) {
        super(fm);
        this.list = list;
        this.titles = titles;
    }

    public ListFragmentAdapter(FragmentManager fm, List<Fragment> list, List<String> titles, List<Integer> listSortType) {
        super(fm);
        this.list = list;
        this.titles = titles;
        this.listSortType = listSortType;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (listSortType != null) {
            Bundle bundle = new Bundle();
            bundle.putInt("DATA", listSortType.get(position));
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
        return titles.get(position);
    }
}
