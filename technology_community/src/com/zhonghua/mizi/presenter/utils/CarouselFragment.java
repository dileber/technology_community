package com.zhonghua.mizi.presenter.utils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.zhonghua.dileber.mvp.presenter.FragmentPresenter;
import com.zhonghua.mizi.delegate.utils.CarouselFragmentViewDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shidawei on 16/3/12.
 */
public class CarouselFragment  extends FragmentPresenter<CarouselFragmentViewDelegate> {
    @Override
    protected Class<CarouselFragmentViewDelegate> getDelegateClass() {
        return CarouselFragmentViewDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        viewDelegate.imageAdapter(null);
    }

    @Override
    public void onClick(View v) {

    }
}