package com.zhonghua.mizi.scm;

import com.zhonghua.dileber.mvp.scm.INetListener;

/**
 * Created by shidawei on 16/3/16.
 */
public interface OnLoginLinstener extends INetListener{

    void errorMsg(String message);

}
