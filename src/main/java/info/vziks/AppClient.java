package info.vziks;

import info.vziks.grpc.AppRequest;
import info.vziks.grpc.AppResponse;
import info.vziks.grpc.AppServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Class AppClient
 * Project grpc-demo-client
 *
 * @author Anton Prokhorov <vziks@live.ru>
 */
public class AppClient {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("first");
        list.add("second");


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
            AppRequest request = AppRequest.newBuilder().setName(name).addAllArr(list).build();
            Iterator<AppResponse> response = stub.hello(request);

            while (response.hasNext()) {
                System.out.println(response.next());
            }
        }
        channel.shutdownNow();
    }


}
