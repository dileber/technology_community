package com.zhonghua.mizi.delegate;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.zhonghua.dileber.mvp.view.AppViewDelegate;
import com.zhonghua.dileber.tools.UText;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.model.UserModel;
import com.zhonghua.mizi.presenter.RegisterActivity;
import com.zhonghua.mizi.presenter.menu.HomeActivity;
import com.zhonghua.mizi.scm.OnLoginLinstener;

/**
 * Created by  on 16/1/17.
 */
public class LoginViewDelegate extends AppViewDelegate implements ILoginView,View.OnClickListener{


    @Override
    public int getRootLayoutId() {
        return R.layout.activity_login;
    }


    EditText login_username,login_pass;

    @Override
    public void initWidget() {
        super.initWidget();
        login_username = get(R.id.login_username);
        login_pass = get(R.id.login_pass);
        setOnClickListener(this,R.id.login_open,R.id.login_regist);
        textChange();
    }

    private void textChange(){
        login_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                UText.getWatcher(editable,UText.PATTERN_N_A_Z);
            }
        });
    }


    @Override
    public UserModel getUser() {
        UserModel userModel = new UserModel();
        userModel.setLoginname(login_username.getText().toString().trim());
        userModel.setUserpass(login_pass.getText().toString().trim());
        return userModel;
    }

    @Override
    public void onClick(View view) {
        Intent it = new Intent();
        switch (view.getId()){
            case R.id.login_open:
                it.setClass(getActivity(), HomeActivity.class);
                getActivity().startActivity(it);
                getActivity().finish();
                break;
            case R.id.login_regist:
                it.setClass(getActivity(), RegisterActivity.class);
                getActivity().startActivity(it);

                break;
        }

    }


}