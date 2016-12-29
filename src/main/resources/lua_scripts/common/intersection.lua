local function intersection(a, b)
	local ret = {}
	for _,b_ in ipairs(b) do
		if find(b_,a) then table.insert(ret, b_) end
	end
	return ret
end
