package com.youyicheng.KaoLiao.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;

public class HomePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;
    private FragmentManager fm;

    public HomePagerAdapter(FragmentManager fragmentManager, ArrayList<Fragment> fragments, ArrayList<String> titles) {
        super(fragmentManager);
        this.titles=titles;
        this.fragments=fragments;
        this.fm=fragmentManager;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

}
