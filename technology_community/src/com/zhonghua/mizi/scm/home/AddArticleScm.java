package com.zhonghua.mizi.scm.home;
import com.android.volley.VolleyError;
import com.zhonghua.dileber.http.HttpListener;
import com.zhonghua.dileber.mvp.model.SWrapper;
import com.zhonghua.dileber.mvp.scm.Scm;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.app.Api;
import com.zhonghua.mizi.app.AppHper;
import com.zhonghua.mizi.app.Configer;
import com.zhonghua.mizi.model.ArtModel;
import com.zhonghua.mizi.model.UserModel;
import com.zhonghua.mizi.presenter.utils.CarouselFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by  on 16/1/17.
 */
public class AddArticleScm extends Scm implements IAddArticleScm {

    @Override
    public void addArticle(ArtModel artModel, final OnAddArticleLinstener onAddArticleLinstener) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("title",artModel.getTitle());
        map.put("content",artModel.getContent());
        map.put("area",1);
        UserModel userModel = AppHper.getAppUser();
        if(userModel==null){
            onAddArticleLinstener.errMsg("您尚未登录，无法发表文章");
            return;
        }
        map.put("userid", userModel.getId());

        netWork(Configer.HTTP_URL + Api.ADD_ART, map, SWrapper.class, new HttpListener<SWrapper>() {
            @Override
            protected void onSuccess(SWrapper response) {
                if(response!=null){
                    if(response.getState()==1){
                        onAddArticleLinstener.success(response.getMsg());
                        return;
                    }
                }
                onAddArticleLinstener.errMsg("发表失败");
            }

            @Override
            protected void onFailure(VolleyError error) {
                onAddArticleLinstener.failed();
            }
        },TYPE_GET);
    }
}
