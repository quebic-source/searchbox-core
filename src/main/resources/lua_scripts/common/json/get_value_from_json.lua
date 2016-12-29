local function get_value_from_json(json_str, field)
	return cjson.decode(json_str)[field]
end
