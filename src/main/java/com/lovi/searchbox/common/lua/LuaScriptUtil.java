package com.lovi.searchbox.common.lua;

import static com.lovi.searchbox.config.ConfigKeys.lua_scripts_dir;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class LuaScriptUtil {

	/*
	public static String readScript(String scriptName) throws IOException {
		Path file = Paths.get(lua_scripts_dir + "/" + scriptName + ".lua");
		return new String(Files.readAllBytes(file));
	}*/
	
	public static String readScript(String scriptName) throws IOException {
		Resource resource = new ClassPathResource(lua_scripts_dir + "/" + scriptName + ".lua");
		InputStream resourceInputStream = resource.getInputStream();
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(resourceInputStream))) {
            return buffer.lines().collect(Collectors.joining("\n"));
        }
	}
	
}
