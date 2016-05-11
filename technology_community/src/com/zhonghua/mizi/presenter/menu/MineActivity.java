package com.zhonghua.mizi.presenter.menu;

import android.os.Bundle;
import android.view.View;
import com.zhonghua.dileber.mvp.presenter.ActivityPresenter;
import com.zhonghua.dileber.tools.annotation.CloseTitle;
import com.zhonghua.dileber.tools.annotation.HideKeyboard;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.delegate.menu.MineViewDelegate;
import com.zhonghua.mizi.scm.menu.IMineScm;
import com.zhonghua.mizi.scm.menu.MineScm;

@HideKeyboard
@CloseTitle
public class MineActivity extends ActivityPresenter<MineViewDelegate>  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IMineScm mineSrc = new MineScm();

    }

    @Override
    protected Class<MineViewDelegate> getDelegateClass() {
        return MineViewDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
    }


    @Override
    public void onClick(View view) {

    }
}