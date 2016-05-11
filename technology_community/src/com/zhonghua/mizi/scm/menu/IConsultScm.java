package com.zhonghua.mizi.scm.menu;

import com.zhonghua.dileber.http.HttpListener;
import com.zhonghua.mizi.model.ConsultWrapper;


/**
 * Created by  on 16/1/17.
 */
public interface IConsultScm {

    void getConsult(Integer page, Integer count, final OnConsultListListener onConsultListListener) ;

}
