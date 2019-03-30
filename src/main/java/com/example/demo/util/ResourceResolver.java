package com.example.demo.util;

import java.io.File;

/**
 * This is an utility class to help file locating.
 */

public class ResourceResolver {
	
	/** The base folder to store all the data used by this project. */
	private static final String BASE_DIR = System.getProperty("user.home") + "/hackSC-master";
	
	/**
	 * Get the file used to store the store object JSON
	 *
	 * @param storeId
	 * @return
	 */
	public static File getStoreFile() {
		File file = new File(BASE_DIR + "/" + "store-map.json");
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		return file;
	}
}
