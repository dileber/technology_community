package com.zhonghua.mizi.delegate.message;

import com.zhonghua.dileber.mvp.view.AppViewDelegate;
import com.zhonghua.mizi.R;

/**
 * Created by  on 16/1/17.
 */
public class SettingViewDelegate extends AppViewDelegate implements ISettingView{


    @Override
    public int getRootLayoutId() {
        return R.layout.activity_setting;
    }


    @Override
    public void initWidget() {
        super.initWidget();
    }

}