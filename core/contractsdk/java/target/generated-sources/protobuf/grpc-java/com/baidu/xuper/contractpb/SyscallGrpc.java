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
 * xchain syscall service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.29.0)",
    comments = "Source: contract_service.proto")
public final class SyscallGrpc {

  private SyscallGrpc() {}

  public static final String SERVICE_NAME = "xchain.contract.svc.Syscall";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.PutRequest,
      com.baidu.xuper.contractpb.Contract.PutResponse> getPutObjectMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PutObject",
      requestType = com.baidu.xuper.contractpb.Contract.PutRequest.class,
      responseType = com.baidu.xuper.contractpb.Contract.PutResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.PutRequest,
      com.baidu.xuper.contractpb.Contract.PutResponse> getPutObjectMethod() {
    io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.PutRequest, com.baidu.xuper.contractpb.Contract.PutResponse> getPutObjectMethod;
    if ((getPutObjectMethod = SyscallGrpc.getPutObjectMethod) == null) {
      synchronized (SyscallGrpc.class) {
        if ((getPutObjectMethod = SyscallGrpc.getPutObjectMethod) == null) {
          SyscallGrpc.getPutObjectMethod = getPutObjectMethod =
              io.grpc.MethodDescriptor.<com.baidu.xuper.contractpb.Contract.PutRequest, com.baidu.xuper.contractpb.Contract.PutResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PutObject"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.PutRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.PutResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SyscallMethodDescriptorSupplier("PutObject"))
              .build();
        }
      }
    }
    return getPutObjectMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.GetRequest,
      com.baidu.xuper.contractpb.Contract.GetResponse> getGetObjectMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetObject",
      requestType = com.baidu.xuper.contractpb.Contract.GetRequest.class,
      responseType = com.baidu.xuper.contractpb.Contract.GetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.GetRequest,
      com.baidu.xuper.contractpb.Contract.GetResponse> getGetObjectMethod() {
    io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.GetRequest, com.baidu.xuper.contractpb.Contract.GetResponse> getGetObjectMethod;
    if ((getGetObjectMethod = SyscallGrpc.getGetObjectMethod) == null) {
      synchronized (SyscallGrpc.class) {
        if ((getGetObjectMethod = SyscallGrpc.getGetObjectMethod) == null) {
          SyscallGrpc.getGetObjectMethod = getGetObjectMethod =
              io.grpc.MethodDescriptor.<com.baidu.xuper.contractpb.Contract.GetRequest, com.baidu.xuper.contractpb.Contract.GetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetObject"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.GetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.GetResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SyscallMethodDescriptorSupplier("GetObject"))
              .build();
        }
      }
    }
    return getGetObjectMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.DeleteRequest,
      com.baidu.xuper.contractpb.Contract.DeleteResponse> getDeleteObjectMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteObject",
      requestType = com.baidu.xuper.contractpb.Contract.DeleteRequest.class,
      responseType = com.baidu.xuper.contractpb.Contract.DeleteResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.DeleteRequest,
      com.baidu.xuper.contractpb.Contract.DeleteResponse> getDeleteObjectMethod() {
    io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.DeleteRequest, com.baidu.xuper.contractpb.Contract.DeleteResponse> getDeleteObjectMethod;
    if ((getDeleteObjectMethod = SyscallGrpc.getDeleteObjectMethod) == null) {
      synchronized (SyscallGrpc.class) {
        if ((getDeleteObjectMethod = SyscallGrpc.getDeleteObjectMethod) == null) {
          SyscallGrpc.getDeleteObjectMethod = getDeleteObjectMethod =
              io.grpc.MethodDescriptor.<com.baidu.xuper.contractpb.Contract.DeleteRequest, com.baidu.xuper.contractpb.Contract.DeleteResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteObject"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.DeleteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.DeleteResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SyscallMethodDescriptorSupplier("DeleteObject"))
              .build();
        }
      }
    }
    return getDeleteObjectMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.IteratorRequest,
      com.baidu.xuper.contractpb.Contract.IteratorResponse> getNewIteratorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "NewIterator",
      requestType = com.baidu.xuper.contractpb.Contract.IteratorRequest.class,
      responseType = com.baidu.xuper.contractpb.Contract.IteratorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.IteratorRequest,
      com.baidu.xuper.contractpb.Contract.IteratorResponse> getNewIteratorMethod() {
    io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.IteratorRequest, com.baidu.xuper.contractpb.Contract.IteratorResponse> getNewIteratorMethod;
    if ((getNewIteratorMethod = SyscallGrpc.getNewIteratorMethod) == null) {
      synchronized (SyscallGrpc.class) {
        if ((getNewIteratorMethod = SyscallGrpc.getNewIteratorMethod) == null) {
          SyscallGrpc.getNewIteratorMethod = getNewIteratorMethod =
              io.grpc.MethodDescriptor.<com.baidu.xuper.contractpb.Contract.IteratorRequest, com.baidu.xuper.contractpb.Contract.IteratorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "NewIterator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.IteratorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.IteratorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SyscallMethodDescriptorSupplier("NewIterator"))
              .build();
        }
      }
    }
    return getNewIteratorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.QueryTxRequest,
      com.baidu.xuper.contractpb.Contract.QueryTxResponse> getQueryTxMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryTx",
      requestType = com.baidu.xuper.contractpb.Contract.QueryTxRequest.class,
      responseType = com.baidu.xuper.contractpb.Contract.QueryTxResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.QueryTxRequest,
      com.baidu.xuper.contractpb.Contract.QueryTxResponse> getQueryTxMethod() {
    io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.QueryTxRequest, com.baidu.xuper.contractpb.Contract.QueryTxResponse> getQueryTxMethod;
    if ((getQueryTxMethod = SyscallGrpc.getQueryTxMethod) == null) {
      synchronized (SyscallGrpc.class) {
        if ((getQueryTxMethod = SyscallGrpc.getQueryTxMethod) == null) {
          SyscallGrpc.getQueryTxMethod = getQueryTxMethod =
              io.grpc.MethodDescriptor.<com.baidu.xuper.contractpb.Contract.QueryTxRequest, com.baidu.xuper.contractpb.Contract.QueryTxResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryTx"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.QueryTxRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.QueryTxResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SyscallMethodDescriptorSupplier("QueryTx"))
              .build();
        }
      }
    }
    return getQueryTxMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.QueryBlockRequest,
      com.baidu.xuper.contractpb.Contract.QueryBlockResponse> getQueryBlockMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryBlock",
      requestType = com.baidu.xuper.contractpb.Contract.QueryBlockRequest.class,
      responseType = com.baidu.xuper.contractpb.Contract.QueryBlockResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.QueryBlockRequest,
      com.baidu.xuper.contractpb.Contract.QueryBlockResponse> getQueryBlockMethod() {
    io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.QueryBlockRequest, com.baidu.xuper.contractpb.Contract.QueryBlockResponse> getQueryBlockMethod;
    if ((getQueryBlockMethod = SyscallGrpc.getQueryBlockMethod) == null) {
      synchronized (SyscallGrpc.class) {
        if ((getQueryBlockMethod = SyscallGrpc.getQueryBlockMethod) == null) {
          SyscallGrpc.getQueryBlockMethod = getQueryBlockMethod =
              io.grpc.MethodDescriptor.<com.baidu.xuper.contractpb.Contract.QueryBlockRequest, com.baidu.xuper.contractpb.Contract.QueryBlockResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryBlock"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.QueryBlockRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.QueryBlockResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SyscallMethodDescriptorSupplier("QueryBlock"))
              .build();
        }
      }
    }
    return getQueryBlockMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.TransferRequest,
      com.baidu.xuper.contractpb.Contract.TransferResponse> getTransferMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Transfer",
      requestType = com.baidu.xuper.contractpb.Contract.TransferRequest.class,
      responseType = com.baidu.xuper.contractpb.Contract.TransferResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.TransferRequest,
      com.baidu.xuper.contractpb.Contract.TransferResponse> getTransferMethod() {
    io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.TransferRequest, com.baidu.xuper.contractpb.Contract.TransferResponse> getTransferMethod;
    if ((getTransferMethod = SyscallGrpc.getTransferMethod) == null) {
      synchronized (SyscallGrpc.class) {
        if ((getTransferMethod = SyscallGrpc.getTransferMethod) == null) {
          SyscallGrpc.getTransferMethod = getTransferMethod =
              io.grpc.MethodDescriptor.<com.baidu.xuper.contractpb.Contract.TransferRequest, com.baidu.xuper.contractpb.Contract.TransferResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Transfer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.TransferRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.TransferResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SyscallMethodDescriptorSupplier("Transfer"))
              .build();
        }
      }
    }
    return getTransferMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.ContractCallRequest,
      com.baidu.xuper.contractpb.Contract.ContractCallResponse> getContractCallMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ContractCall",
      requestType = com.baidu.xuper.contractpb.Contract.ContractCallRequest.class,
      responseType = com.baidu.xuper.contractpb.Contract.ContractCallResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.ContractCallRequest,
      com.baidu.xuper.contractpb.Contract.ContractCallResponse> getContractCallMethod() {
    io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.ContractCallRequest, com.baidu.xuper.contractpb.Contract.ContractCallResponse> getContractCallMethod;
    if ((getContractCallMethod = SyscallGrpc.getContractCallMethod) == null) {
      synchronized (SyscallGrpc.class) {
        if ((getContractCallMethod = SyscallGrpc.getContractCallMethod) == null) {
          SyscallGrpc.getContractCallMethod = getContractCallMethod =
              io.grpc.MethodDescriptor.<com.baidu.xuper.contractpb.Contract.ContractCallRequest, com.baidu.xuper.contractpb.Contract.ContractCallResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ContractCall"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.ContractCallRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.ContractCallResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SyscallMethodDescriptorSupplier("ContractCall"))
              .build();
        }
      }
    }
    return getContractCallMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.CrossContractQueryRequest,
      com.baidu.xuper.contractpb.Contract.CrossContractQueryResponse> getCrossContractQueryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CrossContractQuery",
      requestType = com.baidu.xuper.contractpb.Contract.CrossContractQueryRequest.class,
      responseType = com.baidu.xuper.contractpb.Contract.CrossContractQueryResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.CrossContractQueryRequest,
      com.baidu.xuper.contractpb.Contract.CrossContractQueryResponse> getCrossContractQueryMethod() {
    io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.CrossContractQueryRequest, com.baidu.xuper.contractpb.Contract.CrossContractQueryResponse> getCrossContractQueryMethod;
    if ((getCrossContractQueryMethod = SyscallGrpc.getCrossContractQueryMethod) == null) {
      synchronized (SyscallGrpc.class) {
        if ((getCrossContractQueryMethod = SyscallGrpc.getCrossContractQueryMethod) == null) {
          SyscallGrpc.getCrossContractQueryMethod = getCrossContractQueryMethod =
              io.grpc.MethodDescriptor.<com.baidu.xuper.contractpb.Contract.CrossContractQueryRequest, com.baidu.xuper.contractpb.Contract.CrossContractQueryResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CrossContractQuery"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.CrossContractQueryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.CrossContractQueryResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SyscallMethodDescriptorSupplier("CrossContractQuery"))
              .build();
        }
      }
    }
    return getCrossContractQueryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.GetAccountAddressesRequest,
      com.baidu.xuper.contractpb.Contract.GetAccountAddressesResponse> getGetAccountAddressesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAccountAddresses",
      requestType = com.baidu.xuper.contractpb.Contract.GetAccountAddressesRequest.class,
      responseType = com.baidu.xuper.contractpb.Contract.GetAccountAddressesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.GetAccountAddressesRequest,
      com.baidu.xuper.contractpb.Contract.GetAccountAddressesResponse> getGetAccountAddressesMethod() {
    io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.GetAccountAddressesRequest, com.baidu.xuper.contractpb.Contract.GetAccountAddressesResponse> getGetAccountAddressesMethod;
    if ((getGetAccountAddressesMethod = SyscallGrpc.getGetAccountAddressesMethod) == null) {
      synchronized (SyscallGrpc.class) {
        if ((getGetAccountAddressesMethod = SyscallGrpc.getGetAccountAddressesMethod) == null) {
          SyscallGrpc.getGetAccountAddressesMethod = getGetAccountAddressesMethod =
              io.grpc.MethodDescriptor.<com.baidu.xuper.contractpb.Contract.GetAccountAddressesRequest, com.baidu.xuper.contractpb.Contract.GetAccountAddressesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAccountAddresses"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.GetAccountAddressesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.GetAccountAddressesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SyscallMethodDescriptorSupplier("GetAccountAddresses"))
              .build();
        }
      }
    }
    return getGetAccountAddressesMethod;
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
    if ((getPingMethod = SyscallGrpc.getPingMethod) == null) {
      synchronized (SyscallGrpc.class) {
        if ((getPingMethod = SyscallGrpc.getPingMethod) == null) {
          SyscallGrpc.getPingMethod = getPingMethod =
              io.grpc.MethodDescriptor.<com.baidu.xuper.contractpb.Contract.PingRequest, com.baidu.xuper.contractpb.Contract.PingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Ping"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.PingRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.PingResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SyscallMethodDescriptorSupplier("Ping"))
              .build();
        }
      }
    }
    return getPingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.PostLogRequest,
      com.baidu.xuper.contractpb.Contract.PostLogResponse> getPostLogMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PostLog",
      requestType = com.baidu.xuper.contractpb.Contract.PostLogRequest.class,
      responseType = com.baidu.xuper.contractpb.Contract.PostLogResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.PostLogRequest,
      com.baidu.xuper.contractpb.Contract.PostLogResponse> getPostLogMethod() {
    io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.PostLogRequest, com.baidu.xuper.contractpb.Contract.PostLogResponse> getPostLogMethod;
    if ((getPostLogMethod = SyscallGrpc.getPostLogMethod) == null) {
      synchronized (SyscallGrpc.class) {
        if ((getPostLogMethod = SyscallGrpc.getPostLogMethod) == null) {
          SyscallGrpc.getPostLogMethod = getPostLogMethod =
              io.grpc.MethodDescriptor.<com.baidu.xuper.contractpb.Contract.PostLogRequest, com.baidu.xuper.contractpb.Contract.PostLogResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PostLog"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.PostLogRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.PostLogResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SyscallMethodDescriptorSupplier("PostLog"))
              .build();
        }
      }
    }
    return getPostLogMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.GetCallArgsRequest,
      com.baidu.xuper.contractpb.Contract.CallArgs> getGetCallArgsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetCallArgs",
      requestType = com.baidu.xuper.contractpb.Contract.GetCallArgsRequest.class,
      responseType = com.baidu.xuper.contractpb.Contract.CallArgs.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.GetCallArgsRequest,
      com.baidu.xuper.contractpb.Contract.CallArgs> getGetCallArgsMethod() {
    io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.GetCallArgsRequest, com.baidu.xuper.contractpb.Contract.CallArgs> getGetCallArgsMethod;
    if ((getGetCallArgsMethod = SyscallGrpc.getGetCallArgsMethod) == null) {
      synchronized (SyscallGrpc.class) {
        if ((getGetCallArgsMethod = SyscallGrpc.getGetCallArgsMethod) == null) {
          SyscallGrpc.getGetCallArgsMethod = getGetCallArgsMethod =
              io.grpc.MethodDescriptor.<com.baidu.xuper.contractpb.Contract.GetCallArgsRequest, com.baidu.xuper.contractpb.Contract.CallArgs>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetCallArgs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.GetCallArgsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.CallArgs.getDefaultInstance()))
              .setSchemaDescriptor(new SyscallMethodDescriptorSupplier("GetCallArgs"))
              .build();
        }
      }
    }
    return getGetCallArgsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.SetOutputRequest,
      com.baidu.xuper.contractpb.Contract.SetOutputResponse> getSetOutputMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetOutput",
      requestType = com.baidu.xuper.contractpb.Contract.SetOutputRequest.class,
      responseType = com.baidu.xuper.contractpb.Contract.SetOutputResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.SetOutputRequest,
      com.baidu.xuper.contractpb.Contract.SetOutputResponse> getSetOutputMethod() {
    io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.SetOutputRequest, com.baidu.xuper.contractpb.Contract.SetOutputResponse> getSetOutputMethod;
    if ((getSetOutputMethod = SyscallGrpc.getSetOutputMethod) == null) {
      synchronized (SyscallGrpc.class) {
        if ((getSetOutputMethod = SyscallGrpc.getSetOutputMethod) == null) {
          SyscallGrpc.getSetOutputMethod = getSetOutputMethod =
              io.grpc.MethodDescriptor.<com.baidu.xuper.contractpb.Contract.SetOutputRequest, com.baidu.xuper.contractpb.Contract.SetOutputResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetOutput"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.SetOutputRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.SetOutputResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SyscallMethodDescriptorSupplier("SetOutput"))
              .build();
        }
      }
    }
    return getSetOutputMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.EmitEventRequest,
      com.baidu.xuper.contractpb.Contract.EmitEventResponse> getEmitEventMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EmitEvent",
      requestType = com.baidu.xuper.contractpb.Contract.EmitEventRequest.class,
      responseType = com.baidu.xuper.contractpb.Contract.EmitEventResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.EmitEventRequest,
      com.baidu.xuper.contractpb.Contract.EmitEventResponse> getEmitEventMethod() {
    io.grpc.MethodDescriptor<com.baidu.xuper.contractpb.Contract.EmitEventRequest, com.baidu.xuper.contractpb.Contract.EmitEventResponse> getEmitEventMethod;
    if ((getEmitEventMethod = SyscallGrpc.getEmitEventMethod) == null) {
      synchronized (SyscallGrpc.class) {
        if ((getEmitEventMethod = SyscallGrpc.getEmitEventMethod) == null) {
          SyscallGrpc.getEmitEventMethod = getEmitEventMethod =
              io.grpc.MethodDescriptor.<com.baidu.xuper.contractpb.Contract.EmitEventRequest, com.baidu.xuper.contractpb.Contract.EmitEventResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EmitEvent"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.EmitEventRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.contractpb.Contract.EmitEventResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SyscallMethodDescriptorSupplier("EmitEvent"))
              .build();
        }
      }
    }
    return getEmitEventMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SyscallStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SyscallStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SyscallStub>() {
        @java.lang.Override
        public SyscallStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SyscallStub(channel, callOptions);
        }
      };
    return SyscallStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SyscallBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SyscallBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SyscallBlockingStub>() {
        @java.lang.Override
        public SyscallBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SyscallBlockingStub(channel, callOptions);
        }
      };
    return SyscallBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SyscallFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SyscallFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SyscallFutureStub>() {
        @java.lang.Override
        public SyscallFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SyscallFutureStub(channel, callOptions);
        }
      };
    return SyscallFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * xchain syscall service
   * </pre>
   */
  public static abstract class SyscallImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * KV service
     * </pre>
     */
    public void putObject(com.baidu.xuper.contractpb.Contract.PutRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.PutResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPutObjectMethod(), responseObserver);
    }

    /**
     */
    public void getObject(com.baidu.xuper.contractpb.Contract.GetRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.GetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetObjectMethod(), responseObserver);
    }

    /**
     */
    public void deleteObject(com.baidu.xuper.contractpb.Contract.DeleteRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.DeleteResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteObjectMethod(), responseObserver);
    }

    /**
     */
    public void newIterator(com.baidu.xuper.contractpb.Contract.IteratorRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.IteratorResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getNewIteratorMethod(), responseObserver);
    }

    /**
     * <pre>
     * Chain service
     * </pre>
     */
    public void queryTx(com.baidu.xuper.contractpb.Contract.QueryTxRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.QueryTxResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryTxMethod(), responseObserver);
    }

    /**
     */
    public void queryBlock(com.baidu.xuper.contractpb.Contract.QueryBlockRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.QueryBlockResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryBlockMethod(), responseObserver);
    }

    /**
     */
    public void transfer(com.baidu.xuper.contractpb.Contract.TransferRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.TransferResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTransferMethod(), responseObserver);
    }

    /**
     */
    public void contractCall(com.baidu.xuper.contractpb.Contract.ContractCallRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.ContractCallResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getContractCallMethod(), responseObserver);
    }

