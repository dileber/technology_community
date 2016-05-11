package com.zhonghua.mizi.delegate;

import android.content.Intent;
import android.os.Handler;
import com.zhonghua.dileber.mvp.view.AppViewDelegate;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.presenter.LoginActivity;
import com.zhonghua.mizi.presenter.menu.HomeActivity;

/**
 * Created by  on 16/1/17.
 */
public class SplashViewDelegate extends AppViewDelegate implements ISplashView{


    @Override
    public int getRootLayoutId() {
        return R.layout.activity_splash;
    }


    @Override
    public void initWidget() {
        super.initWidget();
    }





}