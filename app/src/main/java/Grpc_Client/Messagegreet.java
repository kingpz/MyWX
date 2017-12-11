package Grpc_Client;

import java.util.ArrayList;
import java.util.List;

import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.MessageReply;
import io.grpc.examples.helloworld.MessageRequest;
import io.grpc.examples.helloworld.Messages;
import io.grpc.stub.StreamObserver;
import android.os.Handler;
import android.os.Message;

/**
 * Created by hasee on 2017/11/23.
 */

public class Messagegreet {
    public static List Send_loginmess(int id,String type,GreeterGrpc.GreeterBlockingStub blockingStub){
        MessageRequest.Builder builder=MessageRequest.newBuilder();
        builder.setType(type);
        builder.setMFromUserID(id);
        MessageRequest request=builder.build();
        MessageReply response;
        try {
            response = blockingStub.messageVerification(request);
            if (!response.getType().equals("no"))
            {
                List<Messages> mes= response.getMesList();
                ArrayList<Bean.Messages> meslist=new ArrayList<>();
                for (int i=0;i<mes.size();i++)
                {
                    Bean.Messages messages=new Bean.Messages();
                    messages.setM_FromUserID(mes.get(i).getMFromUserID());
                    messages.setM_ToUserID(mes.get(i).getMFromUserID());
                    messages.setM_PostMessages(mes.get(i).getMPostMessages());
                    meslist.add(messages);
                }
                return meslist;
            }
            else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean Send_mess(Bean.Messages messages, String type, GreeterGrpc.GreeterBlockingStub blockingStub){
        MessageRequest.Builder builder=MessageRequest.newBuilder();
        builder.setType(type);
        builder.setMFromUserID(messages.getM_FromUserID());
        builder.setMToUserID(messages.getM_ToUserID());
        builder.setMPostMessages(messages.getM_PostMessages());
        MessageRequest request=builder.build();
        MessageReply response;
        try {
            response = blockingStub.messageVerification(request);
                return response.getType().equals("ok");
        }
        catch(Exception e)
        {
            return false;
        }
    }

    public static StreamObserver<MessageRequest> routeChat(GreeterGrpc.GreeterStub stub, final Handler handler) {
        System.out.println("start routeChat");
        //写入监听
        StreamObserver<MessageRequest> requestObserver =
                //写回监听
                stub.chatMessage(new StreamObserver<MessageReply>() {
                    @Override
                    public void onNext(MessageReply value) {
                        Message message=handler.obtainMessage();
                        List<Messages> mes= value.getMesList();
                        ArrayList<Bean.Messages> meslist=new ArrayList<>();
                        for (int i=0;i<mes.size();i++)
                        {
                            Bean.Messages messages=new Bean.Messages();
                            messages.setM_FromUserID(mes.get(i).getMFromUserID());
                            messages.setM_ToUserID(mes.get(i).getMFromUserID());
                            messages.setM_PostMessages(mes.get(i).getMPostMessages());
                            meslist.add(messages);
                        }
                        message.obj=meslist;
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onCompleted() {

                    }
                });
        return requestObserver;
    }

}
