package com.zhonghua.mizi.delegate.home;

import android.widget.TextView;
import com.zhonghua.dileber.mvp.view.AppViewDelegate;
import com.zhonghua.dileber.tools.annotation.CloseTitle;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.delegate.TitleViewDelegate;
import com.zhonghua.mizi.exception.ArticleException;
import com.zhonghua.mizi.model.ArtModel;

/**
 * Created by  on 16/1/17.
 */
@CloseTitle
public class AddArticleViewDelegate extends TitleViewDelegate implements IAddArticleView{



    @Override
    public int getRootLayoutId() {
        return R.layout.activity_addarticle;
    }

    TextView add_article_text,add_article_content,add_article_btn;
    @Override
    public void initWidget() {
        super.initWidget();
        add_article_text = get(R.id.add_article_text);
        add_article_btn = get(R.id.add_article_btn);
        add_article_content = get(R.id.add_article_content);
    }


    public ArtModel getArticle() throws ArticleException {
        String text = add_article_text.getText().toString().trim();
        String content = add_article_content.getText().toString().trim();
        if(text.equals("")){
            throw new ArticleException("标题不能为空");
        }
        if(content.equals("")){
            throw new ArticleException("内容不能为空");
        }
        ArtModel artModel = new ArtModel();
        artModel.setTitle(text);
        artModel.setContent(content);
        return artModel;
    }



}