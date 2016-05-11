package com.zhonghua.mizi.delegate;

import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import com.zhonghua.dileber.mvp.view.AppViewDelegate;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.presenter.RegisterActivity;
import com.zhonghua.mizi.presenter.consult.ConsultInfoActivity;
import com.zhonghua.mizi.presenter.home.AddArticleActivity;
import com.zhonghua.mizi.presenter.home.ArticleActivity;
import com.zhonghua.mizi.presenter.menu.ConsultActivity;
import com.zhonghua.mizi.presenter.menu.HomeActivity;
import android.view.WindowManager.LayoutParams;
import com.zhonghua.mizi.presenter.message.UserCheckActivity;

/**
 * title
 * Created by shidawei on 16/3/12.
 */
public abstract class TitleViewDelegate extends AppViewDelegate implements View.OnClickListener{

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        setOnClickListener(this, R.id.title_pop,R.id.title_back);
    }

    private void initTitle() {
        View search = get(R.id.title_search_text);
        View left = get(R.id.title_left);
        View name = get(R.id.title_name);
        View back = get(R.id.title_back);
        View menu = get(R.id.title_pop);
        if(getActivity() instanceof HomeActivity){
            search.setVisibility(View.VISIBLE);
            left.setVisibility(View.VISIBLE);
        }else{
            name.setVisibility(View.VISIBLE);
            if(getActivity() instanceof RegisterActivity||getActivity() instanceof ArticleActivity||
                    getActivity() instanceof AddArticleActivity||getActivity() instanceof ConsultInfoActivity
                    ||getActivity() instanceof UserCheckActivity){
                back.setVisibility(View.VISIBLE);
                name.setVisibility(View.GONE);
                menu.setVisibility(View.GONE);
            }
        }

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.title_pop:
                btnAllPageTitlePopmenuOnClick(view);
                break;
            case R.id.title_back:
                getActivity().finish();
                break;
            default:

        }
    }

    WindowManager wm;

    /**
     * show popmenu
      * @param view
     */
    private void btnAllPageTitlePopmenuOnClick(View view){
        wm = getActivity().getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        View popView = getActivity().getLayoutInflater().inflate(
                R.layout.pop_menu, null);
        final PopupWindow popupWindow = new PopupWindow(popView,
                width / 2, LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        // 获取popwindow焦点
        popupWindow.setFocusable(true);
        // 设置popwindow如果点击外面区域，便关闭。
        popupWindow.setOutsideTouchable(true);
        int[] location = new int[2];
        view.getLocationOnScreen(location);

        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0],
                location[1] + 53);

        Button pop_version = (Button) popView.findViewById(R.id.pop_version);

        Button pop_me = (Button) popView.findViewById(R.id.pop_me);

        pop_version.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showAlert(null,"当前版本号为1.0\n暂无最新版本");
                popupWindow.dismiss();
            }
        });

        pop_me.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showAlert(null,"DrCoSu工作室\n史大伟");
                popupWindow.dismiss();
            }
        });


    }

}