    /**
     */
    public void crossContractQuery(com.baidu.xuper.contractpb.Contract.CrossContractQueryRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.CrossContractQueryResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCrossContractQueryMethod(), responseObserver);
    }

    /**
     */
    public void getAccountAddresses(com.baidu.xuper.contractpb.Contract.GetAccountAddressesRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.GetAccountAddressesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAccountAddressesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Heartbeat
     * </pre>
     */
    public void ping(com.baidu.xuper.contractpb.Contract.PingRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.PingResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPingMethod(), responseObserver);
    }

    /**
     * <pre>
     * Post log
     * </pre>
     */
    public void postLog(com.baidu.xuper.contractpb.Contract.PostLogRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.PostLogResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPostLogMethod(), responseObserver);
    }

    /**
     */
    public void getCallArgs(com.baidu.xuper.contractpb.Contract.GetCallArgsRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.CallArgs> responseObserver) {
      asyncUnimplementedUnaryCall(getGetCallArgsMethod(), responseObserver);
    }

    /**
     */
    public void setOutput(com.baidu.xuper.contractpb.Contract.SetOutputRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.SetOutputResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSetOutputMethod(), responseObserver);
    }

    /**
     * <pre>
     * Send Event
     * </pre>
     */
    public void emitEvent(com.baidu.xuper.contractpb.Contract.EmitEventRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.EmitEventResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEmitEventMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getPutObjectMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.baidu.xuper.contractpb.Contract.PutRequest,
                com.baidu.xuper.contractpb.Contract.PutResponse>(
                  this, METHODID_PUT_OBJECT)))
          .addMethod(
            getGetObjectMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.baidu.xuper.contractpb.Contract.GetRequest,
                com.baidu.xuper.contractpb.Contract.GetResponse>(
                  this, METHODID_GET_OBJECT)))
          .addMethod(
            getDeleteObjectMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.baidu.xuper.contractpb.Contract.DeleteRequest,
                com.baidu.xuper.contractpb.Contract.DeleteResponse>(
                  this, METHODID_DELETE_OBJECT)))
          .addMethod(
            getNewIteratorMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.baidu.xuper.contractpb.Contract.IteratorRequest,
                com.baidu.xuper.contractpb.Contract.IteratorResponse>(
                  this, METHODID_NEW_ITERATOR)))
          .addMethod(
            getQueryTxMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.baidu.xuper.contractpb.Contract.QueryTxRequest,
                com.baidu.xuper.contractpb.Contract.QueryTxResponse>(
                  this, METHODID_QUERY_TX)))
          .addMethod(
            getQueryBlockMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.baidu.xuper.contractpb.Contract.QueryBlockRequest,
                com.baidu.xuper.contractpb.Contract.QueryBlockResponse>(
                  this, METHODID_QUERY_BLOCK)))
          .addMethod(
            getTransferMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.baidu.xuper.contractpb.Contract.TransferRequest,
                com.baidu.xuper.contractpb.Contract.TransferResponse>(
                  this, METHODID_TRANSFER)))
          .addMethod(
            getContractCallMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.baidu.xuper.contractpb.Contract.ContractCallRequest,
                com.baidu.xuper.contractpb.Contract.ContractCallResponse>(
                  this, METHODID_CONTRACT_CALL)))
          .addMethod(
            getCrossContractQueryMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.baidu.xuper.contractpb.Contract.CrossContractQueryRequest,
                com.baidu.xuper.contractpb.Contract.CrossContractQueryResponse>(
                  this, METHODID_CROSS_CONTRACT_QUERY)))
          .addMethod(
            getGetAccountAddressesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.baidu.xuper.contractpb.Contract.GetAccountAddressesRequest,
                com.baidu.xuper.contractpb.Contract.GetAccountAddressesResponse>(
                  this, METHODID_GET_ACCOUNT_ADDRESSES)))
          .addMethod(
            getPingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.baidu.xuper.contractpb.Contract.PingRequest,
                com.baidu.xuper.contractpb.Contract.PingResponse>(
                  this, METHODID_PING)))
          .addMethod(
            getPostLogMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.baidu.xuper.contractpb.Contract.PostLogRequest,
                com.baidu.xuper.contractpb.Contract.PostLogResponse>(
                  this, METHODID_POST_LOG)))
          .addMethod(
            getGetCallArgsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.baidu.xuper.contractpb.Contract.GetCallArgsRequest,
                com.baidu.xuper.contractpb.Contract.CallArgs>(
                  this, METHODID_GET_CALL_ARGS)))
          .addMethod(
            getSetOutputMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.baidu.xuper.contractpb.Contract.SetOutputRequest,
                com.baidu.xuper.contractpb.Contract.SetOutputResponse>(
                  this, METHODID_SET_OUTPUT)))
          .addMethod(
            getEmitEventMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.baidu.xuper.contractpb.Contract.EmitEventRequest,
                com.baidu.xuper.contractpb.Contract.EmitEventResponse>(
                  this, METHODID_EMIT_EVENT)))
          .build();
    }
  }

  /**
   * <pre>
   * xchain syscall service
   * </pre>
   */
  public static final class SyscallStub extends io.grpc.stub.AbstractAsyncStub<SyscallStub> {
    private SyscallStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SyscallStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SyscallStub(channel, callOptions);
    }

    /**
     * <pre>
     * KV service
     * </pre>
     */
    public void putObject(com.baidu.xuper.contractpb.Contract.PutRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.PutResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPutObjectMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getObject(com.baidu.xuper.contractpb.Contract.GetRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.GetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetObjectMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteObject(com.baidu.xuper.contractpb.Contract.DeleteRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.DeleteResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteObjectMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void newIterator(com.baidu.xuper.contractpb.Contract.IteratorRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.IteratorResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getNewIteratorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Chain service
     * </pre>
     */
    public void queryTx(com.baidu.xuper.contractpb.Contract.QueryTxRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.QueryTxResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryTxMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryBlock(com.baidu.xuper.contractpb.Contract.QueryBlockRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.QueryBlockResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryBlockMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void transfer(com.baidu.xuper.contractpb.Contract.TransferRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.TransferResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTransferMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void contractCall(com.baidu.xuper.contractpb.Contract.ContractCallRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.ContractCallResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getContractCallMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void crossContractQuery(com.baidu.xuper.contractpb.Contract.CrossContractQueryRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.CrossContractQueryResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCrossContractQueryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAccountAddresses(com.baidu.xuper.contractpb.Contract.GetAccountAddressesRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.GetAccountAddressesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAccountAddressesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Heartbeat
     * </pre>
     */
    public void ping(com.baidu.xuper.contractpb.Contract.PingRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.PingResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Post log
     * </pre>
     */
    public void postLog(com.baidu.xuper.contractpb.Contract.PostLogRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.PostLogResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPostLogMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getCallArgs(com.baidu.xuper.contractpb.Contract.GetCallArgsRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.CallArgs> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetCallArgsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setOutput(com.baidu.xuper.contractpb.Contract.SetOutputRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.SetOutputResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetOutputMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Send Event
     * </pre>
     */
    public void emitEvent(com.baidu.xuper.contractpb.Contract.EmitEventRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.EmitEventResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEmitEventMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * xchain syscall service
   * </pre>
   */
  public static final class SyscallBlockingStub extends io.grpc.stub.AbstractBlockingStub<SyscallBlockingStub> {
    private SyscallBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SyscallBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SyscallBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * KV service
     * </pre>
     */
    public com.baidu.xuper.contractpb.Contract.PutResponse putObject(com.baidu.xuper.contractpb.Contract.PutRequest request) {
      return blockingUnaryCall(
          getChannel(), getPutObjectMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.baidu.xuper.contractpb.Contract.GetResponse getObject(com.baidu.xuper.contractpb.Contract.GetRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetObjectMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.baidu.xuper.contractpb.Contract.DeleteResponse deleteObject(com.baidu.xuper.contractpb.Contract.DeleteRequest request) {
      return blockingUnaryCall(
          getChannel(), getDeleteObjectMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.baidu.xuper.contractpb.Contract.IteratorResponse newIterator(com.baidu.xuper.contractpb.Contract.IteratorRequest request) {
      return blockingUnaryCall(
          getChannel(), getNewIteratorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Chain service
     * </pre>
     */
    public com.baidu.xuper.contractpb.Contract.QueryTxResponse queryTx(com.baidu.xuper.contractpb.Contract.QueryTxRequest request) {
      return blockingUnaryCall(
          getChannel(), getQueryTxMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.baidu.xuper.contractpb.Contract.QueryBlockResponse queryBlock(com.baidu.xuper.contractpb.Contract.QueryBlockRequest request) {
      return blockingUnaryCall(
          getChannel(), getQueryBlockMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.baidu.xuper.contractpb.Contract.TransferResponse transfer(com.baidu.xuper.contractpb.Contract.TransferRequest request) {
      return blockingUnaryCall(
          getChannel(), getTransferMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.baidu.xuper.contractpb.Contract.ContractCallResponse contractCall(com.baidu.xuper.contractpb.Contract.ContractCallRequest request) {
      return blockingUnaryCall(
          getChannel(), getContractCallMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.baidu.xuper.contractpb.Contract.CrossContractQueryResponse crossContractQuery(com.baidu.xuper.contractpb.Contract.CrossContractQueryRequest request) {
      return blockingUnaryCall(
          getChannel(), getCrossContractQueryMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.baidu.xuper.contractpb.Contract.GetAccountAddressesResponse getAccountAddresses(com.baidu.xuper.contractpb.Contract.GetAccountAddressesRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetAccountAddressesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Heartbeat
     * </pre>
     */
    public com.baidu.xuper.contractpb.Contract.PingResponse ping(com.baidu.xuper.contractpb.Contract.PingRequest request) {
      return blockingUnaryCall(
          getChannel(), getPingMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Post log
     * </pre>
     */
    public com.baidu.xuper.contractpb.Contract.PostLogResponse postLog(com.baidu.xuper.contractpb.Contract.PostLogRequest request) {
      return blockingUnaryCall(
          getChannel(), getPostLogMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.baidu.xuper.contractpb.Contract.CallArgs getCallArgs(com.baidu.xuper.contractpb.Contract.GetCallArgsRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetCallArgsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.baidu.xuper.contractpb.Contract.SetOutputResponse setOutput(com.baidu.xuper.contractpb.Contract.SetOutputRequest request) {
      return blockingUnaryCall(
          getChannel(), getSetOutputMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Send Event
     * </pre>
     */
    public com.baidu.xuper.contractpb.Contract.EmitEventResponse emitEvent(com.baidu.xuper.contractpb.Contract.EmitEventRequest request) {
      return blockingUnaryCall(
          getChannel(), getEmitEventMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * xchain syscall service
   * </pre>
   */
  public static final class SyscallFutureStub extends io.grpc.stub.AbstractFutureStub<SyscallFutureStub> {
    private SyscallFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SyscallFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SyscallFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * KV service
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.contractpb.Contract.PutResponse> putObject(
        com.baidu.xuper.contractpb.Contract.PutRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPutObjectMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.contractpb.Contract.GetResponse> getObject(
        com.baidu.xuper.contractpb.Contract.GetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetObjectMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.contractpb.Contract.DeleteResponse> deleteObject(
        com.baidu.xuper.contractpb.Contract.DeleteRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteObjectMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.contractpb.Contract.IteratorResponse> newIterator(
        com.baidu.xuper.contractpb.Contract.IteratorRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getNewIteratorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Chain service
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.contractpb.Contract.QueryTxResponse> queryTx(
        com.baidu.xuper.contractpb.Contract.QueryTxRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryTxMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.contractpb.Contract.QueryBlockResponse> queryBlock(
        com.baidu.xuper.contractpb.Contract.QueryBlockRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryBlockMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.contractpb.Contract.TransferResponse> transfer(
        com.baidu.xuper.contractpb.Contract.TransferRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTransferMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.contractpb.Contract.ContractCallResponse> contractCall(
        com.baidu.xuper.contractpb.Contract.ContractCallRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getContractCallMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.contractpb.Contract.CrossContractQueryResponse> crossContractQuery(
        com.baidu.xuper.contractpb.Contract.CrossContractQueryRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCrossContractQueryMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.contractpb.Contract.GetAccountAddressesResponse> getAccountAddresses(
        com.baidu.xuper.contractpb.Contract.GetAccountAddressesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAccountAddressesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Heartbeat
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.contractpb.Contract.PingResponse> ping(
        com.baidu.xuper.contractpb.Contract.PingRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Post log
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.contractpb.Contract.PostLogResponse> postLog(
        com.baidu.xuper.contractpb.Contract.PostLogRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPostLogMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.contractpb.Contract.CallArgs> getCallArgs(
        com.baidu.xuper.contractpb.Contract.GetCallArgsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetCallArgsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.contractpb.Contract.SetOutputResponse> setOutput(
        com.baidu.xuper.contractpb.Contract.SetOutputRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSetOutputMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Send Event
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.contractpb.Contract.EmitEventResponse> emitEvent(
        com.baidu.xuper.contractpb.Contract.EmitEventRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getEmitEventMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PUT_OBJECT = 0;
  private static final int METHODID_GET_OBJECT = 1;
  private static final int METHODID_DELETE_OBJECT = 2;
  private static final int METHODID_NEW_ITERATOR = 3;
  private static final int METHODID_QUERY_TX = 4;
  private static final int METHODID_QUERY_BLOCK = 5;
  private static final int METHODID_TRANSFER = 6;
  private static final int METHODID_CONTRACT_CALL = 7;
  private static final int METHODID_CROSS_CONTRACT_QUERY = 8;
  private static final int METHODID_GET_ACCOUNT_ADDRESSES = 9;
  private static final int METHODID_PING = 10;
  private static final int METHODID_POST_LOG = 11;
  private static final int METHODID_GET_CALL_ARGS = 12;
  private static final int METHODID_SET_OUTPUT = 13;
  private static final int METHODID_EMIT_EVENT = 14;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SyscallImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SyscallImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PUT_OBJECT:
          serviceImpl.putObject((com.baidu.xuper.contractpb.Contract.PutRequest) request,
              (io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.PutResponse>) responseObserver);
          break;
        case METHODID_GET_OBJECT:
          serviceImpl.getObject((com.baidu.xuper.contractpb.Contract.GetRequest) request,
              (io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.GetResponse>) responseObserver);
          break;
        case METHODID_DELETE_OBJECT:
          serviceImpl.deleteObject((com.baidu.xuper.contractpb.Contract.DeleteRequest) request,
              (io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.DeleteResponse>) responseObserver);
          break;
        case METHODID_NEW_ITERATOR:
          serviceImpl.newIterator((com.baidu.xuper.contractpb.Contract.IteratorRequest) request,
              (io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.IteratorResponse>) responseObserver);
          break;
        case METHODID_QUERY_TX:
          serviceImpl.queryTx((com.baidu.xuper.contractpb.Contract.QueryTxRequest) request,
              (io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.QueryTxResponse>) responseObserver);
          break;
        case METHODID_QUERY_BLOCK:
          serviceImpl.queryBlock((com.baidu.xuper.contractpb.Contract.QueryBlockRequest) request,
              (io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.QueryBlockResponse>) responseObserver);
          break;
        case METHODID_TRANSFER:
          serviceImpl.transfer((com.baidu.xuper.contractpb.Contract.TransferRequest) request,
              (io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.TransferResponse>) responseObserver);
          break;
        case METHODID_CONTRACT_CALL:
          serviceImpl.contractCall((com.baidu.xuper.contractpb.Contract.ContractCallRequest) request,
              (io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.ContractCallResponse>) responseObserver);
          break;
        case METHODID_CROSS_CONTRACT_QUERY:
          serviceImpl.crossContractQuery((com.baidu.xuper.contractpb.Contract.CrossContractQueryRequest) request,
              (io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.CrossContractQueryResponse>) responseObserver);
          break;
        case METHODID_GET_ACCOUNT_ADDRESSES:
          serviceImpl.getAccountAddresses((com.baidu.xuper.contractpb.Contract.GetAccountAddressesRequest) request,
              (io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.GetAccountAddressesResponse>) responseObserver);
          break;
        case METHODID_PING:
          serviceImpl.ping((com.baidu.xuper.contractpb.Contract.PingRequest) request,
              (io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.PingResponse>) responseObserver);
          break;
        case METHODID_POST_LOG:
          serviceImpl.postLog((com.baidu.xuper.contractpb.Contract.PostLogRequest) request,
              (io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.PostLogResponse>) responseObserver);
          break;
        case METHODID_GET_CALL_ARGS:
          serviceImpl.getCallArgs((com.baidu.xuper.contractpb.Contract.GetCallArgsRequest) request,
              (io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.CallArgs>) responseObserver);
          break;
        case METHODID_SET_OUTPUT:
          serviceImpl.setOutput((com.baidu.xuper.contractpb.Contract.SetOutputRequest) request,
              (io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.SetOutputResponse>) responseObserver);
          break;
        case METHODID_EMIT_EVENT:
          serviceImpl.emitEvent((com.baidu.xuper.contractpb.Contract.EmitEventRequest) request,
              (io.grpc.stub.StreamObserver<com.baidu.xuper.contractpb.Contract.EmitEventResponse>) responseObserver);
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

  private static abstract class SyscallBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SyscallBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.baidu.xuper.contractpb.ContractService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Syscall");
    }
  }

  private static final class SyscallFileDescriptorSupplier
      extends SyscallBaseDescriptorSupplier {
    SyscallFileDescriptorSupplier() {}
  }

  private static final class SyscallMethodDescriptorSupplier
      extends SyscallBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SyscallMethodDescriptorSupplier(String methodName) {
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
      synchronized (SyscallGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SyscallFileDescriptorSupplier())
              .addMethod(getPutObjectMethod())
              .addMethod(getGetObjectMethod())
              .addMethod(getDeleteObjectMethod())
              .addMethod(getNewIteratorMethod())
              .addMethod(getQueryTxMethod())
              .addMethod(getQueryBlockMethod())
              .addMethod(getTransferMethod())
              .addMethod(getContractCallMethod())
              .addMethod(getCrossContractQueryMethod())
              .addMethod(getGetAccountAddressesMethod())
              .addMethod(getPingMethod())
              .addMethod(getPostLogMethod())
              .addMethod(getGetCallArgsMethod())
              .addMethod(getSetOutputMethod())
              .addMethod(getEmitEventMethod())
              .build();
        }
      }
    }
    return result;
  }
}
