package com.zhonghua.mizi.delegate.menu;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import com.zhonghua.dileber.data.db.annotation.Insert;
import com.zhonghua.dileber.mvp.view.AppViewDelegate;
import com.zhonghua.dileber.tools.SLog;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.delegate.TitleViewDelegate;
import com.zhonghua.mizi.presenter.menu.ConsultActivity;
import com.zhonghua.mizi.presenter.menu.HomeActivity;
import com.zhonghua.mizi.presenter.menu.MessageActivity;
import com.zhonghua.mizi.presenter.menu.MineActivity;

import static com.zhonghua.mizi.R.color.blue_deep;

/**
 * 菜单类
 * Created by shidawei on 16/3/11.
 */
public abstract class MenuViewDelegate extends TitleViewDelegate {

    @Override
    public void initWidget() {
        super.initWidget();
        initMenu();
        setOnClickListener(this, R.id.all_comm, R.id.all_index, R.id.all_setting, R.id.all_zixun);
    }

    private void initMenu(){
        View view = null;
        if(getActivity() instanceof MessageActivity){
            view = get(R.id.all_comm);
        }else if(getActivity() instanceof HomeActivity){
            view = get(R.id.all_index);
        }else if(getActivity() instanceof MineActivity){
            view = get(R.id.all_setting);
        }else if(getActivity() instanceof ConsultActivity){
            view = get(R.id.all_zixun);
        }
        if(view!=null){
            view.setBackgroundColor(blue_deep);
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        Intent it = new Intent();
        boolean jump = true;
        switch (view.getId()){
            case R.id.all_comm:
                it.setClass(getActivity(), MessageActivity.class);
                break;
            case R.id.all_index:
                it.setClass(getActivity(), HomeActivity.class);
                break;
            case R.id.all_setting:
                it.setClass(getActivity(), MineActivity.class);
                break;
            case R.id.all_zixun:
                it.setClass(getActivity(), ConsultActivity.class);
                break;
            default:
                jump = false;
        }
        if(jump){
            SLog.i("<<<<<<<<<<", getActivity().getComponentName(), it.getComponent());
            getActivity().startActivity(it);
            getActivity().overridePendingTransition(0, 0);
            if(getActivity().getComponentName().compareTo(it.getComponent())!=0){
                getActivity().finish();
            }
        }


    }
}
