from xuperchain.context import Context
from xuperchain.contract_service.contract_service_pb2_grpc import SyscallStub
import grpc
# class CodeService():
#     def __init__(self, xchain_addr, contract):
#         self.xchain_addr = xchain_addr
#         self.contract = contract
#         channel = grpc.insecure_channel(xchain_addr)
#         conn = SyscallStub(channel=channel)
#         self.rpcClient = conn
#
#     def bridge_call(self):
#         # why bridge call
#         pass
#
#     def call(self, context, request):
#         ctx = Context()
#         # code = None
#         method = ""
#         if not hash(self.contract, method):
#             raise Exception  # TODO
#         f = getattr(self.contract, method)
#         #     TODO 类型判断
#         f(ctx)
#         ctx.setOutput()
#
#     def ping(self):
#         pass