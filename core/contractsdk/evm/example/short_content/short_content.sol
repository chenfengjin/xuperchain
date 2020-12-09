pragma solidity >=0.0.0;
import "iterable_mapping.sol" as it_mapping;
import "github.com/Arachnid/solidity-stringutils/strings.sol";


contract ShortContent {
    using strings for *;
    it_mapping.IterableMapping.itmap data;
    string  USER_BUCKET = "USER_";
    int TOPIC_LENGTH_LIMIT = 36;
    int TITLE_LENGTH_LIMIT = 100;
    int CONTENT_LENGTH_LIMIT = 3000;

constructor() public{
    }
    function storeShortContent(string memory user_id,string memory title, string memory topic, string memory content){
        string key = string(abi.encodePacked(UserBucket,"/",user_id,"/",topic,"/",title));
        require( topic.length > TOPIC_LENGTH_LIMIT || content.length > CONTENT_LENGTH_LIMIT || title.length > TITLE_LENGTH_LIMIT,
        "The length of topic or title or content is more than limitation");
        it_map.insert(data,key,content);
    }
    function queryByUser(string memory user_id) returns (string result){
        string key = string(abi.encodePacked(UserBucket,"/",user_id,"/"));
        for (uint256 i = IterableMapping.iterate_start(data); IterableMapping.iterate_valid(data, i); i = IterableMapping.iterate_next(data, i))
        {
            (string memory k,string  memory v )= IterableMapping.iterate_get(data, i);
            if (k.toSlice().startsWith(key.toSlice())){
                result.concat (v.toSlice());
            }
        }
    }

    function queryByTitle(string memory user_id,string memory topic,string memory title) returns (string result){
        string key = string(abi.encodePacked(UserBucket,"/",user_id,"/",topic,"/"));
        result = data.data[key].value;
    }
    function queryByTopic(string memory user_id,string memory topic) returns(string result){
        string key = string(abi.encodePacked(UserBucket,"/",user_id,"/",topic,"/",title));
        for (uint256 i = IterableMapping.iterate_start(data); IterableMapping.iterate_valid(data, i); i = IterableMapping.iterate_next(data, i))
        {
            (string memory k,string  memory v )= IterableMapping.iterate_get(data, i);
            if (k.toSlice().startsWith(key.toSlice())){
            result.concat (v.toSlice());
            }
        }
    }
}
