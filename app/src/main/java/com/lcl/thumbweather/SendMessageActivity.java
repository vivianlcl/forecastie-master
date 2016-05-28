package com.lcl.thumbweather;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lcl.thumbweather.R;

/**
 * Created by Administrator on 2016/5/16.
 */
public class SendMessageActivity extends Activity{
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private ViewPager viewPager;

    private String[] titles = new String[]{"联系人","发送记录"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_toolbar);

        initViews();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initViews(){
        tabLayout = (TabLayout)findViewById(R.id.share_tabs);
        toolbar = (Toolbar)findViewById(R.id.share_toolbar);
        viewPager = (ViewPager)findViewById(R.id.share_viewpager);
    }
}
