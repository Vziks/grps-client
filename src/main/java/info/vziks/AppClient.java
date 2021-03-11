package info.vziks;

import info.vziks.grpc.AppServiceGrpc;
import info.vziks.grpc.AppServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * Class AppClient
 * Project grpc-demo-client
 *
 * @author Anton Prokhorov <vziks@live.ru>
 */
public class AppClient {

    public static void main(String[] args) {

        String[] arrNames = new String[]{
                "Liam",
                "Charlotte",
                "Ava",
                "Ethan",
                "Mason",
                "Amelia"
        };
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080").usePlaintext().build();

        for (String name : arrNames) {
            AppServiceGrpc.AppServiceBlockingStub stub = AppServiceGrpc.newBlockingStub(channel);
            AppServiceOuterClass.AppRequest request = AppServiceOuterClass.AppRequest.newBuilder().setName(name).build();
            AppServiceOuterClass.AppResponse response = stub.hello(request);
            System.out.println(response);
        }

        channel.shutdownNow();
    }
}
