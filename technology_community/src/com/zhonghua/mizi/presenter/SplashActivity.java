package com.zhonghua.mizi.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import com.zhonghua.dileber.mvp.presenter.ActivityPresenter;
import com.zhonghua.dileber.tools.annotation.CloseStatusBar;
import com.zhonghua.dileber.tools.annotation.CloseTitle;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.delegate.SplashViewDelegate;
import com.zhonghua.mizi.scm.ISplashScm;
import com.zhonghua.mizi.scm.SplashScm;

@CloseTitle
@CloseStatusBar
public class SplashActivity extends ActivityPresenter<SplashViewDelegate>  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected Class<SplashViewDelegate> getDelegateClass() {
        return SplashViewDelegate.class;
    }

    ISplashScm splashSrc;
    splashhandler splashhandler;
    Handler x;
    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        splashSrc = new SplashScm();
        x = new Handler();
        splashhandler = new splashhandler();
        splashSrc.splash();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(x!=null&&splashhandler!=null){
            x.removeCallbacks(splashhandler);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(x!=null&&splashhandler!=null){
            x.postDelayed(splashhandler, 3000);
        }
    }

    class splashhandler implements Runnable{

        public void run() {
            Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onClick(View view) {

    }
}