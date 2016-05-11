package com.zhonghua.mizi.presenter.message;

import android.os.Bundle;
import android.view.View;
import com.zhonghua.dileber.mvp.presenter.ActivityPresenter;
import com.zhonghua.dileber.mvp.presenter.FragmentActivityPresenter;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.delegate.message.SettingViewDelegate;
import com.zhonghua.mizi.scm.message.ISettingScm;
import com.zhonghua.mizi.scm.message.SettingScm;

public class SettingActivity extends FragmentActivityPresenter<SettingViewDelegate> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ISettingScm settingSrc = new SettingScm();

    }

    @Override
    protected Class<SettingViewDelegate> getDelegateClass() {
        return SettingViewDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
    }


    @Override
    public void onClick(View view) {

    }
}