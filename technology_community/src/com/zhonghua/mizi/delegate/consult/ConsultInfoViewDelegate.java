package com.zhonghua.mizi.delegate.consult;

import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import com.zhonghua.dileber.mvp.view.AppViewDelegate;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.delegate.TitleViewDelegate;
import com.zhonghua.mizi.model.ConsultModel;

/**
 * Created by  on 16/1/17.
 */
public class ConsultInfoViewDelegate extends TitleViewDelegate implements IConsultInfoView{


    @Override
    public int getRootLayoutId() {
        return R.layout.activity_consultinfo;
    }


    NetworkImageView consultinof_image;
    TextView consultinfo_text,consultinof_context;
    @Override
    public void initWidget() {
        super.initWidget();
        consultinfo_text = get(R.id.consultinfo_text);
        consultinof_context = get(R.id.consultinof_context);
        consultinof_image = get(R.id.consultinof_image);
    }

    public void setConsultInfo(ConsultModel consultInfo){
        consultinof_image.setImageUrl(consultInfo.getImage(), R.drawable.icon);
        consultinof_context.setText(consultInfo.getContext());
        consultinfo_text.setText(consultInfo.getTitle());
    }

}