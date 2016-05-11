package com.zhonghua.mizi.presenter.message;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.zhonghua.dileber.mvp.presenter.ActivityPresenter;
import com.zhonghua.dileber.mvp.presenter.FragmentActivityPresenter;
import com.zhonghua.dileber.mvp.presenter.FragmentPresenter;
import com.zhonghua.dileber.tools.annotation.CloseTitle;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.delegate.message.OnlineViewDelegate;
import com.zhonghua.mizi.presenter.utils.CarouselFragment;
import com.zhonghua.mizi.scm.message.IOnlineScm;
import com.zhonghua.mizi.scm.message.OnlineScm;

@CloseTitle
public class OnlineActivity extends FragmentActivityPresenter<OnlineViewDelegate> {
    private EaseConversationListFragment conversationListFragment;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IOnlineScm onlineSrc = new OnlineScm();

        conversationListFragment = new EaseConversationListFragment();

        conversationListFragment.setConversationListItemClickListener(new EaseConversationListFragment.EaseConversationListItemClickListener() {

            @Override
            public void onListItemClicked(EMConversation conversation) {
                startActivity(new Intent(OnlineActivity.this, ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, conversation.getUserName()));
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.online_fragment, conversationListFragment)
                .commit();

    }



    @Override
    protected Class<OnlineViewDelegate> getDelegateClass() {
        return OnlineViewDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
    }


    @Override
    public void onClick(View view) {

    }
}