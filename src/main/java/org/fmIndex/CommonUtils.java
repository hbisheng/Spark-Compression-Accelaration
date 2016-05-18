package org.fmIndex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CommonUtils {
	public static byte[] readFile(String filePath) {
		byte[] data = null;
		if (new File(filePath).length() < Integer.MAX_VALUE) {
			Path path = Paths.get(filePath);
			try {
				data = Files.readAllBytes(path);
				System.out.println("Read data length:" + data.length);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
		return data;
	}
	
	public static long bytesToLong(byte[] bytes) {
		final ByteBuffer bb = ByteBuffer.wrap(bytes);
	    bb.order(ByteOrder.LITTLE_ENDIAN);
    	return bb.getLong();
	}

	public static void loadReadsFromFile(ArrayList<ReadTWrapper> reads,
			String arg1) throws IOException, Exception {
		// loading reads
		BufferedReader data = new BufferedReader(new InputStreamReader(new FileInputStream(arg1)));
		String line;
		int lineCnt = 0;
		while ((line = data.readLine()) != null) {
			if(lineCnt++ % 4 != 1) {
				continue;
			}	
			reads.add(new ReadTWrapper(line.trim().toUpperCase().getBytes()));
		}
	}
}
