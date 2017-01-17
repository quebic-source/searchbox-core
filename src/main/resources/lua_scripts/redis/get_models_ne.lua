local function get_models_ne(setName, fieldValue)
	return difference(get_models_by_pattern(setName, '*'), get_models_by_pattern(setName, fieldValue .. '*'))
end