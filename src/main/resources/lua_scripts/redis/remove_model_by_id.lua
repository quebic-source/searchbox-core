local function remove_autocomplete_set(setKey, fieldValue, id)

	local cursor = '0'
	local done = false
	local level = 1
		
	repeat

		local scan_result = redis.call('SCAN', cursor, 'MATCH', setKey)
		cursor =  scan_result[1]
				
		for y,x in pairs(scan_result[2]) do
			redis.call('ZREM', x, string.lower(fieldValue .. ':' .. id))
		end
			
		if cursor == '0' then
			done = true;
		end
			
	until done
	
end

local function remove_index_set(setKey, id)
	redis.call('ZREM', setKey, id)
end

local app_name = ARGV[1]
local model_name = ARGV[2]
local id = ARGV[3]

local table_prefix = KEYS[1]
local index_prefix = KEYS[2]
local auto_prefix = KEYS[3]

local table_set_key = app_name .. ':' .. table_prefix .. ':' .. model_name

if redis.call('HEXISTS', table_set_key, id) == 0 then
	return -1
end 

local modelData = redis.call('HMGET', table_set_key, id)[1]

redis.replicate_commands()
redis.call('HDEL', table_set_key, id)

for k,v in pairs(cjson.decode(modelData)) do
	
	local autocomplete_set_key = app_name .. ':' .. auto_prefix .. ':' .. model_name .. ':' .. k .. ':*'
	remove_autocomplete_set(autocomplete_set_key, v, id)
	
	local index_set_key = app_name .. ':' .. index_prefix .. ':' .. model_name .. ':' .. k .. ':' .. remove_spaces(v)
	remove_index_set(index_set_key, id)
	
end

return 1
