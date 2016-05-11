package com.zhonghua.mizi.presenter;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.zhonghua.dileber.mvp.presenter.ActivityPresenter;
import com.zhonghua.dileber.tools.annotation.CloseTitle;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.delegate.RegisterViewDelegate;
import com.zhonghua.mizi.exception.RegistException;
import com.zhonghua.mizi.model.UserModel;
import com.zhonghua.mizi.scm.IRegisterScm;
import com.zhonghua.mizi.scm.OnRegisterLinstener;
import com.zhonghua.mizi.scm.RegisterScm;
import com.zhonghua.mizi.utils.HuanXinUtil;

@CloseTitle
public class RegisterActivity extends ActivityPresenter<RegisterViewDelegate>  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Class<RegisterViewDelegate> getDelegateClass() {
        return RegisterViewDelegate.class;
    }
    IRegisterScm registerSrc;
    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        registerSrc = new RegisterScm();

        viewDelegate.get(R.id.register_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                UserModel userModel = null;
                try {
                    userModel = viewDelegate.getUser();
                } catch (RegistException e) {
                    //e.printStackTrace();
                    viewDelegate.toast(e.getMessage(), Toast.LENGTH_SHORT);
                    return;
                }
                final UserModel finalUserModel = userModel;
                registerSrc.regist(userModel, new OnRegisterLinstener() {
                    @Override
                    public void errorMsg(String message) {
                        viewDelegate.loadDialogDismiss();
                        viewDelegate.showAlert(viewDelegate.DIALOG_ERROR, message);
                    }

                    @Override
                    public void before() {
                        viewDelegate.loading();
                    }

                    @Override
                    public void success(Object model) {
                        viewDelegate.loadDialogDismiss();
                        UserModel userModel1 = (UserModel) model;
                        viewDelegate.toast("欢迎" + userModel1.getUsername(), Toast.LENGTH_SHORT);
                        //Intent it = new Intent();
                        //it.setClass(RegisterActivity.this, HomeActivity.class);
                        //startActivity(it);
                        finish();
                        HuanXinUtil.huanXinRx(finalUserModel);
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



}