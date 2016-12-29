local function find(a, tbl)
	for _,a_ in ipairs(tbl) do if a_==a then return true end end
end
