local function get_models_by_pattern(setName, pattern)
	
	local result = {}
	
	local cursor = '0'
	local done = false
	
	repeat
		
		local scan_result = redis.call('SCAN', cursor, 'MATCH', setName .. ':' .. pattern)
		cursor =  scan_result[1]
		
		for k,v in pairs(scan_result[2]) do
		
			local modelIds = redis.call('ZRANGE', v, 0, -1)
			
			for q,u in pairs(modelIds) do
				table.insert(result, u)
			end
		
		end
		
		if cursor == '0' then
			done = true;
		end
		
	until done
	
	return result
	
end
