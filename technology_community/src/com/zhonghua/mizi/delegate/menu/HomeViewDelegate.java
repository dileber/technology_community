package com.zhonghua.mizi.delegate.menu;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.android.volley.toolbox.NetworkImageView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhonghua.dileber.mvp.view.AppViewDelegate;
import com.zhonghua.dileber.tools.UTime;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.model.ArtModel;
import com.zhonghua.mizi.model.UserModel;
import com.zhonghua.mizi.presenter.home.AddArticleActivity;
import com.zhonghua.mizi.presenter.home.ArticleActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  on 16/1/17.
 */
public class HomeViewDelegate extends MenuViewDelegate implements IHomeView{


    @Override
    public int getRootLayoutId() {
        return R.layout.activity_home;
    }

    HomeListAdapter homeListAdapter;

    PullToRefreshListView home_list;
    EditText title_search_text;

    @Override
    public void initWidget() {
        super.initWidget();
        home_list = get(R.id.home_list);
        home_list.setMode(PullToRefreshBase.Mode.BOTH);
        homeListAdapter = new HomeListAdapter(getActivity());
        setOnClickListener(this, R.id.title_left);
        title_search_text = get(R.id.title_search_text);
        homeListAdapter.setArtModels(artModels_);
        home_list.setAdapter(homeListAdapter);
        home_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent();
                it.setClass(getActivity(), ArticleActivity.class);
                it.putExtra(ArticleActivity.ARTICLE, artModels_.get(i-1));
                getActivity().startActivity(it);

            }
        });
    }

    public void cleraSearch(){
        title_search_text.setText("");
    }

    List<ArtModel> artModels_ = new ArrayList<ArtModel>();

    public void showList(List<ArtModel> artModels){

        homeListAdapter.setArtModels(artModels);
        homeListAdapter.notifyDataSetChanged();
    }


    public void addList(List<ArtModel> artModels){
        homeListAdapter.addArtModel(artModels);
        homeListAdapter.notifyDataSetChanged();
    }

    public class HomeListAdapter extends BaseAdapter{
        private LayoutInflater mInflater;
        class CellHolder{
            TextView list_home_name;
            NetworkImageView list_home_image;
            TextView list_home_title;
            TextView list_home_content;
            TextView list_home_time;
        }


        public HomeListAdapter(Context context){
            this.mInflater = LayoutInflater.from(context);
        }

        public void setArtModels(List<ArtModel> artModels) {
            artModels_ = artModels;
        }


        public void addArtModel(List<ArtModel> artModels){

            artModels_.addAll(artModels);

        }


        @Override
        public int getCount() {
            return artModels_.size();
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
        public View getView(int position, View convertView, ViewGroup parent) {
            CellHolder _cellHolder = null;

            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_home, null);
                _cellHolder = new CellHolder();
                _cellHolder.list_home_content = (TextView) convertView.findViewById(R.id.list_home_content);
                _cellHolder.list_home_name = (TextView) convertView.findViewById(R.id.list_home_name);
                _cellHolder.list_home_time = (TextView) convertView.findViewById(R.id.list_home_time);
                _cellHolder.list_home_title = (TextView) convertView.findViewById(R.id.list_home_title);
                _cellHolder.list_home_image = (NetworkImageView) convertView.findViewById(R.id.list_home_image);
                convertView.setTag(_cellHolder);
            } else {
                _cellHolder = (CellHolder) convertView.getTag();
            }
            ArtModel artModel = artModels_.get(position);
            _cellHolder.list_home_content.setText(artModel.getContent());

            _cellHolder.list_home_time.setText(UTime.getDateStr(UTime.Pattern.y_m_d_h_m_s,artModel.getTime()));
            _cellHolder.list_home_title.setText(artModel.getTitle());
            UserModel userModel = artModel.getUser();
            if(userModel!=null){
                _cellHolder.list_home_image.setImageUrl(userModel.getUserimage(),R.drawable.image_loading);
                _cellHolder.list_home_name.setText(userModel.getUsername());
            }

            return convertView;
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.title_left:
                Intent it = new Intent();
                it.setClass(getActivity(), AddArticleActivity.class);

                getActivity().startActivityForResult(it, AddArticleActivity.REQUEST_CODE);
                break;
        }
    }
}