package com.example.hasee.mywx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Bean.UserBean;
import Grpc_Client.Client;
import Grpc_Client.Usergreet;

/**
 * Created by Administrator on 2017/11/9.
 */

public class RegisterActivity extends AppCompatActivity {
    private UserBean userBean;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        final EditText userEt = (EditText) findViewById(R.id.userEt);
        final EditText pswEt = (EditText) findViewById(R.id.pswEt);
        final EditText psw2Et = (EditText) findViewById(R.id.pswEt);

        Button register = (Button) findViewById(R.id.register_bt);
        Button exit = (Button) findViewById(R.id.exit);

        register.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String name=userEt.getText().toString();
                String psw=pswEt.getText().toString();
                if((pswEt.getText().toString()).equals(psw2Et.getText().toString())==false){

                    new AlertDialog.Builder(RegisterActivity.this)
                            .setIcon(getResources().getDrawable(R.drawable.debug))
                            .setTitle("注册错误")
                            .setMessage("二次输入不一致，\n请重新输入！")
                            .create().show();
                }
                else
                if (!"".equals(userEt.getText().toString()) && !"".equals(pswEt.getText().toString()))
                    {
                        Client client=Client.getClient();
                        boolean ft= Usergreet.Registergreet(name,psw,client.getGreeterBlockingStub());
                        if(ft)
                        {
                            Toast.makeText(getApplicationContext(), "注册成功！！！", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setClass(RegisterActivity.this,LoginActivity.class);
                            finish();
                            startActivity(intent);
                        }
                        else
                        {
                            new AlertDialog.Builder(RegisterActivity.this)
                                    .setIcon(getResources().getDrawable(R.drawable.debug))
                                    .setTitle("注册失败")
                                    .setMessage("账号已存在")
                                    .create().show();
                        }
                    }
                else{

                    new AlertDialog.Builder(RegisterActivity.this)
                            .setIcon(getResources().getDrawable(R.drawable.debug))
                            .setTitle("注册错误")
                            .setMessage("微信帐号或者密码不能为空，\n请输入完整！")
                            .create().show();
                }
            }
        });

        exit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }

        });

    }
}
