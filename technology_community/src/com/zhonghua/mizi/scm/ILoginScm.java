package com.zhonghua.mizi.scm;

import com.zhonghua.mizi.model.UserModel;

/**
 * Created by  on 16/1/17.
 */
public interface ILoginScm {

    void login(UserModel userModel, final OnLoginLinstener onLoginLinstener);

}
