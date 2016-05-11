package com.zhonghua.mizi.delegate;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import com.zhonghua.dileber.mvp.view.AppViewDelegate;
import com.zhonghua.dileber.tools.HNetwork;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.presenter.LoginActivity;
import com.zhonghua.mizi.presenter.menu.HomeActivity;
import com.zhonghua.mizi.presenter.menu.MineActivity;

/**
 * Created by  on 16/1/17.
 */
public class MainViewDelegate extends AppViewDelegate implements IMainView{


    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void initWidget() {
        super.initWidget();
        get(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivity(it);
            }
        });
    }


    @Override
    public void checkNetwork() {
        if(!HNetwork.getInstance().checkNetwork()){
            toast("网络未链接，请检查网络", Toast.LENGTH_LONG);
        }

    }
}