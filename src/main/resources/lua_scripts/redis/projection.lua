local function projection(t, hideFields, showFields)
  
  print('t')
  print(t)
  print('showFields')
  print(showFields)
 
  
  if(hideFields ~= nil) then
    print('hideFields')
  	print(hideFields)
    for k,v in pairs(cjson.decode(hideFields)) do
      t[k] = nil
    end
    
  elseif(showFields ~= nil) then
    local _showFields = cjson.decode(showFields);
    for k,v in pairs(t) do
      if(_showFields[k] == nil) then
        t[k] = nil
      end
    end
  end
  
  return t
end
