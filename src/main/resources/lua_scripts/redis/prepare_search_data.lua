local zset_autocomplete_table_key = KEYS[1]
local zset_index_table_key =  KEYS[2]

local autoCompleteValue = ARGV[1]
local id = ARGV[2]

for k,v in pairs(split_to_words(autoCompleteValue)) do
	redis.call('ZADD', zset_autocomplete_table_key .. ':' .. k, 0.0, v .. ':' .. id) 
end

redis.call('ZADD', zset_index_table_key, 0.0, id)

return 1
