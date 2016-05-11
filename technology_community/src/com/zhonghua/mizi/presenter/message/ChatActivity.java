package com.zhonghua.mizi.presenter.message;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.zhonghua.dileber.mvp.presenter.FragmentActivityPresenter;
import com.zhonghua.dileber.tools.annotation.CloseTitle;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.delegate.message.ChatViewDelegate;
import com.zhonghua.mizi.delegate.message.OnlineViewDelegate;

@CloseTitle
public class ChatActivity extends FragmentActivityPresenter<ChatViewDelegate> {
    private EaseChatFragment chatFragment;
    String toChatUsername;

    @Override
    public void onBackPressed() {
        chatFragment.onBackPressed();
    }
    
    public String getToChatUsername(){
        return toChatUsername;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Class<ChatViewDelegate> getDelegateClass() {
        return ChatViewDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        //聊天人或群id
        toChatUsername = getIntent().getExtras().getString(EaseConstant.EXTRA_USER_ID);
        chatFragment = new EaseChatFragment();
        //传入参数
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().replace(R.id.chart_fragment, chatFragment).commit();
    }

    @Override
    public void onClick(View view) {

    }
}
