package com.zhonghua.mizi.delegate.message;

import com.zhonghua.dileber.mvp.view.AppViewDelegate;
import com.zhonghua.mizi.R;

/**
 * Created by  on 16/1/17.
 */
public class ChatViewDelegate extends AppViewDelegate implements IChatView{


    @Override
    public int getRootLayoutId() {
        return R.layout.activity_chat;
    }


    @Override
    public void initWidget() {
        super.initWidget();
    }

}