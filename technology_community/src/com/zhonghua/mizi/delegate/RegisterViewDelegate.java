package com.zhonghua.mizi.delegate;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import com.zhonghua.dileber.mvp.view.AppViewDelegate;
import com.zhonghua.dileber.tools.UText;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.exception.RegistException;
import com.zhonghua.mizi.model.UserModel;

/**
 * Created by  on 16/1/17.
 */
public class RegisterViewDelegate extends TitleViewDelegate implements IRegisterView{


    @Override
    public int getRootLayoutId() {
        return R.layout.activity_register;
    }

    EditText name,username,pass,pass_re;

    @Override
    public void initWidget() {
        super.initWidget();
        name = get(R.id.register_name);
        username = get(R.id.register_username);
        pass = get(R.id.register_pass);
        pass_re = get(R.id.register_pass_re);
        textChange();
    }

    private void textChange(){
        username.addTextChangedListener(new TextWatcher() {
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
    public UserModel getUser() throws RegistException {
        String name_t = name.getText().toString().trim();
        String username_t = username.getText().toString().trim();
        String pass_t = pass.getText().toString().trim();
        String pass_re_t = pass_re.getText().toString().trim();
        if(!UText.checkEditText(name_t,0)){
            throw new RegistException("用户名不能为空");
        }
        if(!pass_t.equals(pass_re_t)){
            throw new RegistException("两次密码不一致");
        }
        if(!UText.checkEditText(username_t,6)||!UText.checkEditText(pass_t,6)){
            throw new RegistException("用户名密码不能小于6位");
        }
        UserModel userModel = new UserModel();
        userModel.setLoginname(username_t);
        userModel.setUsername(name_t);
        userModel.setUserpass(pass_t);
        return userModel;
    }
}