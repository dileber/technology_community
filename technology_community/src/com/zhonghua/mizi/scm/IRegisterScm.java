package com.zhonghua.mizi.scm;

import com.zhonghua.mizi.model.UserModel;

/**
 * Created by  on 16/1/17.
 */
public interface IRegisterScm {

    void regist(UserModel userModel, final OnRegisterLinstener onRegisterLinstener);

}
