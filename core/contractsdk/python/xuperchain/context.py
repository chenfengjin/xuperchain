
class Context():
    def __init__(self,ctxid,bridgeCallFunc):
        self.contractArgs = None
        self.bridgeCallfunc = bridgeCallFunc
        self.header = None
        request = None
        bridgeCallFunc("methodGetCallArgs",request,callArgs)
        self.contractArgs = {}
        for key,value in callargs:
            self.contractArgs[key] = value

    def get_object(self):
        pass
    def put_object(self):
        pass
    def delete_object(self):
        pass
    def emit_event(self):
        pass
    def emit_json_event(self): #TODO name
        pass
    def set_output(self):
        pass

# bridgeCallFunc
# Init
# Method
# Args
# Caller
# Initiator
# AuthRequire
# PutObject
# GetObject
# DeleteObject
# NewIterator
# QueryTx
# QueryBlock
# Transfer
# TransferAmount
# Call
# CrossQuery
# SetOutput
# Logf
# EmitEvent
# EmitJSONEvent
