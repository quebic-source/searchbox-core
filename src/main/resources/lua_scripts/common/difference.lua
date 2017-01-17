local function difference(a,b)
	local ret = {}
	for _,a_ in ipairs(a) do
		if not find(a_,b) then table.insert(ret, a_) end
	end
	return ret
end