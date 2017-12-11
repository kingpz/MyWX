package com.example.hasee.mywx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import Bean.UserBean;
import Grpc_Client.Client;
import Grpc_Client.Usergreet;

public class AddFriendsActivity extends AppCompatActivity {

    private EditText edt_add;
    private Button btn_search;
    private TextView tv_friend;
    private Button btn_add;
    //private UserBean userBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends);
        edt_add = (EditText)findViewById(R.id.fri_id);
        btn_search = (Button)findViewById(R.id.search);
        tv_friend = (TextView)findViewById(R.id.tv_friend);
        btn_add = (Button)findViewById(R.id.btn_add);
        //final Client client=Client.getClient();
        //final int id =  Integer.parseInt(edt_add.getText().toString());
        //final UserBean user = (UserBean) getIntent().getSerializableExtra("user");
        //final UserBean friend = Usergreet.searchgreet(id,client.getGreeterBlockingStub());

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Client client= Client.getClient();
                final int id =  Integer.parseInt(edt_add.getText().toString());
                final UserBean user = (UserBean) getIntent().getSerializableExtra("user");
                //System.out.println(user.getU_ID());
                //final UserBean friend = Usergreet.searchgreet(id,client.getGreeterBlockingStub());
                if (!"".equals(edt_add.getText().toString()) )
                {
                    //Client client=Client.getClient();
                   // List<UserBean> friends= Usergreet.querryFriend(user.getU_ID(),client.getGreeterBlockingStub());
                    Log.i("4444444444444444444","ssssssssssssssssssssssssss");
                    UserBean userBean= Usergreet.querryFriend(user.getU_ID(),id,client.getGreeterBlockingStub());
                    //String fri_name = userBean.getU_NickName();
                    System.out.println(userBean.getU_ID());

                    if(userBean == null){
                        new AlertDialog.Builder(AddFriendsActivity.this)
                                .setIcon(getResources().getDrawable(R.drawable.anbei))
                                .setTitle("error")
                                .setMessage("未添加该好友")
                                .create().show();
                    }
                    Intent intent = new Intent();
                    intent.setClass(AddFriendsActivity.this, MainActivity.class);
                    intent.putExtra("user", user);
                    intent.putExtra("friend", userBean);
                    startActivity(intent);
                    /*new AlertDialog.Builder(AddFriendsActivity.this)
                            .setIcon(getResources().getDrawable(R.drawable.anbei))
                            .setTitle("添加好友成功!")
                            .setMessage("您添加好友"+userBean.getU_ID()+"成功!")
                            .create().show();
                            */
                        //startActivity(intent);

                }
                else
                {
                    new AlertDialog.Builder(AddFriendsActivity.this)
                            .setIcon(getResources().getDrawable(R.drawable.debug))
                            .setTitle("错误")
                            .setMessage("请输入id！")
                            .create().show();
                }

            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Client client= Client.getClient();
                final int id =  Integer.parseInt(edt_add.getText().toString());
                final UserBean user = (UserBean) getIntent().getSerializableExtra("user");
                //System.out.println(user.getU_ID());
                //final UserBean friend = Usergreet.searchgreet(id,client.getGreeterBlockingStub());
                if (!"".equals(edt_add.getText().toString()) )
                {
                    //Client client=Client.getClient();
                    // List<UserBean> friends= Usergreet.querryFriend(user.getU_ID(),client.getGreeterBlockingStub());
                    UserBean userBean= Usergreet.addFriend(user.getU_ID(),id,client.getGreeterBlockingStub());
                    //String fri_name = userBean.getU_NickName();
                    //tv_friend.append(fri_name);
                    Intent intent = new Intent();
                    intent.setClass(AddFriendsActivity.this,MainActivity.class);
                    intent.putExtra("user",user);
                    intent.putExtra("friend",userBean);
                    new AlertDialog.Builder(AddFriendsActivity.this)
                            .setIcon(getResources().getDrawable(R.drawable.anbei))
                            .setTitle("添加好友成功!")
                            .setMessage("您添加好友"+userBean.getU_ID()+"成功!")
                            .create().show();
                    startActivity(intent);
                }
                else
                {
                    new AlertDialog.Builder(AddFriendsActivity.this)
                            .setIcon(getResources().getDrawable(R.drawable.debug))
                            .setTitle("错误")
                            .setMessage("该微信帐号不存在！")
                            .create().show();
                }

            }
        });

    }

}
