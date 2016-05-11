package com.zhonghua.mizi.scm.menu;
import com.android.volley.VolleyError;
import com.zhonghua.dileber.http.HttpListener;
import com.zhonghua.dileber.mvp.scm.Scm;
import com.zhonghua.dileber.tools.HJson;
import com.zhonghua.dileber.tools.SLog;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.app.Api;
import com.zhonghua.mizi.app.Configer;
import com.zhonghua.mizi.model.ArtModel;
import com.zhonghua.mizi.model.ArtWrapper;
import com.zhonghua.mizi.model.ConsultModel;
import com.zhonghua.mizi.model.ConsultWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by  on 16/1/17.
 */
public class HomeScm extends Scm implements IHomeScm {

    @Override
    public void getHomeList(int page, int count,String like, final OnHomeListListener onHomeListListener) {
        onHomeListListener.before();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("page",page);
        map.put("count",count);
        map.put("like",like);


        netWork(Configer.HTTP_URL + Api.FIND_ART, map, ArtWrapper.class, new HttpListener<ArtWrapper>() {
            @Override
            protected void onSuccess(ArtWrapper response) {
                if(response!=null){
                    if(response.getState()==1){
                        if(response.getData()!=null){
                            onHomeListListener.success(response.getData());
                            return;
                        }

                    }
                }
                onHomeListListener.failed();
            }

            @Override
            protected void onFailure(VolleyError error) {
                onHomeListListener.failed();
            }
        }, TYPE_GET);
    }
}
