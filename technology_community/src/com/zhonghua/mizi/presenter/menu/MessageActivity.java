package com.zhonghua.mizi.presenter.menu;

import android.os.Bundle;
import android.view.View;
import com.zhonghua.dileber.mvp.presenter.ActivityPresenter;
import com.zhonghua.dileber.tools.annotation.CloseTitle;
import com.zhonghua.dileber.tools.annotation.HideKeyboard;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.delegate.menu.MessageViewDelegate;
import com.zhonghua.mizi.scm.menu.IMessageScm;
import com.zhonghua.mizi.scm.menu.MessageScm;

@HideKeyboard
@CloseTitle
public class MessageActivity extends ActivityPresenter<MessageViewDelegate>  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IMessageScm messageSrc = new MessageScm();

    }

    @Override
    protected Class<MessageViewDelegate> getDelegateClass() {
        return MessageViewDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
    }


    @Override
    public void onClick(View view) {

    }
}