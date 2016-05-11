package com.zhonghua.mizi.presenter.message;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.zhonghua.dileber.mvp.presenter.ActivityPresenter;
import com.zhonghua.dileber.mvp.scm.INetListener;
import com.zhonghua.dileber.tools.annotation.CloseStatusBar;
import com.zhonghua.dileber.tools.annotation.CloseTitle;
import com.zhonghua.dileber.view.dialog.DialogLinstener;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.app.AppHper;
import com.zhonghua.mizi.delegate.message.UserCheckViewDelegate;
import com.zhonghua.mizi.model.UserFriendModel;
import com.zhonghua.mizi.model.UserModel;
import com.zhonghua.mizi.scm.message.IUserCheckScm;
import com.zhonghua.mizi.scm.message.UserCheckScm;

import java.util.List;

@CloseTitle
public class UserCheckActivity extends ActivityPresenter<UserCheckViewDelegate>  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected Class<UserCheckViewDelegate> getDelegateClass() {
        return UserCheckViewDelegate.class;
    }

    IUserCheckScm usercheckSrc;

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        usercheckSrc = new UserCheckScm();
        UserFriendModel userFriendModel = new UserFriendModel();
        userFriendModel.setState(0);
        UserModel userModel = AppHper.getAppUser();
        if(userModel==null){
            viewDelegate.toast("该功能需要登录，请去登录页登录",Toast.LENGTH_SHORT);
            finish();
        }else{
            userFriendModel.setUserid(userModel.getId());
            usercheckSrc.getFriendList(userFriendModel, new INetListener() {
                @Override
                public void before() {
                    viewDelegate.loading();
                }

                @Override
                public void success(Object model) {
                    Object o = model;
                    if(o!=null){
                        List<UserFriendModel> userFriendModels = (List<UserFriendModel>) o;
                        viewDelegate.showListView(userFriendModels);
                    }
                    viewDelegate.loadDialogDismiss();
                }

                @Override
                public void failed() {
                    viewDelegate.loadDialogDismiss();
                }

                @Override
                public void errMsg(String msg) {
                    viewDelegate.loadDialogDismiss();
                }
            });
        }

    }


    @Override
    public void onClick(View view) {

    }
}