package com.zhonghua.mizi.scm;
import com.android.volley.VolleyError;
import com.zhonghua.dileber.data.PerfManager;
import com.zhonghua.dileber.http.HttpListener;
import com.zhonghua.dileber.mvp.scm.Scm;
import com.zhonghua.dileber.tools.HJson;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.app.Api;
import com.zhonghua.mizi.app.AppHper;
import com.zhonghua.mizi.app.Configer;
import com.zhonghua.mizi.model.UserModel;
import com.zhonghua.mizi.model.UserWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by  on 16/1/17.
 */
public class LoginScm extends Scm implements ILoginScm {

    public void login(UserModel userModel, final OnLoginLinstener onLoginLinstener){
        onLoginLinstener.before();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("loginname",userModel.getLoginname());
        map.put("userpass",userModel.getUserpass());
        map.put("apptype",Configer.app_type);
        netWork(Configer.HTTP_URL + Api.ADD_LOGIN, map, UserWrapper.class, new HttpListener<UserWrapper>() {
            @Override
            protected void onSuccess(UserWrapper response) {
                if(response.getState()==1){
                    UserModel userModel1 = response.getData();
                    if(userModel1==null){
                        onLoginLinstener.errorMsg("用户异常");
                        return;
                    }
                    AppHper.putAppUser(response.getData());
                    onLoginLinstener.success(response.getData());
                }else if(response.getState()==-1){
                    onLoginLinstener.errorMsg(response.getMsg());
                }else{
                    onLoginLinstener.failed();
                }
            }

            @Override
            protected void onFailure(VolleyError error) {

                onLoginLinstener.failed();
            }
        },TYPE_GET);
    }


}
