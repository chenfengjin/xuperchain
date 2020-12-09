package com.baidu.xuper.contractpb;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * service provided by chain code, called by xchain
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.29.0)",
    comments = "Source: contract_service.proto")
public final class NativeCodeGrpc {

  private NativeCodeGrpc() {}

  public static final String SERVICE_NAME = "xchain.contract.svc.NativeCode";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.NativeCallRequest,
      com.baidu.xuper.contractpb.Contract.NativeCallResponse> getCallMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Call",
      requestType = com.baidu.xuper.contractpb.Contract.NativeCallRequest.class,
      responseType = com.baidu.xuper.contractpb.Contract.NativeCallResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.NativeCallRequest,
      com.baidu.xuper.contractpb.Contract.NativeCallResponse> getCallMethod() {
    io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.NativeCallRequest, com.baidu.xuper.contractpb.Contract.NativeCallResponse> getCallMethod;
    if ((getCallMethod = NativeCodeGrpc.getCallMethod) == null) {
      synchronized (NativeCodeGrpc.class) {
        if ((getCallMethod = NativeCodeGrpc.getCallMethod) == null) {
          NativeCodeGrpc.getCallMethod = getCallMethod =
              io.grpc.MethodDescriptor.<com.baidu.xuper.contractpb.Contract.NativeCallRequest, com.baidu.xuper.contractpb.Contract.NativeCallResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Call"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.NativeCallRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.NativeCallResponse.getDefaultInstance()))
              .setSchemaDescriptor(new NativeCodeMethodDescriptorSupplier("Call"))
              .build();
        }
      }
    }
    return getCallMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.PingRequest,
      com.baidu.xuper.contractpb.Contract.PingResponse> getPingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Ping",
      requestType = com.baidu.xuper.contractpb.Contract.PingRequest.class,
      responseType = com.baidu.xuper.contractpb.Contract.PingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.PingRequest,
      com.baidu.xuper.contractpb.Contract.PingResponse> getPingMethod() {
    io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.PingRequest, com.baidu.xuper.contractpb.Contract.PingResponse> getPingMethod;
    if ((getPingMethod = NativeCodeGrpc.getPingMethod) == null) {
      synchronized (NativeCodeGrpc.class) {
        if ((getPingMethod = NativeCodeGrpc.getPingMethod) == null) {
          NativeCodeGrpc.getPingMethod = getPingMethod =
              io.grpc.MethodDescriptor.<com.baidu.xuper.contractpb.Contract.PingRequest, com.baidu.xuper.contractpb.Contract.PingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Ping"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.PingRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.PingResponse.getDefaultInstance()))
              .setSchemaDescriptor(new NativeCodeMethodDescriptorSupplier("Ping"))
              .build();
        }
      }
    }
    return getPingMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static NativeCodeStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NativeCodeStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NativeCodeStub>() {
        @java.lang.Override
        public NativeCodeStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NativeCodeStub(channel, callOptions);
        }
      };
    return NativeCodeStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static NativeCodeBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NativeCodeBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NativeCodeBlockingStub>() {
        @java.lang.Override
        public NativeCodeBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NativeCodeBlockingStub(channel, callOptions);
        }
      };
    return NativeCodeBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static NativeCodeFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NativeCodeFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NativeCodeFutureStub>() {
        @java.lang.Override
        public NativeCodeFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NativeCodeFutureStub(channel, callOptions);
        }
      };
    return NativeCodeFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * service provided by chain code, called by xchain
   * </pre>
   */
  public static abstract class NativeCodeImplBase implements io.grpc.BindableService {

    /**
     */
    public void call(com.baidu.xuper.contractpb.Contract.NativeCallRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.NativeCallResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCallMethod(), responseObserver);
    }

    /**
     */
    public void ping(com.baidu.xuper.contractpb.Contract.PingRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.PingResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPingMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCallMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.baidu.xuper.contractpb.Contract.NativeCallRequest,
                com.baidu.xuper.contractpb.Contract.NativeCallResponse>(
                  this, METHODID_CALL)))
          .addMethod(
            getPingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.baidu.xuper.contractpb.Contract.PingRequest,
                com.baidu.xuper.contractpb.Contract.PingResponse>(
                  this, METHODID_PING)))
          .build();
    }
  }

  /**
   * <pre>
   * service provided by chain code, called by xchain
   * </pre>
   */
  public static final class NativeCodeStub extends io.grpc.stub.AbstractAsyncStub<NativeCodeStub> {
    private NativeCodeStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NativeCodeStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NativeCodeStub(channel, callOptions);
    }

    /**
     */
    public void call(com.baidu.xuper.contractpb.Contract.NativeCallRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.NativeCallResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCallMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void ping(com.baidu.xuper.contractpb.Contract.PingRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.PingResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * service provided by chain code, called by xchain
   * </pre>
   */
  public static final class NativeCodeBlockingStub extends io.grpc.stub.AbstractBlockingStub<NativeCodeBlockingStub> {
    private NativeCodeBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NativeCodeBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NativeCodeBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.baidu.xuper.contractpb.Contract.NativeCallResponse call(com.baidu.xuper.contractpb.Contract.NativeCallRequest request) {
      return blockingUnaryCall(
          getChannel(), getCallMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.baidu.xuper.contractpb.Contract.PingResponse ping(com.baidu.xuper.contractpb.Contract.PingRequest request) {
      return blockingUnaryCall(
          getChannel(), getPingMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * service provided by chain code, called by xchain
   * </pre>
   */
  public static final class NativeCodeFutureStub extends io.grpc.stub.AbstractFutureStub<NativeCodeFutureStub> {
    private NativeCodeFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NativeCodeFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NativeCodeFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.contractpb.Contract.NativeCallResponse> call(
        com.baidu.xuper.contractpb.Contract.NativeCallRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCallMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.contractpb.Contract.PingResponse> ping(
        com.baidu.xuper.contractpb.Contract.PingRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CALL = 0;
  private static final int METHODID_PING = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final NativeCodeImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(NativeCodeImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CALL:
          serviceImpl.call((com.baidu.xuper.contractpb.Contract.NativeCallRequest) request,
              (io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.NativeCallResponse>) responseObserver);
          break;
        case METHODID_PING:
          serviceImpl.ping((com.baidu.xuper.contractpb.Contract.PingRequest) request,
              (io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.PingResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class NativeCodeBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    NativeCodeBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.baidu.xuper.contractpb.ContractService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("NativeCode");
    }
  }

  private static final class NativeCodeFileDescriptorSupplier
      extends NativeCodeBaseDescriptorSupplier {
    NativeCodeFileDescriptorSupplier() {}
  }

  private static final class NativeCodeMethodDescriptorSupplier
      extends NativeCodeBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    NativeCodeMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (NativeCodeGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new NativeCodeFileDescriptorSupplier())
              .addMethod(getCallMethod())
              .addMethod(getPingMethod())
              .build();
        }
      }
    }
    return result;
  }
}
