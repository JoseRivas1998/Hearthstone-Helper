package com.tcg.hearthstoneHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import org.json.JSONObject;

public class JSONUtils {

	public static String getJsonStringFromFile(URI uri) {
		try {
			Scanner scanner;
			scanner = new Scanner(new File(uri));
			String json = scanner.useDelimiter("\\Z").next();
			scanner.close();
			return json;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return "{}";
	}
	
	public static String getJsonStringFromFile(File file) {
		try {
			Scanner scanner;
			scanner = new Scanner(file);
			String json = scanner.useDelimiter("\\Z").next();
			scanner.close();
			return json;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return "{}";
	}
	
	public static boolean objectExists(JSONObject jsonObject, String key) {
		Object o;
		try {
			o = jsonObject.get(key);
		} catch(Exception e) {
			return false;
		}
		return o != null;
	}
	
	public static String[] getNames(JSONObject jsonObject) {
		String[] array = JSONObject.getNames(jsonObject);
		Collections.sort(Arrays.asList(array));
		return array;
	}
	
}
