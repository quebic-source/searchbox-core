local function get_models_by_range(setName, condition, searchValue)
	
	local cursor = '0'
	local done = false;
	local result = {}
	local setNamePrefix = setName  .. '*'
	
	local value = tonumber(searchValue)
	
	repeat
		local scanResult = redis.call('SCAN', cursor, 'MATCH', setNamePrefix)
		
		cursor =  scanResult[1]
		
		for k,v in pairs(scanResult[2]) do
			
			local number = tonumber(split(v, ':')[5])
			
			if  type(number) == 'number' then
				
				if condition == 'gt' then 
					
					if number > value then
						
						local ids = redis.call('ZRANGE', v, 0, -1)
						
						for x,y in pairs(ids) do
							table.insert(result, y)
						end
						
					end
					
				elseif condition == 'gte' then
				
					if number >= value then
						
						local ids = redis.call('ZRANGE', v, 0, -1)
						
						for x,y in pairs(ids) do
							table.insert(result, y)
						end
						
					end
				
				elseif condition == 'lt' then
					
					if number < value then
						
						local ids = redis.call('ZRANGE', v, 0, -1)
						
						for x,y in pairs(ids) do
							table.insert(result, y)
						end
						
					end
					
				else
					
					if number <= value then
						
						local ids = redis.call('ZRANGE', v, 0, -1)
						
						for x,y in pairs(ids) do
							table.insert(result, y)
						end
						
					end
					
				end
			
			end
			
		end
		
		if cursor == '0' then
	        done = true;
	    end
		
	until done
	
	return result

end
