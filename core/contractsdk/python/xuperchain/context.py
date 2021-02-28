from xuperchain.contract_service import SyscallStub
from xuperchain import contract as contract__pb2

class Context(SyscallStub):
    def __init__(self,ctxid,bridgeCallFunc):
        self.contractArgs = None
        self.bridgeCallfunc = bridgeCallFunc
        self.header = contract__pb2.SyscallHeader(ctxid = ctxid)
        request = None
        # bridgeCallFunc("methodGetCallArgs",request,callArgs)
        self.contractArgs = {}
        # for key,value in callargs:
        #     self.contractArgs[key] = value


    def PutObject(self,key,value):
        req = contract__pb2.PutRequest(header = self.header,key=key,value=value)
        super().PutObject(req)

    def GetObject(self,key):
        req = contract__pb2.GetRequest(header=self.header,key = key)
        super().GetObject(key)

    def DeleteObject(self,key):
        req = contract__pb2.DeleteRequest(header = self.header,key = key)
        super().DeleteObject(req)

    def QueryTx(self,txid):
        req = contract__pb2.QueryTxRequest(header = self.header,txid=txid)
        super().QueryTx(req)