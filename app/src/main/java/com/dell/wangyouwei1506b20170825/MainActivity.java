package com.dell.wangyouwei1506b20170825;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fragmentPackage.Fragment_main;
/**
 * 王有为
 * 2017-8-25
 *
 * */
public class MainActivity extends AppCompatActivity {
    //TabLayout+Fragment 滑動
    private TabLayout tabLayout;
    private ViewPager viewPager;
    //为TabLayout添加标题
    private String[] title = {"推荐","社会","热点","北京","视频","图片","体育",};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout=(TabLayout)findViewById(R.id.Tab);
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        //给viewpager设置一个Adapter
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                //给Fragment传值
                Fragment fragment = new Fragment_main();
                Bundle bundle = new Bundle();
                bundle.putString("name",""+position);
                fragment.setArguments(bundle);
                return fragment;
            }

            @Override
            public int getCount() {
                return title.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }
        });
        //绑定viewpager
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);
    }
}
