package com.zhonghua.mizi.delegate.menu;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import com.zhonghua.dileber.view.dialog.DialogLinstener;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.app.AppHper;
import com.zhonghua.mizi.model.UserModel;
import com.zhonghua.mizi.presenter.LoginActivity;
import com.zhonghua.mizi.utils.HuanXinUtil;

/**
 * Created by  on 16/1/17.
 */
public class MineViewDelegate extends MenuViewDelegate implements IMineView{


    @Override
    public int getRootLayoutId() {
        return R.layout.activity_mine;
    }


    TextView mine_name;
    NetworkImageView mine_image;
    @Override
    public void initWidget() {
        super.initWidget();
        setOnClickListener(this, R.id.mine_exit, R.id.mine_clear, R.id.mine_update);
        mine_name = get(R.id.mine_name);
        mine_image = get(R.id.mine_image);
        nameShow();
    }

    private void nameShow() {
        String name = "请登录";
        UserModel userModel= AppHper.getAppUser();
        if(userModel!=null){
            name = userModel.getUsername();
            mine_image.setImageUrl(userModel.getUserimage(),R.drawable.icon);
        }
        mine_name.setText(name);
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.mine_exit:
                String title = "";
                boolean login = true;
                if(AppHper.getAppUser()!=null){
                    title = "您确定要退出登录么";
                    login = true;
                }else{
                    title = "您还没有登录呢，去登录";
                    login = false;
                }
                final boolean finalLogin = login;
                dialogOk(title, new DialogLinstener() {
                    @Override
                    public void confirm(Dialog dialog) {
                        AppHper.clearAppUser();
                        Intent it = new Intent();
                        it.setClass(getActivity(), LoginActivity.class);
                        getActivity().startActivity(it);
                        getActivity().finish();
                        dialog.dismiss();

                        if(finalLogin){
                            HuanXinUtil.logOut(null);
                        }

                    }

                    @Override
                    public void cancel(Dialog dialog) {
                        dialog.dismiss();
                    }
                });



                break;
            case R.id.mine_clear:
                dialogOk("确定要清除缓存么", new DialogLinstener() {
                    @Override
                    public void confirm(Dialog dialog) {
                        dialog.dismiss();
                    }

                    @Override
                    public void cancel(Dialog dialog) {
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.mine_update:
                showAlert(null,"当前版本号为1.0\n暂无最新版本");
                break;
        }

    }


}