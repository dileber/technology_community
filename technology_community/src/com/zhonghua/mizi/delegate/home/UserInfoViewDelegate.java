package com.zhonghua.mizi.delegate.home;

import com.zhonghua.dileber.mvp.view.AppViewDelegate;
import com.zhonghua.mizi.R;

/**
 * Created by  on 16/1/17.
 */
public class UserInfoViewDelegate extends AppViewDelegate implements IUserInfoView{


    @Override
    public int getRootLayoutId() {
        return R.layout.activity_userinfo;
    }


    @Override
    public void initWidget() {
        super.initWidget();
    }

}