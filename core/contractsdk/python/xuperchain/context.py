
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

    def getObject(self):
        pass
    def putObject(self):
        pass
    def deleteObject(self):
        pass
    def emitEvent(self):
        pass
    def emitJsonEvent(self):
        pass
