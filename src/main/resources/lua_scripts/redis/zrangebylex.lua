local function redis_zrangebylex(setName, prefix, allWords)
	
	local result = {}
	
	local splictedWords = split_to_words(prefix)
	
	if allWords ~= true then
	
		local preparePrefixA = '[' .. splictedWords[1]
		local preparePrefixB = '[' .. splictedWords[1] .. 'xff'
		local setNameFirstLevel = setName .. ':1'
		local t = redis.call('ZRANGEBYLEX', setNameFirstLevel, preparePrefixA, preparePrefixB)
			
		for key,value in pairs(t) do
			local s = split(value, ':') 
			table.insert(result, s[2]) 
		end
	
		return result
		
	else
		
		local tmp_id_container = {}
		local _cnt = 1
		for k,v in pairs(splictedWords) do
	
			local preparePrefixA = '[' .. v
			local preparePrefixB = '[' .. v .. 'xff'
			local searchSetPrefix = setName .. ':*'
			
			local cursor = '0'
			local done = false
			local level = 1
			
			repeat

				local scan_result = redis.call('SCAN', cursor, 'MATCH', searchSetPrefix)
				cursor =  scan_result[1]
				
				for y,x in pairs(scan_result[2]) do
					
					local _set = setName .. ':' .. level
					level = level + 1
					
					local t = redis.call('ZRANGEBYLEX', _set, preparePrefixA, preparePrefixB)
					for key,value in pairs(t) do
						
						local s = split(value, ':') 
						local s_2 = s[2]
						
						if tmp_id_container[s_2] == nil then
							tmp_id_container[s_2] = 1
							table.insert(result, s_2)
						end
						
					end
					
				end
			
				if cursor == '0' then
			        done = true;
			    end
			
			until done
			
		end
		
		return result
		
	end
	
end

