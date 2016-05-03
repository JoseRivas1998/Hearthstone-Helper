package com.tcg.hearthstoneHelper;

import java.io.InputStream;

public class FileHandle {

	public static InputStream inputStreamFromFile(String pathname) {
		try {
			InputStream inputStream = FileHandle.class.getResourceAsStream(pathname);
			return inputStream;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
