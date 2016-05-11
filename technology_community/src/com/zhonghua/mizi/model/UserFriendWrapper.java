package com.zhonghua.mizi.model;

import com.zhonghua.dileber.mvp.model.SWrapper;

import java.util.List;

/**
 * Created by shidawei on 16/3/21.
 */
public class UserFriendWrapper extends SWrapper {

    List<UserFriendModel> data;

    public List<UserFriendModel> getData() {
        return data;
    }

    public void setData(List<UserFriendModel> data) {
        this.data = data;
    }

}
