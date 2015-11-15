package com.gatorboard.gatorboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import java.util.HashMap;
import java.util.Map;

import com.gatorboard.gatorboard.CircleIndicator;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private CircleIndicator mCircleIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ignoreSplash()) return;

        initView();
    }

    private boolean ignoreSplash() {
        if(Util.contatins(this, Util.FILE_NAME, Util.getAppVersionCode(this) + "")){
            Intent intent=new Intent(MainActivity.this,SplashScreen.class);
            startActivity(intent);
            this.finish();
            return true;
        }
        return false;
    }

    private void initView() {
        mViewPager= (ViewPager) findViewById(R.id.viewpager);
        mCircleIndicator= (CircleIndicator) findViewById(R.id.circle_indicator);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            private int[] resId={R.mipmap.ic_help_view_1,R.mipmap.ic_help_view_2,R.mipmap.ic_help_view_3,R.mipmap.ic_help_view_4};
            private Map<Integer,Fragment> mFragments=new HashMap<Integer,Fragment>();
            @Override
            public Fragment getItem(int i) {
                Fragment fragment=mFragments.get(i);
                if(fragment==null){
                    fragment=BlankFragment.newInstance(resId[i],i,resId.length);
                    mFragments.put(i,fragment);
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return resId.length;
            }
        });
        mCircleIndicator.setViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_event_details, menu);
        return true;
    }


}

