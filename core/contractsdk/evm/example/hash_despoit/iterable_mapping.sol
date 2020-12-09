
/// @dev Models a uint -> uint mapping where it is possible to iterate over all keys.
pragma solidity ^0.7.5;
library IterableMapping
{
    struct itmap
    {
        mapping(string => IndexValue) data;
        KeyFlag[] keys;
        uint size;
    }
    struct IndexValue { uint keyIndex; string value; } // value 是真实的 value
    struct KeyFlag { string  key; bool deleted; } //key 是真实的 key


    function insert(itmap storage self, string memory key, string memory value)  public returns (bool replaced)
    {
        uint keyIndex = self.data[key].keyIndex;
        self.data[key].value = value;
        if (keyIndex > 0)
            return true;
        else
        {
            keyIndex = self.keys.length+1; // TODO ok?
            self.data[key].keyIndex = keyIndex + 1; //TODO 这里还需要 +1 么？
            self.keys[keyIndex].key = key; //隐含ledeleted false
            self.size++;
            return false;
        }
    }
//    function remove(itmap storage self, uint key) public returns (bool success)
//    {
//        uint keyIndex = self.data[key].keyIndex;
//        if (keyIndex == 0)
//            return false;
//        delete self.data[key];
//        self.keys[keyIndex - 1].deleted = true;
//        self.size --;
//    }
//    function contains(itmap storage self, uint key) public returns (bool)
//    {
//        return self.data[key].keyIndex > 0;
//    }
    function iterate_start(itmap storage self) public returns (uint keyIndex)
    {
        return iterate_next(self, uint(-1));
    }
    function iterate_valid(itmap storage self, uint keyIndex) public returns (bool)
    {
        return keyIndex < self.keys.length;
    }
    function iterate_next(itmap storage self, uint keyIndex) public returns (uint r_keyIndex)
    {
        keyIndex++;
        while (keyIndex < self.keys.length && self.keys[keyIndex].deleted)
            keyIndex++;
        return keyIndex;
    }
    function iterate_get(itmap storage self, uint keyIndex) public returns (string memory key, string memory value)
    {
        key = self.keys[keyIndex].key;
        value = self.data[key].value;
    }
}

// How to use it:
contract User
{
    // Just a struct holding our data.
    IterableMapping.itmap data;
    // Insert something
//    function insert(string memory k, string memory v)  public  returns (string memory size)
//    {
//        // Actually calls itmap_impl.insert, auto-supplying the first parameter for us.
//        IterableMapping.insert(data, k, v);
//        // We can still access members of the struct - but we should take care not to mess with them.
//        return data.size;
//    }
    // Computes the sum of all stored data.
    function sum() public returns  (string memory s)
    {
        for (uint256 i = IterableMapping.iterate_start(data); IterableMapping.iterate_valid(data, i); i = IterableMapping.iterate_next(data, i))
        {
            (string memory key,string  memory value )= IterableMapping.iterate_get(data, i);
            s = value;
        }
    }
}
