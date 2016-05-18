package org.fmIndex;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

/**
struct read_t {
	char sym[MAX_READ_LENGTH+1];
	uint32_t low;
	uint32_t high;
	uint8_t len;
	bool is_align;
}
*/

public class ReadTWrapper {
	
	byte[] sym;
	long low;
	long high;
	int len;
	boolean is_align;
	public ReadTWrapper(byte[] bytes) throws Exception {
		if(bytes.length > Constant.MAX_READ_LENGTH) {
			throw new Exception("Read len =" +bytes.length+ " exceeds MAX_READ_LENGTH("+Constant.MAX_READ_LENGTH+")");
		}
		sym = bytes;
		len = bytes.length;
		is_align = false;
	}
}
