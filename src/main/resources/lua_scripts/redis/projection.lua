local function projection(t, hideFields)

  if(hideFields ~= nil) then
  
  	for index = 1, #t do
  		
  		local result = cjson.decode(t[index])
  		local hideFieldsTable = cjson.decode(hideFields)
  		
  		for k,v in pairs(hideFieldsTable) do
  			result[k] = nil
  		end
  		
  		t[index] = cjson.encode(result)
  		
  	end
  
  end
 
  return t
  
end
