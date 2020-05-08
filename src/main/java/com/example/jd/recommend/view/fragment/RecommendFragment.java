package com.example.jd.recommend.view.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.jd.R;
import com.example.jd.base.BaseFragment;
import com.example.jd.bean.SectionBean;
import com.example.jd.recommend.adapter.NaviRec_Adapter;
import com.example.jd.recommend.adapter.RecommendAdapter;
import com.example.jd.recommend.adapter.Recommend_Pager_Adapter;
import com.example.jd.recommend.contract.RecommendContract;
import com.example.jd.recommend.presenter.RecommendPresenterImpl;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class RecommendFragment extends BaseFragment<RecommendPresenterImpl> implements RecommendContract.IHomeView {

    private RecyclerView mRvHome;
    private RecommendAdapter adapter;
    private TabLayout mTl;
    private ViewPager mVp;
    private ArrayList<String> titles;
    private ArrayList<Fragment> fragments;
    private List<SectionBean.DataBean.ListBean> idList = new ArrayList<>();
    private ImageView mLeftimg;
    private ImageView mRightimg;
    private NavigationView mNv;
    private DrawerLayout mDl;

    @Override
    protected RecommendPresenterImpl initPre() {
        return new RecommendPresenterImpl();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_recommend;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mPre.getSectionList();
    }

    @Override
    protected void initView(View itemView) {
        mTl = (TabLayout) itemView.findViewById(R.id.tl);
        mVp = (ViewPager) itemView.findViewById(R.id.vp);
        mTl.setSelectedTabIndicatorHeight(0);
        mLeftimg = (ImageView) itemView.findViewById(R.id.leftimg);
        mRightimg = (ImageView) itemView.findViewById(R.id.rightimg);
        mNv = (NavigationView) itemView.findViewById(R.id.nv);
        mDl = (DrawerLayout) itemView.findViewById(R.id.dl);

        mLeftimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDl.openDrawer(mNv);
            }
        });
    }

    private void initFragments() {
        fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            RecommendRecFragment recommendRecFragment = new RecommendRecFragment();
            Bundle bundle = new Bundle();
            bundle.putString("id", idList.get(i).getId());
            recommendRecFragment.setArguments(bundle);
            fragments.add(recommendRecFragment);
        }
    }

    private void initTitles() {
        titles = new ArrayList<>();
        for (int i = 0; i < idList.size(); i++) {
            String name = idList.get(i).getName();
            titles.add(name);
        }
    }

    @Override
    public void getSectionList(SectionBean bean) {
        List<SectionBean.DataBean.ListBean> list = bean.getData().getList();
        this.idList.addAll(list);

        initTitles();
        initFragments();
//        for (int i = 0; i < titles.size(); i++) {
//            mTl.addTab(mTl.newTab().setText(titles.get(i)));
//        }
        Recommend_Pager_Adapter home_pager_adapter = new Recommend_Pager_Adapter(getChildFragmentManager(), fragments);
        mVp.setAdapter(home_pager_adapter);
        mTl.setupWithViewPager(mVp);

        initTab();
        initNaviRec();
    }

    private void initTab() {
        for (int i = 0; i < titles.size(); i++) {
            TabLayout.Tab tab = mTl.getTabAt(i);
            tab.setCustomView(getTabView(i));
        }
        tabListener();
    }

    private void tabListener() {
        mTl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position%5){
                    case 0:
                        GradientDrawable drawable = new GradientDrawable();
                        drawable.setCornerRadius(10);
                        drawable.setStroke(1, Color.parseColor("#FF0000"));
                        drawable.setColor(Color.parseColor("#FF0000"));
                        TextView customView = (TextView) tab.getCustomView();
                        customView.setTextColor(getResources().getColor(R.color.white));
                        customView.setBackground(drawable);
                        break;
                    case 1:
                        GradientDrawable drawable1 = new GradientDrawable();
                        drawable1.setCornerRadius(10);
                        drawable1.setStroke(1, Color.parseColor("#FF7700"));
                        drawable1.setColor(Color.parseColor("#FF7700"));
                        TextView customView1 = (TextView) tab.getCustomView();
                        customView1.setTextColor(getResources().getColor(R.color.white));
                        customView1.setBackground(drawable1);
                        break;
                    case 2:
                        GradientDrawable drawable2 = new GradientDrawable();
                        drawable2.setCornerRadius(10);
                        drawable2.setStroke(1, Color.parseColor("#3BFF00"));
                        drawable2.setColor(Color.parseColor("#3BFF00"));
                        TextView customView2 = (TextView) tab.getCustomView();
                        customView2.setTextColor(getResources().getColor(R.color.white));
                        customView2.setBackground(drawable2);
                        break;
                    case 3:
                        GradientDrawable drawable3 = new GradientDrawable();
                        drawable3.setCornerRadius(10);
                        drawable3.setStroke(1, Color.parseColor("#00FDFF"));
                        drawable3.setColor(Color.parseColor("#00FDFF"));
                        TextView customView3 = (TextView) tab.getCustomView();
                        customView3.setTextColor(getResources().getColor(R.color.white));
                        customView3.setBackground(drawable3);
                        break;
                    case 4:
                        GradientDrawable drawable4 = new GradientDrawable();
                        drawable4.setCornerRadius(10);
                        drawable4.setStroke(1, Color.parseColor("#004AFF"));
                        drawable4.setColor(Color.parseColor("#004AFF"));
                        TextView customView4 = (TextView) tab.getCustomView();
                        customView4.setTextColor(getResources().getColor(R.color.white));
                        customView4.setBackground(drawable4);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                GradientDrawable drawable4 = new GradientDrawable();
//                drawable4.setCornerRadius(0);
//                drawable4.setStroke(1, Color.parseColor("#FFFFFF"));
//                drawable4.setColor(Color.parseColor("#FFFFFF"));
                TextView customView4 = (TextView) tab.getCustomView();
                customView4.setTextColor(getResources().getColor(R.color.black));
                customView4.setBackground(drawable4);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private View getTabView(int i) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.recommend_tab_clum, null);
        TextView textView = inflate.findViewById(R.id.clum_tv);
        textView.setHeight(80);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(18);
        textView.setText(titles.get(i));
        if (i == 0){
            GradientDrawable drawable = new GradientDrawable();
            drawable.setCornerRadius(10);
            drawable.setStroke(1, Color.parseColor("#FF0000"));
            drawable.setColor(Color.parseColor("#FF0000"));
            textView.setBackground(drawable);
        }
        return textView;
    }

    private void initNaviRec() {
        View view = mNv.getHeaderView(0);
        RecyclerView vp_navi = view.findViewById(R.id.vp_navi);

        NaviRec_Adapter adapter = new NaviRec_Adapter(getActivity(),titles);
        vp_navi.setLayoutManager(new GridLayoutManager(getActivity(),2));
        vp_navi.setAdapter(adapter);
    }


}
