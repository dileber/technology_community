package com.zhonghua.mizi.scm.menu;

import com.zhonghua.dileber.mvp.scm.INetListener;
import com.zhonghua.mizi.model.ConsultModel;

import java.util.List;

/**
 * Created by shidawei on 16/3/13.
 */
public interface OnConsultListListener<T> extends INetListener<T> {

    void leftList(List<ConsultModel> left);

    void rightList(List<ConsultModel> right);


}
