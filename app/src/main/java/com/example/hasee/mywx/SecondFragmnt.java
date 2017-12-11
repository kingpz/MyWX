package com.example.hasee.mywx;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import Bean.UserBean;


/**
 * Created by Administrator on 2017\10\14 0014.
 */

public class SecondFragmnt extends Fragment {
    private ListView friends_list;
    private List<UserBean>  friends = new ArrayList<>();

    Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //final UserBean user = (UserBean) getIntent().getSerializableExtra("user");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment,container,false);

        //final ImageView img_second = (ImageView)view.findViewById(R.id.);
       // img_second.setImageResource(R.drawable.caiyingwen);
       // FriendsAdapter adapter = new FriendsAdapter(SecondFragmnt.this,R.layout.friend,friends_list);
        //System.out.println(friend);
            //Client client=Client.getClient();
            //userBean= Usergreet.searchgreet(id,client.getGreeterBlockingStub());//
            // String fri_name = userBean.getU_NickName();
            //tv_friend.append(userBean.toString());

        return view;
    }

}
