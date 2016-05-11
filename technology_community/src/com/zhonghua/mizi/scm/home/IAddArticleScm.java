package com.zhonghua.mizi.scm.home;

import com.zhonghua.mizi.model.ArtModel;

/**
 * Created by  on 16/1/17.
 */
public interface IAddArticleScm {

    void addArticle(ArtModel artModel,OnAddArticleLinstener onAddArticleLinstener);

}
