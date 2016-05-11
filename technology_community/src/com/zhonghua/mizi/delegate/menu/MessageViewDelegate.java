package com.zhonghua.mizi.delegate.menu;

import android.content.Intent;
import android.view.View;
import com.zhonghua.dileber.mvp.view.AppViewDelegate;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.presenter.message.OnlineActivity;
import com.zhonghua.mizi.presenter.message.PhoneBookActivity;
import com.zhonghua.mizi.presenter.message.UserCheckActivity;

/**
 * Created by  on 16/1/17.
 */
public class MessageViewDelegate extends MenuViewDelegate implements IMessageView{


    @Override
    public int getRootLayoutId() {
        return R.layout.activity_message;
    }


    @Override
    public void initWidget() {
        super.initWidget();
        setOnClickListener(this, R.id.message_conversation,R.id.message_setting,R.id.message_phonebook,R.id.message_checkuser);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        Intent it = new Intent();
        switch (view.getId()){

            case R.id.message_conversation:
                it.setClass(getActivity(), OnlineActivity.class);
                getActivity().startActivity(it);
                break;
            case R.id.message_setting:
                break;
            case R.id.message_phonebook:
                it.setClass(getActivity(), PhoneBookActivity.class);
                getActivity().startActivity(it);
                break;
            case R.id.message_checkuser:
                it.setClass(getActivity(), UserCheckActivity.class);
                getActivity().startActivity(it);
                break;

        }

    }
}