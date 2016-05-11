package com.zhonghua.mizi.presenter.menu.frag;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;
import com.zhonghua.dileber.http.HttpListener;
import com.zhonghua.dileber.mvp.presenter.FragmentPresenter;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.delegate.menu.frag.ConsultFragmentDelegate;
import com.zhonghua.mizi.model.ConsultModel;
import com.zhonghua.mizi.model.ConsultWrapper;
import com.zhonghua.mizi.scm.menu.ConsultScm;
import com.zhonghua.mizi.scm.menu.IConsultScm;
import com.zhonghua.mizi.scm.menu.OnConsultListListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shidawei on 16/3/12.
 */
public class ConsultFragment extends FragmentPresenter<ConsultFragmentDelegate>{
    @Override
    protected Class<ConsultFragmentDelegate> getDelegateClass() {
        return ConsultFragmentDelegate.class;
    }
    IConsultScm iConsultScm = null;
    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        iConsultScm = new ConsultScm();
        iConsultScm.getConsult(0, 30, new OnConsultListListener() {
            @Override
            public void leftList(List left) {
                List<View> list_left_view = viewDelegate.getNeedView(left);
                viewDelegate.addView(list_left_view, ConsultFragmentDelegate.LAYOUT_LEFT);
            }

            @Override
            public void rightList(List right) {
                List<View> list_right_view = viewDelegate.getNeedView(right);
                viewDelegate.addView(list_right_view, ConsultFragmentDelegate.LAYOUT_RIGHT);
            }

            @Override
            public void before() {
                viewDelegate.loading();
            }

            @Override
            public void success(Object model) {
                viewDelegate.loadDialogDismiss();
            }

            @Override
            public void failed() {
                viewDelegate.loadDialogDismiss();
            }

            @Override
            public void errMsg(String msg) {

            }
        });

    }

    @Override
    public void onClick(View view) {

    }
}
