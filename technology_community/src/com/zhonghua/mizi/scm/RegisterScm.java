package com.zhonghua.mizi.scm;
import com.android.volley.VolleyError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.zhonghua.dileber.http.HttpListener;
import com.zhonghua.dileber.mvp.scm.Scm;
import com.zhonghua.dileber.tools.SLog;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.app.Api;
import com.zhonghua.mizi.app.AppHper;
import com.zhonghua.mizi.app.Configer;
import com.zhonghua.mizi.model.UserModel;
import com.zhonghua.mizi.model.UserWrapper;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by  on 16/1/17.
 */
public class RegisterScm extends Scm implements IRegisterScm {

    @Override
    public void regist(UserModel userModel, final OnRegisterLinstener onRegisterLinstener) {
        onRegisterLinstener.before();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("username",userModel.getUsername());
        map.put("userpass",userModel.getUserpass());
        map.put("apptype",Configer.app_type);
        map.put("loginname",userModel.getLoginname());

        netWork(Configer.HTTP_URL + Api.ADD_REGIST, map, UserWrapper.class, new HttpListener<UserWrapper>() {
            @Override
            protected void onSuccess(UserWrapper response) {
                if(response!=null){
                    if(response.getState()==1){
                        UserModel userModel1 = response.getData();
                        if(userModel1==null){
                            onRegisterLinstener.errorMsg("用户异常");
                            return;
                        }
                        AppHper.putAppUser(response.getData());
                        onRegisterLinstener.success(response.getData());
                    }else if(response.getState()==-1){
                        onRegisterLinstener.errorMsg(response.getMsg());
                    }
                }
                onRegisterLinstener.failed();
            }

            @Override
            protected void onFailure(VolleyError error) {
                onRegisterLinstener.failed();
            }
        },TYPE_POST);
    }


}
