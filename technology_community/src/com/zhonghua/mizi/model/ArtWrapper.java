package com.zhonghua.mizi.model;

import com.zhonghua.dileber.mvp.model.SWrapper;

import java.util.List;

/**
 * Created by shidawei on 16/3/12.
 */
public class ArtWrapper extends SWrapper{

    List<ArtModel> data;

    public List<ArtModel> getData() {
        return data;
    }

    public void setData(List<ArtModel> data) {
        this.data = data;
    }
}
