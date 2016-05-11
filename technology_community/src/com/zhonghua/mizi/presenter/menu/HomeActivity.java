package com.zhonghua.mizi.presenter.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.util.NetUtils;
import com.zhonghua.dileber.mvp.presenter.ActivityPresenter;
import com.zhonghua.dileber.tools.SLog;
import com.zhonghua.dileber.tools.annotation.CloseTitle;
import com.zhonghua.dileber.tools.annotation.HideKeyboard;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.app.AppHper;
import com.zhonghua.mizi.delegate.menu.HomeViewDelegate;
import com.zhonghua.mizi.model.ArtModel;
import com.zhonghua.mizi.presenter.home.AddArticleActivity;
import com.zhonghua.mizi.presenter.utils.CarouselFragment;
import com.zhonghua.mizi.scm.menu.IHomeScm;
import com.zhonghua.mizi.scm.menu.HomeScm;
import com.zhonghua.mizi.scm.menu.OnHomeListListener;

import java.util.List;

@HideKeyboard
@CloseTitle
public class HomeActivity extends ActivityPresenter<HomeViewDelegate>  {


    String like="";
    IHomeScm homeSrc;
    PullToRefreshListView home_list;
    int page = 1;
    int count = 10;
    boolean add = false;
    EditText title_search_text;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(R.id.home_carousel, new CarouselFragment())
                .commit();
        homeSrc = new HomeScm();
        page = 1;
        add = false;
        showList();
    }



    private void showList(){
        homeSrc.getHomeList(page, count,like, new OnHomeListListener() {
            @Override
            public void before() {
                viewDelegate.loading();
            }

            @Override
            public void success(Object model) {
                viewDelegate.loadDialogDismiss();
                List<ArtModel> artModels = (List<ArtModel>) model;
                if(add){
                    viewDelegate.addList(artModels);
                    if(artModels.size()==0){
                        page --;
                        if(page<1){
                            page = 1;
                        }
                    }
                }else{
                    viewDelegate.showList(artModels);
                }
                home_list.onRefreshComplete();

            }

            @Override
            public void failed() {
                viewDelegate.loadDialogDismiss();
                home_list.onRefreshComplete();
            }

            @Override
            public void errMsg(String msg) {

            }
        });
    }

    @Override
    protected Class<HomeViewDelegate> getDelegateClass() {
        return HomeViewDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();

        if(AppHper.getAppUser()!=null){
            //注册一个监听连接状态的listener
            EMClient.getInstance().addConnectionListener(new MyConnectionListener());
        }


        home_list = viewDelegate.get(R.id.home_list);
        home_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = android.text.format.DateUtils.formatDateTime(
                        HomeActivity.this.getApplicationContext(),
                        System.currentTimeMillis(),
                        android.text.format.DateUtils.FORMAT_SHOW_TIME
                                | android.text.format.DateUtils.FORMAT_SHOW_DATE
                                | android.text.format.DateUtils.FORMAT_ABBREV_ALL);
                refreshView.getLoadingLayoutProxy()
                        .setLastUpdatedLabel(label);
                //home_list.onRefreshComplete();
                page = 1;
                add = false;
                like = "";
                viewDelegate.cleraSearch();
                showList();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                home_list.getLoadingLayoutProxy(false, true)
                        .setLastUpdatedLabel("上拉加载");
                home_list.getLoadingLayoutProxy(false, true)
                        .setPullLabel("");
                home_list.getLoadingLayoutProxy(false, true)
                        .setRefreshingLabel("正在加载...");
                home_list.getLoadingLayoutProxy(false, true)
                        .setReleaseLabel("放开以加载");
                page++;
                add = true;
                showList();
            }
        });


        title_search_text = viewDelegate.get(R.id.title_search_text);
        title_search_text.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // TODO Auto-generated method stub
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // 先隐藏键盘
                    page = 1;
                    add = false;
                    like = title_search_text.getText().toString();
                    showList();
                    return true;
                }
                return false;


            }
        });

    }




    @Override
    public void onClick(View view) {

    }

    //实现ConnectionListener接口
    private class MyConnectionListener implements EMConnectionListener {
        @Override
        public void onConnected() {
            //已连接到服务器
        }
        @Override
        public void onDisconnected(final int error) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if(error == EMError.USER_REMOVED){
                        // 显示帐号已经被移除
                        SLog.e("error>>>>>",error);
                    }else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                        // 显示帐号在其他设备登陆
                        SLog.e("error>>>>>",error);
                    } else {
                        if (NetUtils.hasNetwork(HomeActivity.this)){
                            //连接不到聊天服务器
                        }else{
                            //当前网络不可用，请检查网络设置
                        }
                    }
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if(requestCode== AddArticleActivity.REQUEST_CODE) {
            if(resultCode==AddArticleActivity.RESULT_CODE) {
                page = 1;
                add = false;
                showList();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

}