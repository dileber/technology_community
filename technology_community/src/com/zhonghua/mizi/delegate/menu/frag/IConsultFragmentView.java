package com.zhonghua.mizi.delegate.menu.frag;

import android.view.View;
import com.zhonghua.mizi.model.ConsultModel;

import java.util.List;

/**
 * Created by shidawei on 16/3/12.
 */
public interface IConsultFragmentView {

    int LAYOUT_LEFT=1;
    int LAYOUT_RIGHT=2;

    void addView(List<View> list,int type);

    List<View> getNeedView(List<ConsultModel> consultModels);

}
