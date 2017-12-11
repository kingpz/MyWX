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
import io.grpc.examples.helloworld.MessageRequest;
import io.grpc.stub.StreamObserver;

/**
 * Created by hasee on 2017/11/25.
 */

public class TextDoubleGrpcActivity extends AppCompatActivity implements View.OnClickListener {
    ListView listView;
    Button button;
    EditText text;
    Context mContext;
    Messages messages;
    Message_list_Adapter adapter;
    Client client=Client.getClient();

    Handler handler=new Handler(){
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
    StreamObserver<MessageRequest>  requestObserver=Messagegreet.routeChat(client.getGreeterStub(),handler);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_messages);

        mContext = this;
        listView = (ListView) findViewById(R.id.msg_list_view);
        text = (EditText) findViewById(R.id.input_text);
        button = (Button) findViewById(R.id.send);
        button.setOnClickListener(this);


        messages = (Messages) getIntent().getSerializableExtra("message");
        MessageRequest.Builder builder=MessageRequest.newBuilder();
        builder.setType("1");
        builder.setMFromUserID(messages.getM_ToUserID());
        MessageRequest request=builder.build();
        requestObserver.onNext(request);
        ArrayList<Messages> meslist = new ArrayList<Messages>();
        meslist.add(messages);

        adapter = new Message_list_Adapter((ArrayList<Messages>) meslist, mContext);
        listView.setAdapter(adapter);
        ischat(meslist);
    }

    public boolean ischat(ArrayList<Messages> meslist){
        if (meslist != null){
            return true;
        }
        return false;
    }
    @Override
    public void onClick(View v) {
        if(!text.getText().toString().equals("")) {
            Messages messages1 = new Messages();
            messages1.setM_FromUserID(messages.getM_ToUserID());
            messages1.setM_ToUserID(messages.getM_FromUserID());
            messages1.setM_PostMessages(text.getText().toString());
            messages1.setM_MessagesType(1);
            MessageRequest.Builder builder=MessageRequest.newBuilder();
            builder.setMMessagesType(1);
            builder.setType("3");
            builder.setMFromUserID(messages.getM_ToUserID());
            builder.setMToUserID(messages.getM_FromUserID());
            builder.setMPostMessages(text.getText().toString());
            MessageRequest request=builder.build();
            requestObserver.onNext(request);
            adapter.add(messages1);
            text.setText("");
//            if(Messagegreet.Send_mess(messages1,"3",client.getGreeterBlockingStub())) {
//                adapter.add(messages1);
//                text.setText("");
//            }
        }
    }
}
