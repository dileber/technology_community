package com.zhonghua.mizi.scm.home;

import com.zhonghua.mizi.model.UserFriendModel;

/**
 * Created by  on 16/1/17.
 */
public interface IArticleScm {

    void addFriend(UserFriendModel userFriendModel,OnArticleLinistener onArticleLinistener);

    void findFriend(UserFriendModel userFriendModel,OnArticleLinistener onArticleLinistener);

}
