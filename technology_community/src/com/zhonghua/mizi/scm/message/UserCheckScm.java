package com.zhonghua.mizi.scm.message;
import com.android.volley.VolleyError;
import com.zhonghua.dileber.http.HttpListener;
import com.zhonghua.dileber.mvp.model.SWrapper;
import com.zhonghua.dileber.mvp.scm.INetListener;
import com.zhonghua.dileber.mvp.scm.Scm;
import com.zhonghua.dileber.tools.SLog;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.app.Api;
import com.zhonghua.mizi.app.Configer;
import com.zhonghua.mizi.model.UserFriendModel;
import com.zhonghua.mizi.model.UserFriendWrapper;
import com.zhonghua.mizi.utils.HuanXinUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by  on 16/1/17.
 */
public class UserCheckScm extends Scm implements IUserCheckScm {


    @Override
    public void getFriendList(UserFriendModel userFriendModel, final INetListener iNetListener) {
        iNetListener.before();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("userId",userFriendModel.getUserid());
        map.put("type",userFriendModel.getState());
        netWork(Configer.HTTP_URL + Api.FIND_FRIENDS, map, UserFriendWrapper.class, new HttpListener<UserFriendWrapper>() {
            @Override
            protected void onSuccess(UserFriendWrapper response) {
                if(response!=null){
                    if(response.getState()==1){
                        List<UserFriendModel> data = response.getData();
                        if(data!=null) {
                            iNetListener.success(data);
                            return;
                        }
                        iNetListener.errMsg("快快添加好友");
                        return;
                    }
                }
                iNetListener.failed();
            }

            @Override
            protected void onFailure(VolleyError error) {
                iNetListener.failed();
            }
        },TYPE_GET);
    }

    @Override
    public void checkFriend(final UserFriendModel userFriendModel, final INetListener iNetListener) {
        iNetListener.before();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("userFriendId",userFriendModel.getId());
        netWork(Configer.HTTP_URL + Api.OK_FRIEND, map, SWrapper.class, new HttpListener<SWrapper>() {
            @Override
            protected void onSuccess(SWrapper response) {
                if (response != null) {
                    if (response.getState() == 1) {
                        iNetListener.success("添加成功");
                        String userlogname = userFriendModel.getUserModel().getLoginname();
                        SLog.w("<><><><><><>",userlogname);
                        HuanXinUtil.okFriend(userlogname);
                        return;
                    }
                }
                iNetListener.errMsg("添加失败");
            }

            @Override
            protected void onFailure(VolleyError error) {
                iNetListener.failed();
            }
        }, TYPE_GET);
    }
}
