local zset_index_table_key = KEYS[1]
local hm_key = KEYS[2]

local pageLength = tonumber(ARGV[1])
local pageNo = tonumber(ARGV[2])

local modelIds = redis.call('ZRANGE', zset_index_table_key, 0, -1)

return get_models_by_ids(hm_key, modelIds, pageLength, pageNo)