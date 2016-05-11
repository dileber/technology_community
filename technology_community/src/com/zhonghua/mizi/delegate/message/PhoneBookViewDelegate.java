package com.zhonghua.mizi.delegate.message;

import com.zhonghua.dileber.mvp.view.AppViewDelegate;
import com.zhonghua.mizi.R;

/**
 * Created by  on 16/1/17.
 */
public class PhoneBookViewDelegate extends AppViewDelegate implements IPhoneBookView{


    @Override
    public int getRootLayoutId() {
        return R.layout.activity_phonebook;
    }


    @Override
    public void initWidget() {
        super.initWidget();
    }

}