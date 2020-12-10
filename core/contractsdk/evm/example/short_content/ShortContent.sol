pragma solidity >=0.0.0;
import "iterable_mapping.sol" as it_mapping;
import "strings.sol";


contract ShortContent {
    using strings for *;
    it_mapping.IterableMapping.itmap data;
    string  UserBucket = "USER_";
    uint256 TOPIC_LENGTH_LIMIT = 36;
    uint256 TITLE_LENGTH_LIMIT = 100;
    uint256 CONTENT_LENGTH_LIMIT = 3000;

    constructor() public{
    }
    function storeShortContent(string memory user_id,string memory title, string memory topic, string memory content) public {
        string memory key = string(abi.encodePacked(UserBucket,"/",user_id,"/",topic,"/",title));
        require( bytes(topic).length > TOPIC_LENGTH_LIMIT ||
        bytes(content).length > CONTENT_LENGTH_LIMIT || bytes(title).length > TITLE_LENGTH_LIMIT,
        "The length of topic or title or content is more than limitation");
        it_mapping.IterableMapping.insert(data,key,content);
    }
    function queryByUser(string memory user_id) public returns (string memory result){
//        string memory result=";
        string memory key = string(abi.encodePacked(UserBucket,"/",user_id,"/"));
        for (uint256 i = it_mapping.IterableMapping.iterate_start(data); it_mapping.IterableMapping.iterate_valid(data, i); i = it_mapping.IterableMapping.iterate_next(data, i))
        {
            (string memory k,string  memory v )= it_mapping.IterableMapping.iterate_get(data, i);
            if (k.toSlice().startsWith(key.toSlice())){
//                result.toSlice().concat (v.toSlice());
            }
        }
    }

    function queryByTitle(string memory user_id,string memory topic,string memory title) public  returns (string memory result){
        string memory key = string(abi.encodePacked(UserBucket,"/",user_id,"/",topic,"/",title));
        result = data.data[key].value;
    }
    function queryByTopic(string memory user_id,string memory topic) public returns(string memory result){
        string memory key = string(abi.encodePacked(UserBucket,"/",user_id,"/",topic));
        for (uint256 i = it_mapping.IterableMapping.iterate_start(data); it_mapping.IterableMapping.iterate_valid(data, i); i = it_mapping.IterableMapping.iterate_next(data, i))
        {
            (string memory k,string  memory v )= it_mapping.IterableMapping.iterate_get(data, i);
            if (k.toSlice().startsWith(key.toSlice())){
//            result.concat (v.toSlice());
            }
        }
    }
}
