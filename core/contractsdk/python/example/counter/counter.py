from xuperchain import Context
from xuperchain import Driver

class Counter():
    def __init__(self, ctx: Context):
        pass

    def get(self, ctx: Context):
        pass

    def increase(self, ctx: Context):
        pass


if __name__ == "__main__":
    Driver.serve(Counter())
