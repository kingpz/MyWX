package com.example.hasee.mywx;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import Adapter.Message_list_Adapter;
import Bean.Messages;
import Bean.UserBean;
import Grpc_Client.Client;
import Grpc_Client.Messagegreet;
import Grpc_Client.Usergreet;

import static Grpc_Client.Usergreet.querryFriend;

/**
 * Created by Administrator on 2017\10\14 0014.
 */

public class FirstFragment extends Fragment {
    private ContentAdapter adapter;
    private List<View> views;
    Context mContext;
    final ArrayList<Messages> list=new ArrayList<>();
    Client client=Client.getClient();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragmrnt,container,false);
        mContext = getContext();
        ListView listView=(ListView)view.findViewById(R.id.message_list);
        Messages messages=new Messages();
        messages.setM_PostMessages("           ");
        UserBean user=(UserBean) getActivity().getIntent().getSerializableExtra("user");
        UserBean friend = (UserBean)getActivity().getIntent().getSerializableExtra("friend");
        //TextDoubleGrpcActivity ta = new TextDoubleGrpcActivity();

        // final UserBean user = (UserBean) getIntent().getSerializableExtra("user");
        //final Client client= Client.getClient();
        //List<UserBean> friends= new ArrayList<UserBean>();
        //friends= Usergreet.querryFriend(user.getU_ID(),client.getGreeterBlockingStub());
        //Log.i("friends", friends.toString());
        //System.out.println(user);
        /*int id=0;
        if(user.getU_ID()==user.getU_ID())
        {
            id=4;
        }
        else if(user.getU_ID()==4)
        {
            id=1;
        }*/
        int id = 1;
        if (friend != null) {
            id = friend.getU_ID();
            messages.setM_FromUserID(id);
            messages.setM_ToUserID(user.getU_ID());
            list.add(messages);
        }
        //messages.setM_FromUserID(id);
        //messages.setM_ToUserID(user.getU_ID());
        //list.add(messages);
        Message_list_Adapter adapter=new Message_list_Adapter(list,mContext);
        listView.setAdapter(adapter);
        //setListAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0) {

                    Messages info = list.get(i);
                    Intent intent = new Intent(getActivity(), SendMessagesActivity.class);
                    intent.putExtra("message", info);
                    startActivity(intent);
                }
                else if (i==1)
                {
                    Messages info = list.get(i);
                    Intent intent = new Intent(getActivity(), TextDoubleGrpcActivity.class);
                    intent.putExtra("message", info);
                    startActivity(intent);
                }
                /*for (int j=0;j<list.size();j++){
                    Messages info = list.get(i);
                    Intent intent = new Intent(getActivity(), TextDoubleGrpcActivity.class);
                    intent.putExtra("message", info);
                    startActivity(intent);
                }*/

            }
        });

        // ViewPager适配器ContentAdapter

        //final ImageView img_first = (ImageView)view.findViewById(R.id.imageView1);
        //img_first.setImageResource(R.drawable.anbei);

        return view;
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    public void onPageSelected(int position) {

    }
    public void onPageScrollStateChanged(int state) {

    }
    public void onClick(View v) {

    }
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(position==0) {
            Messages info = list.get(position);
            Intent intent = new Intent(getActivity(), SendMessagesActivity.class);
            intent.putExtra("message", info);
            startActivity(intent);
        }
        else if (position==1)
        {
            Messages info = list.get(position);
            Intent intent = new Intent(getActivity(), TextDoubleGrpcActivity.class);
            intent.putExtra("message", info);
            startActivity(intent);
        }

    }
}
