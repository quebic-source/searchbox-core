local function union(a, b)
	for _,b_ in ipairs(b) do
		if not find(b_, a) then table.insert(a, b_) end
	end
	return a
end
