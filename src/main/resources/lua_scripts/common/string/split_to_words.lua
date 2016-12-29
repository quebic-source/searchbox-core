local function split_to_words(s)
	local words = {}
	for word in s:gmatch("%w+") do table.insert(words, word) end
	return words
end
