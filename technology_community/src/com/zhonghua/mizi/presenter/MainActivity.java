package com.zhonghua.mizi.presenter;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.zhonghua.dileber.mvp.presenter.ActivityPresenter;
import com.zhonghua.dileber.tools.SLog;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.delegate.MainViewDelegate;
import com.zhonghua.mizi.scm.IMainScm;
import com.zhonghua.mizi.scm.MainScm;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends ActivityPresenter<MainViewDelegate>  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IMainScm mainSrc = new MainScm();

    }


    @Override
    protected Class<MainViewDelegate> getDelegateClass() {
        return MainViewDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        viewDelegate.checkNetwork();
    }


    @Override
    public void onClick(View view) {

    }
}