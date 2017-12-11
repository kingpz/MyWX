package com.example.hasee.mywx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Bean.UserBean;
import Grpc_Client.Client;
import Grpc_Client.Usergreet;

/**
 * Created by Administrator on 2017/11/9.
 */

public class LoginActivity extends AppCompatActivity {

    private UserBean userBean;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        final EditText userEt = (EditText) findViewById(R.id.userEt);
        final EditText pswEt = (EditText) findViewById(R.id.pswEt);

        Button login = (Button) findViewById(R.id.login_bt);
        Button forget = (Button) findViewById(R.id.forget_btn);
        Button register = (Button) findViewById(R.id.register_bt);

        login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String name=userEt.getText().toString();
                String password=pswEt.getText().toString();
                if (!"".equals(userEt.getText().toString()) && !"".equals(pswEt.getText().toString()))
                {
                    Client client=Client.getClient();
                    userBean=Usergreet.logingreet(name,password,client.getGreeterBlockingStub());
                    if(userBean!=null)
                    {
                        Intent intent = new Intent();
                        intent.setClass(LoginActivity.this,MainActivity.class);
                        intent.putExtra("user",userBean);
                        finish();
                        startActivity(intent);
                    }
                    else
                    {
                        new AlertDialog.Builder(LoginActivity.this)
                                .setIcon(getResources().getDrawable(R.drawable.debug))
                                .setTitle("登录失败")
                                .setMessage("微信帐号或者密码不正确，\n请检查后重新输入！")
                                .create().show();
                    }
                }
                else
                {
                    new AlertDialog.Builder(LoginActivity.this)
                            .setIcon(getResources().getDrawable(R.drawable.debug))
                            .setTitle("登录错误")
                            .setMessage("微信帐号或者密码不能为空，\n请输入后再登录！")
                            .create().show();
                }

            }
        });

        register.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();

            }
        });


    }
}
