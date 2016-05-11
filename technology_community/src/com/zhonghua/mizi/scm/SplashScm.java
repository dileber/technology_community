package com.zhonghua.mizi.scm;
import com.zhonghua.dileber.mvp.scm.Scm;
import com.zhonghua.dileber.tools.SLog;
import com.zhonghua.mizi.app.AppHper;
import com.zhonghua.mizi.model.UserModel;
import com.zhonghua.mizi.utils.HuanXinUtil;

/**
 * Created by  on 16/1/17.
 */
public class SplashScm extends Scm implements ISplashScm {

    @Override
    public void splash() {
        SLog.w("Ok");
        UserModel userModel = AppHper.getAppUser();
        if(userModel!=null){
            HuanXinUtil.login(userModel,null);
        }
    }






}
