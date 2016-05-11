package com.zhonghua.mizi.scm.message;

import com.zhonghua.dileber.mvp.scm.INetListener;
import com.zhonghua.mizi.model.UserFriendModel;

/**
 * Created by  on 16/1/17.
 */
public interface IUserCheckScm {

    void getFriendList(UserFriendModel userFriendModel,INetListener iNetListener);

    void checkFriend(UserFriendModel userFriendModel,INetListener iNetListener);

}
