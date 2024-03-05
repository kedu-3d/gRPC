import helloworld.GreeterGrpc;
import helloworld.Hello;
import io.grpc.stub.StreamObserver;

// Implementation of the gRPC service on the server-side.
class HelloWorldServiceImpl extends GreeterGrpc.GreeterImplBase {
    @Override
    public void sayHello(Hello.HelloRequest req, StreamObserver<Hello.HelloReply> responseObserver) {
        // Generate a greeting message for the original method
        Hello.HelloReply reply = Hello.HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();

        // Send the reply back to the client.
        responseObserver.onNext(reply);

        // Indicate that no further messages will be sent to the client.
        responseObserver.onCompleted();
    }

    @Override
    public void sayHelloAgain(Hello.HelloRequest req, StreamObserver<Hello.HelloReply> responseObserver) {
        // Generate another greeting message for the new method.
        Hello.HelloReply reply = Hello.HelloReply.newBuilder().setMessage("Hello again " + req.getName()).build();

        // Send the reply back to the client.
        responseObserver.onNext(reply);

        // Indicate that no further messages will be sent to the client.
        responseObserver.onCompleted();
    }
}
