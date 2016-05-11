package com.zhonghua.mizi.presenter.consult;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.zhonghua.dileber.mvp.presenter.ActivityPresenter;
import com.zhonghua.dileber.tools.annotation.CloseTitle;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.delegate.consult.ConsultInfoViewDelegate;
import com.zhonghua.mizi.model.ConsultModel;
import com.zhonghua.mizi.scm.consult.IConsultInfoScm;
import com.zhonghua.mizi.scm.consult.ConsultInfoScm;

@CloseTitle
public class ConsultInfoActivity extends ActivityPresenter<ConsultInfoViewDelegate>  {

    public static final String CONSULT_INFO = "CONSULT_INFO";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IConsultInfoScm consultinfoSrc = new ConsultInfoScm();

    }

    @Override
    protected Class<ConsultInfoViewDelegate> getDelegateClass() {
        return ConsultInfoViewDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        Intent it = getIntent();
        Object o = it.getSerializableExtra(CONSULT_INFO);
        if(o!=null){
            ConsultModel consultModel = (ConsultModel)o;
            viewDelegate.setConsultInfo(consultModel);
        }
    }


    @Override
    public void onClick(View view) {

    }
}