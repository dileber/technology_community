package com.zhonghua.mizi.delegate.message;

import com.zhonghua.dileber.mvp.view.AppViewDelegate;
import com.zhonghua.mizi.R;

/**
 * Created by  on 16/1/17.
 */
public class OnlineViewDelegate extends AppViewDelegate implements IOnlineView{


    @Override
    public int getRootLayoutId() {
        return R.layout.activity_online;
    }


    @Override
    public void initWidget() {
        super.initWidget();
    }

}