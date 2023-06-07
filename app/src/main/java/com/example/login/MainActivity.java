package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager mPaper;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private TextView tv_footable, tv_midfield, tv_re, tv_my;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLayout();
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mFragments.get(arg0);
            }
        };
        mPaper.setAdapter(mAdapter);
        mPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private int currentIndex;

            @Override
            public void onPageSelected(int position) {
                resetColor();
                switch (position) {
                    case 0:
                        tv_footable.setTextColor(Color.rgb(87, 153, 8));
                        break;
                    case 1:
                        tv_midfield.setTextColor(Color.rgb(87, 153, 8));
                        break;
                    case 2:
                        tv_re.setTextColor(Color.rgb(87, 153, 8));
                        break;
                    case 3:
                        tv_my.setTextColor(Color.rgb(87, 153, 8));
                        break;
                }
                currentIndex = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    public void initLayout() {
        tv_footable = (TextView) findViewById(R.id.tv_tuijian);
        tv_midfield = (TextView) findViewById(R.id.tv_zhouwei);
        tv_re = (TextView) findViewById(R.id.tv_redian);
        tv_my = (TextView) findViewById(R.id.tv_wode);
        mPaper = (ViewPager) findViewById(R.id.view_pager);
        imageView = findViewById(R.id.add_image);
        tv_footable.setOnClickListener(this);
        tv_midfield.setOnClickListener(this);
        tv_re.setOnClickListener(this);
        tv_my.setOnClickListener(this);
        imageView.setOnClickListener(this);

        Tuijian f2 = new Tuijian();
        Zhouw f1 = new Zhouw();
        Remen remen = new Remen();
        Wode wode = new Wode();

        mFragments.add(f1);
        mFragments.add(f2);
        mFragments.add(remen);
        mFragments.add(wode);

    }

    public void resetColor() {
        tv_footable.setTextColor(Color.rgb(56, 56, 56));
        tv_midfield.setTextColor(Color.rgb(56, 56, 56));
        tv_re.setTextColor(Color.rgb(56, 56, 56));
        tv_my.setTextColor(Color.rgb(56, 56, 56));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_tuijian:
                resetColor();
                tv_footable.setTextColor(Color.rgb(87, 153, 8));
                mPaper.setCurrentItem(0);
                break;
            case R.id.tv_zhouwei:
                resetColor();
                tv_midfield.setTextColor(Color.rgb(87, 153, 8));
                mPaper.setCurrentItem(1);
                break;
            case R.id.tv_redian:
                resetColor();
                tv_midfield.setTextColor(Color.rgb(87, 153, 8));
                mPaper.setCurrentItem(2);
                break;
            case R.id.tv_wode:
                resetColor();
                tv_midfield.setTextColor(Color.rgb(87, 153, 8));
                mPaper.setCurrentItem(3);
                break;
            case R.id.add_image:
               startActivity(new Intent().setClass(MainActivity.this,AddImage.class));
                break;
            default:
                break;
        }
    }

    /**
     * ViewPager适配器
     */
    public class MyPagerAdapter extends PagerAdapter {
        public List<Activity> mListViews;

        public MyPagerAdapter(List<Activity> mListViews) {
            this.mListViews = mListViews;
        }


        @Override
        public void finishUpdate(View arg0) {
        }

        @Override
        public int getCount() {
            return mListViews.size();
        }


        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == (arg1);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
        }
    }
}
