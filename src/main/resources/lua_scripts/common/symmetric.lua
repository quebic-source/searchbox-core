local function symmetric(a, b)
	return difference(union(a,b), intersection(a,b))
end