package com.example.hasee.mywx;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import Adapter.Message_list_Adapter;
import Bean.Messages;
import Grpc_Client.Client;
import Grpc_Client.Messagegreet;

/**
 * Created by hasee on 2017/11/23.
 */

public class SendMessagesActivity extends AppCompatActivity implements View.OnClickListener {
    ListView listView;
    Button button;
    EditText text;
    Context mContext;
    Messages messages;
    Message_list_Adapter adapter;
    Client client=Client.getClient();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_messages);

        mContext=this;
        listView=(ListView)findViewById(R.id.msg_list_view);
        text=(EditText)findViewById(R.id.input_text);
        button=(Button)findViewById(R.id.send);
        button.setOnClickListener(this);


        messages=(Messages) getIntent().getSerializableExtra("message");
        List meslist=Messagegreet.Send_loginmess(messages.getM_ToUserID(),"1",client.getGreeterBlockingStub());

        if (meslist==null) {
            meslist = new ArrayList<Messages>();
            meslist.add(messages);
        }
        adapter=new Message_list_Adapter((ArrayList<Messages>) meslist,mContext);
        listView.setAdapter(adapter);
        final Handler handler=new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                List meslist=(ArrayList)msg.obj;
                for (int i=0;i<meslist.size();i++)
                {
                    Messages messages=(Messages)meslist.get(i);
                    adapter.add(messages);
                }
            }
        };

        Thread t = new Thread(new Runnable(){
            public void run(){
                while(true){
                    try {
                        Thread.sleep(500);
                        Message message=handler.obtainMessage();
                        List meslist=Messagegreet.Send_loginmess(messages.getM_ToUserID(),"2",client.getGreeterBlockingStub());
                        if(meslist!=null)
                        {
                            message.obj=meslist;
                            handler.sendMessage(message);
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }});
        t.start();
    }
    @Override
    public void onClick(View v) {
        if(!text.getText().toString().equals("")) {
            Messages messages1 = new Messages();
            messages1.setM_FromUserID(messages.getM_ToUserID());
            messages1.setM_ToUserID(messages.getM_FromUserID());
            messages1.setM_PostMessages(text.getText().toString());
            messages1.setM_MessagesType(1);
            if(Messagegreet.Send_mess(messages1,"3",client.getGreeterBlockingStub())) {
                adapter.add(messages1);
                text.setText("");
            }
            }
    }
}
