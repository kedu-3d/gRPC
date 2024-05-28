package tradearea.warehouse;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class WarehouseServer {
    private final int port;
    private final Server server;

    public WarehouseServer(int port) {
        this.port = port;
        this.server = ServerBuilder.forPort(port)
                .addService(new WarehouseServiceImpl())
                .build();
    }

    private void start() throws IOException {
        server.start();
        System.out.println("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            WarehouseServer.this.stop();
            System.err.println("*** server shut down");
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        WarehouseServer server = new WarehouseServer(50051);
        server.start();
        server.blockUntilShutdown();
    }
}
