package com.zhonghua.mizi.scm.home;
import com.android.volley.VolleyError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.zhonghua.dileber.http.HttpListener;
import com.zhonghua.dileber.mvp.model.SWrapper;
import com.zhonghua.dileber.mvp.scm.Scm;
import com.zhonghua.dileber.tools.SLog;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.app.Api;
import com.zhonghua.mizi.app.Configer;
import com.zhonghua.mizi.model.UserFriendModel;
import com.zhonghua.mizi.model._UserFriendWrapper;
import com.zhonghua.mizi.utils.HuanXinUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by  on 16/1/17.
 */
public class ArticleScm extends Scm implements IArticleScm {

    public interface FriendState{
        int ok = 1;
        int whate = 0;
        int mu = -1;
    }


    @Override
    public void addFriend(final UserFriendModel userFriendModel, final OnArticleLinistener onArticleLinistener) {
        onArticleLinistener.before();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("friendId",userFriendModel.getFriendid());
        map.put("userId",userFriendModel.getUserid());

        netWork(Configer.HTTP_URL + Api.ADD_FRIEND, map, SWrapper.class, new HttpListener<SWrapper>() {
            @Override
            protected void onSuccess(SWrapper response) {
                if(response!=null){
                    if(response.getState()==1){
                        SLog.w(">>>>>>>>>>>>",userFriendModel.getLoginname());
                        HuanXinUtil.addFriend(userFriendModel.getLoginname());
                        onArticleLinistener.success(FriendState.whate);
                        return;
                    }
                }
                onArticleLinistener.success(FriendState.mu);
            }

            @Override
            protected void onFailure(VolleyError error) {
                onArticleLinistener.failed();
            }
        },TYPE_GET);
    }

    @Override
    public void findFriend(UserFriendModel userFriendModel, final OnArticleLinistener onArticleLinistener) {
        onArticleLinistener.before();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("friendId",userFriendModel.getFriendid());
        map.put("userId",userFriendModel.getUserid());

        netWork(Configer.HTTP_URL + Api.FIND_FRIEND, map, _UserFriendWrapper.class, new HttpListener<_UserFriendWrapper>() {
            @Override
            protected void onSuccess(_UserFriendWrapper response) {
                if(response!=null){
                    if(response.getState()==1){
                        int temp = response.getData().getState();
                        onArticleLinistener.success(temp);
                        return;
                    }
                }
                onArticleLinistener.success(FriendState.mu);
            }

            @Override
            protected void onFailure(VolleyError error) {
                onArticleLinistener.failed();
            }
        },TYPE_GET);
    }
}
