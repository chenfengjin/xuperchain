from xuperchain import Context
from xuperchain import Driver

class Counter():
    def __init__(self, ctx: Context):
        pass
    #TODO  super.XXX ??

    def get(self, ctx: Context):
        ctx.GetObject()
        pass

    def increase(self, ctx: Context):
        pass


if __name__ == "__main__":
    Driver.serve(Counter())
