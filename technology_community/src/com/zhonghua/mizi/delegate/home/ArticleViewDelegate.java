package com.zhonghua.mizi.delegate.home;

import android.view.View;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import com.zhonghua.dileber.mvp.view.AppViewDelegate;
import com.zhonghua.dileber.tools.SFont;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.delegate.TitleViewDelegate;
import com.zhonghua.mizi.model.ArtModel;
import com.zhonghua.mizi.model.UserModel;
import com.zhonghua.mizi.scm.home.ArticleScm;
import com.zhonghua.mizi.scm.home.IArticleScm;

/**
 * Created by  on 16/1/17.
 */
public class ArticleViewDelegate extends TitleViewDelegate implements IArticleView{


    @Override
    public int getRootLayoutId() {
        return R.layout.activity_article;
    }


    NetworkImageView article_image;
    TextView article_name,article_text;
    SFont article_user_add;
    @Override
    public void initWidget() {
        super.initWidget();

        article_image = get(R.id.article_image);
        article_name = get(R.id.article_name);
        article_text = get(R.id.article_text);
        article_user_add = get(R.id.article_user_add);

    }

    public void setArticle(ArtModel article){
        UserModel userModel = article.getUser();
        if(userModel!=null){
            article_image.setImageUrl(userModel.getUserimage(), R.drawable.icon);
            article_name.setText(userModel.getUsername());
        }

        article_text.setText(article.getContent());
    }

    public void userState(int b){
        article_user_add.setVisibility(View.VISIBLE);
        if(b==ArticleScm.FriendState.ok){
            article_user_add.setText(R.string.user_check);
            article_user_add.setClickable(false);
        }else if(b==ArticleScm.FriendState.mu){
            article_user_add.setText(R.string.user_plus);
            article_user_add.setClickable(true);
        }else if(b==ArticleScm.FriendState.whate){
            article_user_add.setText(R.string.users);
            article_user_add.setClickable(false);
        }
    }

}