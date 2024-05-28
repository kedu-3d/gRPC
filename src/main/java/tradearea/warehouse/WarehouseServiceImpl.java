package tradearea.warehouse;

import io.grpc.stub.StreamObserver;
import tradearea.Warehouse.WarehouseRequest;
import tradearea.Warehouse.WarehouseData;
import tradearea.WarehouseServiceGrpc.WarehouseServiceImplBase;
import tradearea.Warehouse.ProductData;

public class WarehouseServiceImpl extends WarehouseServiceImplBase {

    @Override
    public void getWarehouseData(WarehouseRequest request, StreamObserver<WarehouseData> responseObserver) {

        ProductData value1 = ProductData.newBuilder()
                .setProductId("00-443175")
                .setProductCategory("Bio Orangensaft Sonne")
                .setProductName("Getraenk")
                .setProductAmount("2500")
                .setProductUnit("Packung 1L")
                .build();

        WarehouseData data = WarehouseData.newBuilder()
                .setWarehouseID(request.getWarehouseID())
                .setWarehouseName("Kevins Lagerhaus")
                .setWarehouseCity("Wien")
                .setWarehouseCountry("Oestereich")
                .setAddress("Wexstrasse 1234")
                .setTimestamp("2024")
                .addProductData(value1)
                .build();

        responseObserver.onNext(data);
        responseObserver.onCompleted();
    }
}
