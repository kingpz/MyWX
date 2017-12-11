package Grpc_Client;

import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.LoginReply;
import io.grpc.examples.helloworld.LoginRequest;

/**
 * Created by hasee on 2017/11/2.
 */

public class Client {

    private final String host="192.168.43.120";
    private final int  port=50053;

    private final ManagedChannel channel;//grpc信道,需要指定端口和地址
    private final GreeterGrpc.GreeterBlockingStub blockingStub;;//阻塞/同步存根
    private final GreeterGrpc.GreeterStub sutb;//非阻塞,异步存根

    private static Client client=new Client();

    /** Construct client connecting to HelloWorld server at {@code host:port}. */
    private Client() {

        channel = ManagedChannelBuilder.forAddress(host,port)
                .usePlaintext(true)
                .build();
        blockingStub = GreeterGrpc.newBlockingStub(channel);
        sutb=GreeterGrpc.newStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public  GreeterGrpc.GreeterBlockingStub getGreeterBlockingStub(){
        return blockingStub;
    }

    public  GreeterGrpc.GreeterStub getGreeterStub(){
        return sutb;
    }

    public static Client getClient(){
        return client;
    }

}
