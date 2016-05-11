package com.zhonghua.mizi.delegate.menu.frag;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import com.zhonghua.dileber.mvp.view.AppViewDelegate;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.model.ConsultModel;
import com.zhonghua.mizi.presenter.consult.ConsultInfoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shidawei on 16/3/12.
 */
public class ConsultFragmentDelegate extends AppViewDelegate implements IConsultFragmentView{
    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_consult;
    }

    LinearLayout leftLinear,rightLinear;

    @Override
    public void initWidget() {
        super.initWidget();
        leftLinear = get(R.id.consult_left_column);
        rightLinear = get(R.id.consult_right_column);
    }



    @Override
    public void addView(List<View> list,int type) {
        if(type==LAYOUT_LEFT){
            for(View mv:list){
                leftLinear.addView(mv);
            }
        }else if(type==LAYOUT_RIGHT){
            for(View mv:list){
                rightLinear.addView(mv);
            }
        }

    }

    /**
     * 获得网络请求会的model
     * @param consultModels
     * @return
     */
    @Override
    public List<View> getNeedView(List<ConsultModel> consultModels) {
        if(consultModels==null){
            return null;
        }
        List<View> list = new ArrayList<View>();
        for(int i=0;i<consultModels.size();i++){
            final ConsultModel consultModel = consultModels.get(i);
            LayoutInflater inflater = LayoutInflater.from(this.getActivity());
            final View view = inflater.inflate(R.layout.consult_cloum, null);
            TextView textTitle = (TextView) view.findViewById(R.id.consult_cloum_title);
            textTitle.setText(consultModel.getTitle());
            TextView textContent = (TextView)view.findViewById(R.id.consult_cloum_context);
            textContent.setText(consultModel.getContext());
            NetworkImageView networkImageView = (NetworkImageView)view.findViewById(R.id.consult_cloum_image);
            networkImageView.setImageUrl(consultModel.getImage(),R.drawable.image_loading);
            list.add(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent it = new Intent();
                    it.setClass(getActivity(), ConsultInfoActivity.class);
                    it.putExtra(ConsultInfoActivity.CONSULT_INFO, consultModel);
                    getActivity().startActivity(it);
                }
            });
        }
        return list;
    }


}
