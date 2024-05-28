package tradearea.warehouse;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import tradearea.Warehouse;
import tradearea.WarehouseServiceGrpc;

public class WarehouseClient {
    private final ManagedChannel channel;
    private final WarehouseServiceGrpc.WarehouseServiceBlockingStub stub;

    public WarehouseClient(String host, int port) {
        this.channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        this.stub = WarehouseServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, java.util.concurrent.TimeUnit.SECONDS);
    }

    public void greet(String name) {
        Warehouse.WarehouseRequest request = Warehouse.WarehouseRequest.newBuilder().setWarehouseID("001").build();
        Warehouse.WarehouseData response = stub.getWarehouseData(request);

        System.out.println("Response from Warehouse gRPC server:");
        System.out.println("Warehouse ID: " + response.getWarehouseID());
        System.out.println("Warehouse Name: " + response.getWarehouseName());
        System.out.println("Warehouse Country: " + response.getWarehouseCountry());
        System.out.println("Warehouse City: " + response.getWarehouseCity());
        System.out.println("Warehouse Address: " + response.getAddress());
        System.out.println("Timestamp: " + response.getTimestamp());
        System.out.println("Product 1: " + response.getProductData(0));
    }

    public static void main(String[] args) throws Exception {
        WarehouseClient client = new WarehouseClient("localhost", 50051);
        try {
            String user = "Warehouse";
            client.greet(user);
        } finally {
            client.shutdown();
        }
    }
}
