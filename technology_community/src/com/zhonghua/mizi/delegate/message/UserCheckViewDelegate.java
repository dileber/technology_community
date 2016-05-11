package com.zhonghua.mizi.delegate.message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.toolbox.NetworkImageView;
import com.zhonghua.dileber.mvp.scm.INetListener;
import com.zhonghua.dileber.mvp.view.AppViewDelegate;
import com.zhonghua.dileber.tools.UTime;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.delegate.TitleViewDelegate;
import com.zhonghua.mizi.model.ArtModel;
import com.zhonghua.mizi.model.UserFriendModel;
import com.zhonghua.mizi.model.UserModel;
import com.zhonghua.mizi.scm.message.IUserCheckScm;
import com.zhonghua.mizi.scm.message.UserCheckScm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  on 16/1/17.
 */
public class UserCheckViewDelegate extends TitleViewDelegate implements IUserCheckView{


    @Override
    public int getRootLayoutId() {
        return R.layout.activity_usercheck;
    }

    ListView usercheck_list;
    List<UserFriendModel> userFriendModels;
    CheckAdapter checkAdapter;
    IUserCheckScm iUserCheckScm;

    @Override
    public void initWidget() {
        super.initWidget();
        usercheck_list = get(R.id.usercheck_list);
        checkAdapter = new CheckAdapter(getActivity());
        userFriendModels = new ArrayList<UserFriendModel>();
        usercheck_list.setAdapter(checkAdapter);
        iUserCheckScm = new UserCheckScm();
    }

    public void showListView(List<UserFriendModel> userFriendModel){
        checkAdapter.setList(userFriendModel);
        checkAdapter.notifyDataSetChanged();
    }

    public class CheckAdapter extends BaseAdapter{
        private LayoutInflater mInflater;

        class CellHolder{
            NetworkImageView user_check_image;
            TextView user_check_name;
            TextView usercheck_btn;
        }

        private void setList(List<UserFriendModel> userFriendModel){
            userFriendModels = userFriendModel;
        }

        public CheckAdapter(Context context){
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return userFriendModels.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            CellHolder _cellHolder = null;

            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_usercheck, null);
                _cellHolder = new CellHolder();
                _cellHolder.usercheck_btn = (TextView) convertView.findViewById(R.id.usercheck_btn);
                _cellHolder.user_check_name = (TextView) convertView.findViewById(R.id.user_check_name);
                _cellHolder.user_check_image = (NetworkImageView) convertView.findViewById(R.id.user_check_image);
                convertView.setTag(_cellHolder);
            } else {
                _cellHolder = (CellHolder) convertView.getTag();
            }

            final UserFriendModel userFriendModel = userFriendModels.get(position);
            UserModel userModel = userFriendModel.getUserModel();
            if(userModel!=null){
                _cellHolder.user_check_image.setImageUrl(userModel.getUserimage(), R.drawable.image_loading);
                _cellHolder.user_check_name.setText(userModel.getUsername());
            }

            _cellHolder.usercheck_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iUserCheckScm.checkFriend(userFriendModel, new INetListener() {
                        @Override
                        public void before() {

                        }

                        @Override
                        public void success(Object model) {
                            if(model!=null){
                                String str = (String)model;
                                toast(str, Toast.LENGTH_SHORT);
                                removeData(position);
                            }
                        }

                        @Override
                        public void failed() {

                        }

                        @Override
                        public void errMsg(String msg) {
                            toast(msg, Toast.LENGTH_SHORT);
                        }
                    });
                }
            });

            return convertView;
        }
    }

    private void removeData(int position){
        userFriendModels.remove(position);
        checkAdapter.notifyDataSetChanged();
    }

}