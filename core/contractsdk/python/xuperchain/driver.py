import os
import grpc
from xuperchain.contract import *

class CodeService():
    def __init__(self,xchain_addr,contract):
        self.xchain_addr = xchain_addr
        self.contract = contract

    def bridge_call(self):
        pass
    def call(self,context,request):
        ctx = None
        code = None
        return code(ctx)
    def ping(self):
        pass
    # TODO to another class
    # def run(self,request:NativeCallRequest,context):
    #     request = NativeCallRequest()
    #     ctx = None

class Driver():
    def __init__(self):
        pass
    #  TODO 实用单例模式?
    @staticmethod
    def serve(self, contract:Contract):
        chain_addr = os.environ.get("XCHAIN_CHAIN_ADDR")
        code_port = os.environ.get("XCHAIN_CODE_ADDR")
        server = grpc.server()
        # route_guide_pb2_grpc.add_RouteGuideServicer_to_server(
        #     RouteGuideServicer(), server)
        server.add_insecure_port('[::]:'+code_port) # ipv4?
        server.start()
        server.wait_for_termination()



