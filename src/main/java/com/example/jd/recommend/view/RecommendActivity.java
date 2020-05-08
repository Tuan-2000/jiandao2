package com.example.jd.recommend.view;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.jd.R;
import com.example.jd.base.BaseActivity;
import com.example.jd.my.fragment.MyFragment;
import com.example.jd.recommend.adapter.Pager_Adapter;
import com.example.jd.recommend.adapter.Recommend_Pager_Adapter;
import com.example.jd.recommend.view.fragment.RecommendFragment;
import com.example.jd.recommend.presenter.RecommendPresenterImpl;
import com.example.jd.topic.view.fragment.TopicFragment;
import com.example.jd.video.fragment.VideoFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class RecommendActivity extends BaseActivity {

    private ViewPager mVp;
    private TabLayout mTl;
    private ArrayList<String> titles;
    private ArrayList<Fragment> fragments;
    private ArrayList<Integer> images;
    private RecommendFragment recommendFragment;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
        mTl = (TabLayout) findViewById(R.id.tl);
        mTl.setSelectedTabIndicatorHeight(0);
        initImages();
        initTitles();
        initFragments();
        Pager_Adapter adapter = new Pager_Adapter(getSupportFragmentManager(), titles, fragments);
        mVp.setAdapter(adapter);
        mTl.setupWithViewPager(mVp);

        for (int i = 0; i < titles.size(); i++) {
            TabLayout.Tab tab = mTl.getTabAt(i);
            tab.setCustomView(setTabView(i));
        }

    }

    private void initImages() {
        images = new ArrayList<>();
        images.add(R.drawable.recommend_sel);
        images.add(R.drawable.video_sel);
        images.add(R.drawable.topic_sel);
        images.add(R.drawable.my_sel);
    }

    private View setTabView(int i) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.tabs_custom, null);
        TextView tv = inflate.findViewById(R.id.tv);
        ImageView iv = inflate.findViewById(R.id.iv);
        tv.setText(titles.get(i));
        iv.setImageResource(images.get(i));
        return inflate;
    }

    private void initFragments() {
        fragments = new ArrayList<>();
        recommendFragment = new RecommendFragment();
        fragments.add(new RecommendFragment());
        fragments.add(new VideoFragment());
        fragments.add(new TopicFragment());
        fragments.add(new MyFragment());
    }

    private void initTitles() {
        titles = new ArrayList<>();
        titles.add("推荐");
        titles.add("视频");
        titles.add("专题");
        titles.add("我的");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected RecommendPresenterImpl initPresenter() {
        return null;
    }

    @Override
    protected void initListener() {

    }

}
