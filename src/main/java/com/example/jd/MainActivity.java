package com.example.jd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.jd.app.AppManager;
import com.example.jd.base.BaseActivity;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.IMainView {

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }


    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mPre.getRecommendList();
    }

    @Override
    protected void initListener() {

    }

    private long fristKeyBackTime = 0;

    /**
     * 双击退出    时间分发    onTouchEvent、  disPatchTouch、onInterc。。。
     */
//    @Override
//    public boolean onKeyUp(int keyCode, KeyEvent event) {
//        //        菜单、home、返回键 点击了返回键
////        点击屏幕的时候，   按下，抬起， 移动  判断时间为两
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
//            //            获取当前点击返回键的时间   --   两个时间
//            long currentTime = System.currentTimeMillis();
//            if (currentTime - fristKeyBackTime > 2000) {
//                //                表示不能退出
//                fristKeyBackTime = currentTime;
//                Toast.makeText(this, "在点击一次返回键，退出当前应用", Toast.LENGTH_SHORT).show();
//            } else {
//                AppManager.getInstance().appExit();
//            }
//        }
//        return super.onKeyUp(keyCode, event);
//    }

    @Override
    public void onBackPressed() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - fristKeyBackTime > 2000) {
            fristKeyBackTime = currentTimeMillis;
            Toast.makeText(this, "在点击一次返回键，退出当前应用", Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
        }
    }
}
