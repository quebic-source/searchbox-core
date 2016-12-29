local function get_models_by_ids(mapKey, modelIds, pageLength, pageNo)
	
	local totalIdsCount = #modelIds
	local totalDocCount = 0
	local result = {}
	local documents = {}
	local _t = {}
	local pageIndex = 0;
	local pageCounter = 0;
	
	for k,v in pairs(modelIds) do
		
		local modelData = redis.call('HMGET', mapKey, v)
	
		if modelData[1] then
	
		    table.insert(_t, modelData[1])
		    
		    documents[pageIndex] = _t
		    
		    if pageCounter == pageLength - 1 then
		      _t = {}
		      pageIndex = pageIndex + 1;
		      pageCounter = 0;
			else
				pageCounter = pageCounter + 1
			end
			
			totalDocCount = totalDocCount + 1
			
		end
		
	end
	
	result['result'] = documents[pageNo];
	
	result['documentsCount'] = totalDocCount;
	
	result['pageLength'] = pageLength
	
	result['pageNo'] = pageNo
	
	if pageCounter == 0 then
		result['pagesCount'] = pageIndex
	else
		result['pagesCount'] = pageIndex + 1
	end
	
	return cjson.encode(result)
	
end
