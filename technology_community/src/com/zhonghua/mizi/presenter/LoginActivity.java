package com.zhonghua.mizi.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.zhonghua.dileber.mvp.presenter.ActivityPresenter;
import com.zhonghua.dileber.tools.SLog;
import com.zhonghua.dileber.tools.UText;
import com.zhonghua.dileber.tools.annotation.CloseTitle;
import com.zhonghua.dileber.tools.annotation.HideKeyboard;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.app.AppHper;
import com.zhonghua.mizi.delegate.LoginViewDelegate;
import com.zhonghua.mizi.model.UserModel;
import com.zhonghua.mizi.presenter.menu.HomeActivity;
import com.zhonghua.mizi.scm.ILoginScm;
import com.zhonghua.mizi.scm.LoginScm;
import com.zhonghua.mizi.scm.OnLoginLinstener;
import com.zhonghua.mizi.utils.HuanXinUtil;

@CloseTitle
@HideKeyboard
public class LoginActivity extends ActivityPresenter<LoginViewDelegate>  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected Class<LoginViewDelegate> getDelegateClass() {
        return LoginViewDelegate.class;
    }

    ILoginScm loginScm;

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        loginScm = new LoginScm();

        viewDelegate.get(R.id.login_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final UserModel userModel = viewDelegate.getUser();
                final String userName = userModel.getLoginname();
                final String pass = userModel.getUserpass();
                if(!UText.checkEditText(userName,6)||!UText.checkEditText(pass,6)){
                    viewDelegate.toast("账号或密码不能小于6位数", Toast.LENGTH_SHORT);
                    return;
                }
                loginScm.login(userModel, new OnLoginLinstener() {
                    @Override
                    public void errorMsg(String message) {
                        viewDelegate.loadDialogDismiss();
                        viewDelegate.showAlert(viewDelegate.DIALOG_ERROR,message);
                    }

                    @Override
                    public void before() {
                        viewDelegate.loading();
                    }

                    @Override
                    public void success(Object model) {


                        final UserModel userModel1 = (UserModel)model;
                        final Intent it = new Intent();
                        it.setClass(LoginActivity.this, HomeActivity.class);
                        HuanXinUtil.login(userModel, null);
                        viewDelegate.loadDialogDismiss();
                        viewDelegate.toast("欢迎" + userModel1.getUsername(), Toast.LENGTH_SHORT);
                        startActivity(it);
                        finish();

                    }

                    @Override
                    public void failed() {
                        viewDelegate.loadDialogDismiss();
                    }

                    @Override
                    public void errMsg(String msg) {

                    }
                });

            }
        });



    }


    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(AppHper.getAppUser()!=null){
            Intent it = new Intent();
            it.setClass(LoginActivity.this,HomeActivity.class);
            startActivity(it);
            finish();
        }
    }

}