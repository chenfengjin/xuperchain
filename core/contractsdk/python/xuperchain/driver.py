import os
import grpc
from xuperchain.contract import *
from xuperchain.contract_service import NativeCodeServicer
from xuperchain.contract_service import NativeCode

class Driver():
    def __init__(self):
        pass

    #  TODO 单例子? 静态方法?
    @staticmethod
    def serve(self, contract: any):
        chain_addr = os.environ.get("XCHAIN_CHAIN_ADDR")
        code_port = os.environ.get("XCHAIN_CODE_ADDR")
        code_service = NativeCodeServicer()
        code_service.SetContract(contract=contract)
        server = grpc.server(NativeCodeServicer)

        server.add_insecure_port('[::]:' + code_port)  # ipv4?
        server.start()
        server.wait_for_termination()
        # self.conn = None
        # TODO