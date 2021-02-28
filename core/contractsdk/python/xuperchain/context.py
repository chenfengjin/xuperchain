from xuperchain.contract_service import SyscallStub
from xuperchain import contract as contract__pb2


class Context(SyscallStub):
    def __init__(self, ctxid, bridgeCallFunc):
        self.contractArgs = None
        self.bridgeCallfunc = bridgeCallFunc
        header = contract__pb2.SyscallHeader(ctxid=ctxid)
        self.header = header
        resp = super().GetCallArgs(header)
        # TODO 这里得重写
        self.method = resp.method
        self.initiator = resp.initiator
        self.callArgs = {}
        self.transfer_amoubnt = resp.transfer_amount
        for key, value in resp.args:
            self.callArgs[key] = value
        self.auth_require = resp.auth_require

    def PutObject(self, key, value):
        req = contract__pb2.PutRequest(header=self.header, key=key, value=value)
        super().PutObject(req)

    def GetObject(self, key):
        req = contract__pb2.GetRequest(header=self.header, key=key)
        super().GetObject(key)

    def DeleteObject(self, key):
        req = contract__pb2.DeleteRequest(header=self.header, key=key)
        super().DeleteObject(req)

    def QueryTx(self, txid):
        req = contract__pb2.QueryTxRequest(header=self.header, txid=txid)
        super().QueryTx(req)
