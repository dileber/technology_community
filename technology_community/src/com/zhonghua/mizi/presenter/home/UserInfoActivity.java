package com.zhonghua.mizi.presenter.home;

import android.os.Bundle;
import android.view.View;
import com.zhonghua.dileber.mvp.presenter.ActivityPresenter;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.delegate.home.UserInfoViewDelegate;
import com.zhonghua.mizi.scm.home.IUserInfoScm;
import com.zhonghua.mizi.scm.home.UserInfoScm;

public class UserInfoActivity extends ActivityPresenter<UserInfoViewDelegate>  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IUserInfoScm userinfoSrc = new UserInfoScm();

    }

    @Override
    protected Class<UserInfoViewDelegate> getDelegateClass() {
        return UserInfoViewDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
    }


    @Override
    public void onClick(View view) {

    }
}