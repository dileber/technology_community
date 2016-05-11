package com.zhonghua.mizi.presenter.message;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.zhonghua.dileber.mvp.presenter.ActivityPresenter;
import com.zhonghua.dileber.mvp.presenter.FragmentActivityPresenter;
import com.zhonghua.dileber.mvp.scm.INetListener;
import com.zhonghua.dileber.tools.annotation.CloseTitle;
import com.zhonghua.mizi.R;
import com.zhonghua.mizi.app.AppHper;
import com.zhonghua.mizi.delegate.message.PhoneBookViewDelegate;
import com.zhonghua.mizi.model.UserFriendModel;
import com.zhonghua.mizi.model.UserModel;
import com.zhonghua.mizi.scm.message.IPhoneBookScm;
import com.zhonghua.mizi.scm.message.IUserCheckScm;
import com.zhonghua.mizi.scm.message.PhoneBookScm;
import com.zhonghua.mizi.scm.message.UserCheckScm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CloseTitle
public class PhoneBookActivity extends FragmentActivityPresenter<PhoneBookViewDelegate> {

    private EaseContactListFragment contactListFragment;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected Class<PhoneBookViewDelegate> getDelegateClass() {
        return PhoneBookViewDelegate.class;
    }

    IPhoneBookScm phonebookSrc;
    IUserCheckScm userCheckScm;
    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        phonebookSrc = new PhoneBookScm();
        userCheckScm = new UserCheckScm();
        contactListFragment = new EaseContactListFragment();
        contactListFragment.setContactListItemClickListener(new EaseContactListFragment.EaseContactListItemClickListener() {

            @Override
            public void onListItemClicked(EaseUser user) {
                startActivity(new Intent(PhoneBookActivity.this, ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, user.getUsername()));
            }
        });
        getFriend();
        getSupportFragmentManager().beginTransaction().replace(R.id.phonebook_fragment, contactListFragment)
                .commit();

    }


    @Override
    public void onClick(View view) {

    }


    /**
     * 获取盆友
     * @return
     */
    private void getFriend(){
        UserFriendModel userFriendModel = new UserFriendModel();
        UserModel userModel = AppHper.getAppUser();
        if(userModel!=null){
            userFriendModel.setUserid(userModel.getId());
            userFriendModel.setState(1);
            userCheckScm.getFriendList(userFriendModel, new INetListener() {
                @Override
                public void before() {
                    viewDelegate.loadDialogDismiss();
                }

                @Override
                public void success(Object model) {
                    if(model!=null){
                        List<UserFriendModel> data = (List<UserFriendModel>) model;
                        Map<String, EaseUser> contacts = new HashMap<String, EaseUser>();
                        for(int i = 0; i < data.size(); i++){
                            UserModel user = data.get(i).getUserModel();
                            if(user!=null){
                                EaseUser esuser = new EaseUser(user.getUsername());
                                esuser.setAvatar(user.getUserimage());
                                contacts.put(user.getLoginname(), esuser);
                            }
                        }
                        contactListFragment.setContactsMap(contacts);
                        contactListFragment.refresh();
                    }
                    viewDelegate.loadDialogDismiss();

                }

                @Override
                public void failed() {
                    viewDelegate.loadDialogDismiss();
                }

                @Override
                public void errMsg(String msg) {
                    viewDelegate.loadDialogDismiss();
                    viewDelegate.toast(msg,Toast.LENGTH_SHORT);
                }
            });


        }else{
            viewDelegate.toast("该功能仅在用户登录条件下使用", Toast.LENGTH_SHORT);
            finish();
        }
    }
}