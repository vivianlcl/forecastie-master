package com.lcl.thumbweather;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitleList = new ArrayList<>();

    /**
     * ViewPagerAdapter的构造器
     * @param manager
     *
     * 传入FragmentManager
     */
    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    //获取指定页卡的Fragment
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    //获取Fragment的大小
    @Override
    public int getCount() {
        return fragmentList.size();
    }

    //添加Fragment到viewPager，并设置页卡的标题
    public void addFragment(Fragment fragment, String title) {
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
    }

    //获取指定页卡的标题
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }
}
