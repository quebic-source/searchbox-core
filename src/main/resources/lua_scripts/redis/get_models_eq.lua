local function get_models_eq(setName)
	return redis.call('ZRANGE', setName, 0, -1)
end
