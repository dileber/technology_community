package com.zhonghua.mizi.presenter.menu;

import android.os.Bundle;
import android.view.View;
import com.zhonghua.dileber.mvp.presenter.ActivityPresenter;
import com.zhonghua.dileber.tools.annotation.CloseTitle;
import com.zhonghua.dileber.tools.annotation.HideKeyboard;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.delegate.menu.ConsultViewDelegate;
import com.zhonghua.mizi.presenter.menu.frag.ConsultFragment;
import com.zhonghua.mizi.presenter.utils.CarouselFragment;
import com.zhonghua.mizi.scm.menu.IConsultScm;
import com.zhonghua.mizi.scm.menu.ConsultScm;

@HideKeyboard
@CloseTitle
public class ConsultActivity extends ActivityPresenter<ConsultViewDelegate>  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IConsultScm consultSrc = new ConsultScm();
        getFragmentManager().beginTransaction().replace(R.id.consult_pubuliu, new ConsultFragment())
                .commit();


    }

    @Override
    protected Class<ConsultViewDelegate> getDelegateClass() {
        return ConsultViewDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
    }


    @Override
    public void onClick(View view) {

    }
}