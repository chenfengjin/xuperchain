pragma solidity >=0.0.0;
import map from "iterable_mapping.sol";


contract HashDeposit {
    it_mapping.IterableMapping.itmap user_map;
    it_mapping.IterableMapping.itmap hash_map;

    constructor() public{
    }
    function storeFileInfo(string memory user_id, string memory hash_id, string memory filename){
        string memory UserBucket = "USER_";
        string memory HashBucket = "HASH_";
        string memory value = user_id+"\t" + hash_id+"\t" + filename + filename;
        string memory user_key = UserBucket + "/" + user_id + "/" + hash_id;
        string memory hash_key = HashBucket + "/" + hash_id;
        string memory user_key = "";
        string memory hash_key = "";
        string memory value = "";
        it_mapping.IterableMapping.insert(user_map,user_key,value);
        it_mapping.IterableMapping.insert(hash_map,hash_key,value);
//        TODO
        ite[user_key] = value;
        hash_map[hash_key] = value;
    }
    function queryUserList(){
        string memory result = "";
        for (uint i = it_mapping.IterableMapping.iterate_start(user_map); it_mapping.IterableMapping.iterate_valid(user_map, i); i = it_mapping.IterableMapping.iterate_next(user_map, i))
        {
            (uint key, uint value) = it_mapping.IterableMapping.iterate_get(user_map, i);
            result += value;
        }
        return result;
    }

    function queryFileInfoByUser(string memory user_id) returns (string memory){
        string memory UserBucket = "User_";
        string memory key = UserBucket+"/" +  user_id;
        string memory result = "";
        for (uint i = it_mapping.IterableMapping.iterate_start(user_map); it_mapping.IterableMapping.iterate_valid(user_map, i); i = it_mapping.IterableMapping.iterate_next(user_map, i))
        {
            (uint key, uint value) = it_mapping.IterableMapping.iterate_get(user_map, i);
            if (true){ // TODO
                result = string(abi.encodePacked(key));
            }
        }
        return result;
    }

    function queryFileInfoByHash(string memory hash_id) returns (string memory){
        string memory HashBucket="HASH_";
//        string memory hash_key = HashBucket + "/" + hash_id;
        string memory hash_key =   string(abi.encodePacked(HashBucket,"/",hash_id));

    return hash_map[hash_key];
    }
    
}
