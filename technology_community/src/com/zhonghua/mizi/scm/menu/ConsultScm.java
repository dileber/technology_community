package com.zhonghua.mizi.scm.menu;
import android.view.View;
import com.android.volley.VolleyError;
import com.zhonghua.dileber.http.HttpListener;
import com.zhonghua.dileber.mvp.scm.Scm;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.app.Api;
import com.zhonghua.mizi.app.Configer;
import com.zhonghua.mizi.delegate.menu.frag.ConsultFragmentDelegate;
import com.zhonghua.mizi.model.ConsultModel;
import com.zhonghua.mizi.model.ConsultWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by  on 16/1/17.
 */
public class ConsultScm extends Scm implements IConsultScm {

    @Override
    public void getConsult(Integer page, Integer count, final OnConsultListListener onConsultListListener) {
        onConsultListListener.before();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("page",page);
        map.put("count",count);
        netWork(Configer.HTTP_URL + Api.FIND_CONSULT, map, ConsultWrapper.class, new HttpListener<ConsultWrapper>() {
            @Override
            protected void onSuccess(ConsultWrapper response) {
                if(response!=null){
                    if(response.getState()==1){
                        if(response.getData()!=null){
                            List<ConsultModel> list_left = new ArrayList<ConsultModel>();
                            List<ConsultModel> list_right =  new ArrayList<ConsultModel>();
                            List<ConsultModel> list = response.getData();
                            int size = list.size();
                            for(int i=0;i<size;i++){
                                if(size/2>i){
                                    list_left.add(list.get(i));
                                }else {
                                    list_right.add(list.get(i));
                                }
                            }
                            onConsultListListener.leftList(list_left);
                            onConsultListListener.rightList(list_right);
                            onConsultListListener.success(null);
                            return;
                        }

                    }
                }
                onConsultListListener.failed();
            }

            @Override
            protected void onFailure(VolleyError error) {
                onConsultListListener.failed();
            }
        }, TYPE_GET);
    }
}
