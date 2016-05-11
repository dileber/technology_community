package com.zhonghua.mizi.presenter.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.zhonghua.dileber.mvp.presenter.ActivityPresenter;
import com.zhonghua.dileber.tools.annotation.CloseTitle;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.delegate.home.AddArticleViewDelegate;
import com.zhonghua.mizi.exception.ArticleException;
import com.zhonghua.mizi.model.ArtModel;
import com.zhonghua.mizi.scm.home.IAddArticleScm;
import com.zhonghua.mizi.scm.home.AddArticleScm;
import com.zhonghua.mizi.scm.home.OnAddArticleLinstener;

@CloseTitle
public class AddArticleActivity extends ActivityPresenter<AddArticleViewDelegate>  {

    public static final int RESULT_CODE=101;
    public static final int REQUEST_CODE=1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected Class<AddArticleViewDelegate> getDelegateClass() {
        return AddArticleViewDelegate.class;
    }

    IAddArticleScm addarticleSrc;

    Intent it = null;

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        getIntent();
        addarticleSrc = new AddArticleScm();
        viewDelegate.get(R.id.add_article_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArtModel artModel = null;
                try {
                    artModel = viewDelegate.getArticle();
                } catch (ArticleException e) {
                    e.printStackTrace();
                    viewDelegate.toast(e.getMessage(), Toast.LENGTH_SHORT);
                    return;
                }

                addarticleSrc.addArticle(artModel, new OnAddArticleLinstener() {
                    @Override
                    public void before() {

                    }

                    @Override
                    public void success(Object model) {
                        viewDelegate.toast("发表成功",Toast.LENGTH_SHORT);
                        setResult(RESULT_CODE, it);
                        finish();
                    }

                    @Override
                    public void failed() {
                        viewDelegate.toast("发表失败",Toast.LENGTH_SHORT);
                    }

                    @Override
                    public void errMsg(String msg) {
                        viewDelegate.toast(msg,Toast.LENGTH_SHORT);
                    }
                });
            }
        });
    }


    @Override
    public void onClick(View view) {

    }
}