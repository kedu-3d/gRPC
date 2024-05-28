package tradearea.warehouse;

import io.grpc.stub.StreamObserver;
import tradearea.Warehouse.WarehouseRequest;
import tradearea.Warehouse.WarehouseData;
import tradearea.WarehouseServiceGrpc.WarehouseServiceImplBase;

public class WarehouseServiceImpl extends WarehouseServiceImplBase {

    @Override
    public void getWarehouseData(WarehouseRequest request, StreamObserver<WarehouseData> responseObserver) {

        WarehouseData data = WarehouseData.newBuilder()
                .setWarehouseID(request.getWarehouseID())
                .setWarehouseName("Kevins Lagerhaus")
                .setWarehouseCity("Wien")
                .setWarehouseCountry("Oestereich")
                .setAddress("Wexstrasse 1234")
                .setTimestamp("2024")
                .build();

        responseObserver.onNext(data);
        responseObserver.onCompleted();
    }
}
