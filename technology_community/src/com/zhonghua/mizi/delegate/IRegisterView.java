package com.zhonghua.mizi.delegate;

import com.zhonghua.mizi.exception.RegistException;
import com.zhonghua.mizi.model.UserModel;

/**
 * Created by  on 16/1/17.
 */
public interface IRegisterView {

    UserModel getUser() throws RegistException;

}
