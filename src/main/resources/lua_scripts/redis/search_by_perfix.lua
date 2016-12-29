local zset_autocomplete_key = KEYS[1]
local hm_key = KEYS[2]

local searchPrefix = ARGV[1]
local pageLength = tonumber(ARGV[2])
local pageNo = tonumber(ARGV[3])
local allWords = ARGV[4]

local modelIds = redis_zrangebylex(zset_autocomplete_key, searchPrefix, allWords)

return get_models_by_ids(hm_key, modelIds, pageLength, pageNo)
