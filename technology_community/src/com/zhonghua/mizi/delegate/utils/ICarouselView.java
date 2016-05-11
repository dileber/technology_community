package com.zhonghua.mizi.delegate.utils;

import android.view.View;

import java.util.List;

/**
 * Created by shidawei on 16/3/12.
 */
public interface ICarouselView {

    void imageAdapter(List<String> urlm);

    View imageViews(String url);

}
