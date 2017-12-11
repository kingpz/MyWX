package Grpc_Client;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Bean.UserBean;
import io.grpc.examples.helloworld.AddReply;
import io.grpc.examples.helloworld.AddRequest;
import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.LoginReply;
import io.grpc.examples.helloworld.LoginRequest;
import io.grpc.examples.helloworld.QuerryReply;
import io.grpc.examples.helloworld.QuerryRequest;
import io.grpc.examples.helloworld.RegisterReply;

/**
 * Created by hasee on 2017/11/9.
 */

public class Usergreet {
    /**
     * 登陆功能 hello to server.
     */
    public static UserBean logingreet(String name, String password, GreeterGrpc.GreeterBlockingStub blockingStub) {
        LoginRequest.Builder builder = LoginRequest.newBuilder();
        builder.setName(name);
        builder.setPassword(password);
        LoginRequest request = builder.build();
        LoginReply response;
        try {
            response = blockingStub.loginVerification(request);
            if (response.getMessage().equals("ok")) {
                UserBean userBean = new UserBean();
                userBean.setU_ID(response.getUID());
                userBean.setU_LoginID(response.getULoginID());
                userBean.setU_NickName(response.getUNickName());
                userBean.setU_HeadPortrait(response.getUHeadPortrait());
                return userBean;
            }
            else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static UserBean addFriend(int u_id,int f_id, GreeterGrpc.GreeterBlockingStub blockingStub) {
        AddRequest.Builder builder = AddRequest.newBuilder();
        //builder.setName(name);
        builder.setUId(u_id);
        builder.setFId(f_id);
        AddRequest request = builder.build();
        AddReply response;
        try {
            response = blockingStub.addFriends(request);
            Log.i("tag",response.getMessage());
            if (response.getMessage().equals("ok")) {
                UserBean userBean = new UserBean();
                userBean.setU_ID(response.getUID());
                userBean.setU_LoginID(response.getULoginID());
                userBean.setU_NickName(response.getUNickName());
                userBean.setU_HeadPortrait(response.getUHeadPortrait());

                return userBean;
            }
            else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static UserBean querryFriend(int u_id,int f_id, GreeterGrpc.GreeterBlockingStub blockingStub) {
        //List<UserBean> friends = new ArrayList<UserBean>();
        UserBean friend = new UserBean();
        QuerryRequest.Builder builder = QuerryRequest.newBuilder();
        //builder.setName(name);
        builder.setUId(u_id);
        builder.setFId(f_id);
        QuerryRequest request = builder.build();
        QuerryReply response;
        try {
            response = blockingStub.querryFriend(request);
            if(response.getMessage().equals("no")){
                return null;
            }
            if(response.getMessage().equals("ok")){
                //response.getSerializedSize();
                friend.setU_ID(response.getUID());
                friend.setU_LoginID(response.getULoginID());
                friend.setU_NickName((response.getUNickName()));
                friend.setU_HeadPortrait(response.getUHeadPortrait());
                //friends.add(friend);
            }
            Log.i("qqqqqqqqqqqqqqqq",response.getMessage());
            Log.i("sssssssssss",friend.toString());
            return friend;
        } catch (Exception e) {
            e.printStackTrace();
            return friend;
        }
    }
    public static boolean Registergreet(String name, String password, GreeterGrpc.GreeterBlockingStub blockingStub)
    {
        LoginRequest.Builder builder = LoginRequest.newBuilder();
        builder.setName(name);
        builder.setPassword(password);
        LoginRequest request = builder.build();
        RegisterReply response;
        try {
            response = blockingStub.registerVerification(request);
            if(response.getMessage().equals("ok"))
            {
                return true;
            }
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
