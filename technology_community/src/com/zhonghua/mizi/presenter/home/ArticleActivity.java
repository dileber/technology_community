package com.zhonghua.mizi.presenter.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.hyphenate.chat.EMClient;
import com.zhonghua.dileber.mvp.presenter.ActivityPresenter;
import com.zhonghua.dileber.tools.annotation.CloseTitle;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.app.AppHper;
import com.zhonghua.mizi.delegate.home.ArticleViewDelegate;
import com.zhonghua.mizi.model.ArtModel;
import com.zhonghua.mizi.model.UserFriendModel;
import com.zhonghua.mizi.model.UserModel;
import com.zhonghua.mizi.scm.home.IArticleScm;
import com.zhonghua.mizi.scm.home.ArticleScm;
import com.zhonghua.mizi.scm.home.OnArticleLinistener;

@CloseTitle
public class ArticleActivity extends ActivityPresenter<ArticleViewDelegate>  {

    public static final String ARTICLE="ARTICLE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected Class<ArticleViewDelegate> getDelegateClass() {
        return ArticleViewDelegate.class;
    }
    IArticleScm articleSrc = null;
    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        articleSrc = new ArticleScm();
        Intent it = getIntent();
        Object o = it.getSerializableExtra(ARTICLE);
        if(o!=null){
            ArtModel artModel = (ArtModel)o;
            viewDelegate.setArticle(artModel);

            UserModel userModel = AppHper.getAppUser();
            if(userModel!=null){
                int userid = userModel.getId();
                int friendid = artModel.getUserid();
                final UserFriendModel userFriendModel = new UserFriendModel();
                userFriendModel.setFriendid(friendid);
                userFriendModel.setUserid(userid);
                userFriendModel.setLoginname(artModel.getUser().getLoginname());
                articleSrc.findFriend(userFriendModel, new OnArticleLinistener() {
                    @Override
                    public void errMsg(String str) {

                    }

                    @Override
                    public void before() {

                    }

                    @Override
                    public void success(Object model) {
                        int b = (Integer) model;
                        viewDelegate.userState(b);
                    }

                    @Override
                    public void failed() {

                    }
                });

                viewDelegate.get(R.id.article_user_add).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {
                        articleSrc.addFriend(userFriendModel, new OnArticleLinistener() {
                            @Override
                            public void errMsg(String str) {

                            }

                            @Override
                            public void before() {

                            }

                            @Override
                            public void success(Object model) {
                                int b = (Integer) model;
                                viewDelegate.userState(b);
                                if(b==ArticleScm.FriendState.whate){
                                    viewDelegate.toast("申请成功，请等待对方添加您为好友",Toast.LENGTH_SHORT);
                                }else {
                                    viewDelegate.toast("添加失败",Toast.LENGTH_SHORT);
                                }
                            }

                            @Override
                            public void failed() {

                            }
                        });
                    }
                });

            }


        }else{
            viewDelegate.toast("数据传输错误", Toast.LENGTH_SHORT);
        }


    }


    @Override
    public void onClick(View view) {

    }
}